package poi.game.models.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import poi.game.Poi;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;

public class CameraBoundsCollisionSystem extends IteratingSystem {

    private final OrthographicCamera camera;

    public CameraBoundsCollisionSystem(OrthographicCamera camera){
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.camera = camera;
    }

    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        final Vector2 center = bodyComponent.body.getWorldCenter();
        if (bodyComponent.body.getPosition().x < 10) {
            bodyComponent.body.setLinearVelocity(50, bodyComponent.body.getLinearVelocity().y);
            bodyComponent.body.applyLinearImpulse(70, 0, center.x, center.y, true);
        }
        if (bodyComponent.body.getPosition().x > Poi.WIDTH - 10) {
            bodyComponent.body.setLinearVelocity(-50, bodyComponent.body.getLinearVelocity().y);
            bodyComponent.body.applyLinearImpulse(-70, 0, center.x, center.y, true);
        }
        if (bodyComponent.body.getPosition().y > camera.position.y + Poi.HEIGHT/2 - 15) {
            bodyComponent.body.setTransform(bodyComponent.body.getPosition().x,camera.position.y + Poi.HEIGHT/2 - 15,0);
        }
    }

}
