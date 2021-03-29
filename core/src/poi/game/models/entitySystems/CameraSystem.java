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

    public CameraSystem(final Poi context){
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        camera = context.getCamera();
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime){
        final Vector2 pos = new Vector2(400, ECSEngine.bodyMapper.get(entity).body.getPosition().y);
        camera.position.set(pos,0);

        //Gdx.app.log("MyTag", String.valueOf(camera.position));
    }
}
