package poi.game.models.entitySystems;

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
    private final JoystickController joystickController;
    private final BoostController boostController;

    private Rectangle boundsJoystick1;
    private Rectangle boundsJoystick2;

    public MovementSystem(OrthographicCamera cam, JoystickController joystickController, BoostController boostController) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        xFactor = new Vector2(10, 0);
        touchPos = new Vector3();
        this.cam = cam;
        for (int i=0; i<5; i++) {   // Adding possible fingers to Hashmap
            touches.put(i, touchPos.x);
            touches.put(i, touchPos.y);
        }
        this.joystickController = joystickController;
        this.boostController = boostController;

        boundsJoystick1 = this.joystickController.joystick1.getBoundsJoystick();
        boundsJoystick2 = this.joystickController.joystick2.getBoundsJoystick();
    }



    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        if(boostController.getBoostComponent1().getBoost()){
            System.out.println("Player 1 moves faster");
        }
        if(boostController.getBoostComponent2().getBoost()){
            System.out.println("Player 2 moves faster");
        }
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        for (int i=0; i<=5; i++) {  // Max five finger touch simultaneously
            if (Gdx.input.isTouched(i)) {
                /*if (boundsJoystick.contains(Gdx.input.getX(), Gdx.input.getY())) {
                System.out.println("Joystick touched");
            }
            else {
                System.out.println("NOT touched");
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
            }*/
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

                // Beveger pingvin 1 ved trykk på venstre halvdel
                if (boundsJoystick1.contains(Gdx.input.getX(), Gdx.input.getY())) {
                    joystickController.joystick1.setJoystickTouched(true);
                    //venstre
                    if (touchPos.x < joystickController.joystick1.getPosition() + (boundsJoystick1.getWidth()/2)) {
                        joystickController.joystick1.setMovement(true);
                        if (ECSEngine.playerMapper.get(entity).id == 1) {
                            bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                    //høyre
                    if (touchPos.x > joystickController.joystick1.getPosition() + (boundsJoystick1.getWidth()/2) && touchPos.x < Poi.WIDTH / 2) {
                        joystickController.joystick1.setMovement(false);
                        if (ECSEngine.playerMapper.get(entity).id == 1) {
                            bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                }
                // Beveger pingvin 2 ved trykk på høyre halvdel
                if (boundsJoystick2.contains(Gdx.input.getX(), Gdx.input.getY())) {
                    joystickController.joystick1.setJoystickTouched(true);
                    //venstre
                    if (touchPos.x < joystickController.joystick2.getPosition() + (boundsJoystick2.getWidth()/2) && touchPos.x > Poi.WIDTH / 2) {
                        joystickController.joystick2.setMovement(true);
                        if (ECSEngine.playerMapper.get(entity).id == 2) {
                            bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                    //høyre
                    if (touchPos.x > joystickController.joystick2.getPosition() + (boundsJoystick2.getWidth()/2)) {
                        joystickController.joystick2.setMovement(false);
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

