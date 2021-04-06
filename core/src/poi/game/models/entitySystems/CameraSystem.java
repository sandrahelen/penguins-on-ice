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
    protected void processEntity(final Entity player1, final Entity player2, final float deltaTime){
        Gdx.app.log("Debug", String.valueOf(ECSEngine.bodyMapper.get(entity).body.getPosition().y));
        //Gdx.app.log("1", String.valueOf(ECSEngine.bodyMapper.get(entity).body.getPosition().y[0]));
        final Vector2 posPlayer1 = new Vector2(Poi.WIDTH/2, ECSEngine.bodyMapper.get(player1).body.getPosition().y);
        final Vector2 posPlayer2 = new Vector2(Poi.WIDTH/2, ECSEngine.bodyMapper.get(player2).body.getPosition().y);
        if (posPlayer1.y < posPlayer2.y){
            camera.position.y = posPlayer1.y;
        }
        if (posPlayer2.y < posPlayer1.y){
            camera.position.y = posPlayer2.y;
        }

        camera.position.set(pos,0);

    }
}
