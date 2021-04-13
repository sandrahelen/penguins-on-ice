package poi.game.models.entitySystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import poi.game.Poi;
import poi.game.controllers.GameController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;
import poi.game.views.GameView;



public class MovementSystem extends IteratingSystem{
    public static int touch = 0;
    private Vector3 touchPos;
    private final OrthographicCamera camera;


    public MovementSystem(OrthographicCamera camera) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        touchPos = new Vector3();
        this.camera = camera;
    }

    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        //bodyComponent.body.setTransform(bodyComponent.body.getPosition().x,bodyComponent.body.getPosition().y+1, 0);
        if (Gdx.input.isTouched()) {
            Vector3 touchTransformed = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            touchPos.set(touchTransformed.x, touchTransformed.y, 0);
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
}

