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
                Vector3 touchTransformed = cam.unproject(new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0)); // Scaling touches to Android-mode
                touchPos.set(touchTransformed.x, touchTransformed.y, 0);
                touches.put(i, touchPos.x);

                // Beveger pingvin 1 ved trykk på venstre halvdel
                if (joystickController.joystick1.getJoystickTouched()) {
                    //venstre
                    if (joystickController.joystick1.getMoveLeft()) {
                        if (ECSEngine.playerMapper.get(entity).id == 1) {
                            bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                    //høyre
                    if (!joystickController.joystick1.getMoveLeft()) {
                        if (ECSEngine.playerMapper.get(entity).id == 1) {
                            bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                }
                // Beveger pingvin 2 ved trykk på høyre halvdel
                if (joystickController.joystick2.getJoystickTouched()) {
                    //venstre
                    if (joystickController.joystick2.getMoveLeft()) {
                        joystickController.joystick2.setMoveLeft(true);
                        if (ECSEngine.playerMapper.get(entity).id == 2) {
                            bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                    //høyre
                    if (!joystickController.joystick2.getMoveLeft()) {
                        joystickController.joystick2.setMoveLeft(false);
                        if (ECSEngine.playerMapper.get(entity).id == 2) {
                            bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
                        }
                    }
                }
            }
            //Gdx.app.log("Movement", "Touches: " + touches.get(0));
        }
    }
}

