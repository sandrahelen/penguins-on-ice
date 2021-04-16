package poi.game.models.entitySystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.OrthographicCamera;

import poi.game.controllers.BoostController;
import poi.game.controllers.JoystickController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;


public class MovementSystem extends IteratingSystem{
    private final OrthographicCamera cam;
    private final JoystickController joystickController;
    private final BoostController boostController;

    public MovementSystem(OrthographicCamera cam, JoystickController joystickController, BoostController boostController) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.cam = cam;
        this.joystickController = joystickController;
        this.boostController = boostController;
    }


    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        final Vector2 center = bodyComponent.body.getWorldCenter();
        //Gdx.app.log("Speed", String.valueOf(bodyComponent.body.getLinearVelocity().y));

        // Air resistance
        if(bodyComponent.body.getLinearVelocity().y > 90){
            bodyComponent.body.applyLinearImpulse(0, -1.5f*bodyComponent.body.getLinearVelocity().y, center.x, center.y, false);
        }
        // Boost for player 1
        if(boostController.getBoostComponent1().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                bodyComponent.body.applyLinearImpulse(0, 2000, center.x, center.y, false);
                bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }
        // Boost for player 2
        if(boostController.getBoostComponent2().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                bodyComponent.body.applyLinearImpulse(0, 2000, center.x, center.y, true);
                bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }

        // Movement with joystick 1
        if (joystickController.joystick1.getJoystickTouched()) {
            // Penguin goes left
            if (joystickController.joystick1.getMoveLeft()) {
                if (ECSEngine.playerMapper.get(entity).id == 1) {
                    bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                }
            }
            // Penguin goes right
            if (!joystickController.joystick1.getMoveLeft()) {
                if (ECSEngine.playerMapper.get(entity).id == 1) {
                    bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
                }
            }
        }
        // Movement with joystick 2
        if (joystickController.joystick2.getJoystickTouched()) {
            // Penguin goes left
            if (joystickController.joystick2.getMoveLeft()) {
                joystickController.joystick2.setMoveLeft(true);
                if (ECSEngine.playerMapper.get(entity).id == 2) {
                    bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
                }
            }
            // Penguin goes right
            if (!joystickController.joystick2.getMoveLeft()) {
                joystickController.joystick2.setMoveLeft(false);
                if (ECSEngine.playerMapper.get(entity).id == 2) {
                    bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
                }
            }
        }
    }
}

