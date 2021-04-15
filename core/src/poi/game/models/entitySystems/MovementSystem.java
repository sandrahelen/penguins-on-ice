package poi.game.models.entitySystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


import java.util.HashMap;
import java.util.Map;

import poi.game.Poi;
import poi.game.controllers.BoostController;
import poi.game.controllers.JoystickController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;


public class MovementSystem extends IteratingSystem{
    public static int touch = 0;
    private int touchP1 = 0;
    private int touchP2 = 0;
    private Vector2 xFactor;
    private Vector3 touchPos;
    private final OrthographicCamera cam;
    private Map<Integer, Float> touches = new HashMap<>();
    private final JoystickController joystickController1;
    private final JoystickController joystickController2;
    private final BoostController boostController;

    private Rectangle boundsJoystick;

    public MovementSystem(OrthographicCamera cam, JoystickController joystickController1, JoystickController joystickController2, BoostController boostController) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        xFactor = new Vector2(10, 0);
        touchPos = new Vector3();
        this.cam = cam;
        for (int i=0; i<5; i++) {   // Adding possible fingers to Hashmap
            touches.put(i, touchPos.x);
            touches.put(i, touchPos.y);
        }
        this.joystickController1 = joystickController1;
        this.joystickController2 = joystickController2;
        this.boostController = boostController;

        boundsJoystick = this.joystickController1.getBounds();
    }



    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        final Vector2 center = bodyComponent.body.getWorldCenter();
        //Gdx.app.log("Speed", String.valueOf(bodyComponent.body.getLinearVelocity().y));
        //Air resistance
        if(bodyComponent.body.getLinearVelocity().y > 90){
            bodyComponent.body.applyLinearImpulse(0, -1.5f*bodyComponent.body.getLinearVelocity().y, center.x, center.y, false);
        }
        if(boostController.getBoostComponent1().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                bodyComponent.body.applyLinearImpulse(0, 2000, center.x, center.y, false);
                bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }
        if(boostController.getBoostComponent2().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                bodyComponent.body.applyLinearImpulse(0, 2000, center.x, center.y, true);
                bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }
        for (int i=0; i<=5; i++) {  // Max five finger touch simultaneously
            if (Gdx.input.isTouched(i)) {
                if (boundsJoystick.contains(Gdx.input.getX(), Gdx.input.getY())) {
                    System.out.println("Joystick touched");
                } else {
                    //System.out.println("NOT touched");
                }
                touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
                if (Poi.WIDTH / 3 > touchPos.x && touchPos.x > 0) {
                    //Plaayer 1 moves left
                    if (ECSEngine.playerMapper.get(entity).id == 1) {
                        touchP2 = 2;
                        joystickController1.setPosition(touchP2);
                        bodyComponent.body.setLinearVelocity(-100, bodyComponent.body.getLinearVelocity().y);
                    }
                }
                //Player 1 moves right
                if (Poi.WIDTH / 2 > touchPos.x && touchPos.x > Poi.WIDTH / 3) {
                    if (ECSEngine.playerMapper.get(entity).id == 1) {
                        touchP1 = 1;
                        joystickController1.setPosition(touchP1);
                        bodyComponent.body.setLinearVelocity(100, bodyComponent.body.getLinearVelocity().y);
                    }
                }
                if (Poi.WIDTH / 2 + Poi.WIDTH / 4 > touchPos.x && touchPos.x > Poi.WIDTH / 2) {
                    if (ECSEngine.playerMapper.get(entity).id == 2) {
                        touchP2 = 2;
                        joystickController2.setPosition(touchP2);
                        bodyComponent.body.setLinearVelocity(-100, bodyComponent.body.getLinearVelocity().y);
                    }
                }
                if (Poi.WIDTH > touchPos.x && touchPos.x > Poi.WIDTH / 2 + Poi.WIDTH / 4) {
                    if (ECSEngine.playerMapper.get(entity).id == 2) {
                        touchP2 = 1;
                        joystickController2.setPosition(touchP2);
                        bodyComponent.body.setLinearVelocity(100, bodyComponent.body.getLinearVelocity().y);
                    }
                }
                /*
            else {
                //Player moves forward only
                if (ECSEngine.playerMapper.get(entity).id == 1) {
                    touchP1 = 0;
                    gameController1.setPosition(touchP1);
                }
                if (ECSEngine.playerMapper.get(entity).id == 2) {
                    touchP2 = 0;
                    gameController2.setPosition(touchP2);
                }

            }
            */
                Vector3 touchTransformed = cam.unproject(new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0)); // Scaling touches to Android-mode
                touchPos.set(touchTransformed.x, touchTransformed.y, 0);
                touches.put(i, touchPos.x);
                if (touches.get(i) < Poi.WIDTH/2) {     // Beveger pingvin 1 ved trykk på venstre halvdel
                    if (Poi.WIDTH / 3 > touchPos.x && touchPos.x > 0) {
                        if (ECSEngine.playerMapper.get(entity).id == 1) {
                            bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                    if (Poi.WIDTH / 2 > touchPos.x && touchPos.x > Poi.WIDTH / 3) {
                        if (ECSEngine.playerMapper.get(entity).id == 1) {
                            bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                }
                if (touches.get(i) > Poi.WIDTH/2) {     // Beveger pingvin 2 ved trykk på høyre halvdel
                    if (Poi.WIDTH / 2 + Poi.WIDTH / 4 > touchPos.x && touchPos.x > Poi.WIDTH / 2) {
                        if (ECSEngine.playerMapper.get(entity).id == 2) {
                            bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                    if (Poi.WIDTH > touchPos.x && touchPos.x > Poi.WIDTH / 2 + Poi.WIDTH / 4) {
                        if (ECSEngine.playerMapper.get(entity).id == 2) {
                            bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                }
            }
            //Gdx.app.log("Movement", "Touches: " + touches);
        }
    }
}

