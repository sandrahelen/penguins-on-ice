package poi.game.models.entitySystems;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.math.Vector2;

import poi.game.Poi;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;

public class CameraSystem extends IteratingSystem {
    private final OrthographicCamera camera;

    public CameraSystem(OrthographicCamera camera){
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime){
        //Gdx.app.log("Debug", String.valueOf(ECSEngine.bodyMapper.get(entity).body.getPosition().y));
        final Vector2 pos = new Vector2(Poi.WIDTH/2, ECSEngine.bodyMapper.get(entity).body.getPosition().y);
        camera.position.set(pos,0);

    }
}
