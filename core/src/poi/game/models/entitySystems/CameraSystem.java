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
    private Vector2 pos1;
    private Vector2 pos2;

    public CameraSystem(OrthographicCamera camera){
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.camera = camera;
    }

    @Override
    protected void processEntity(final Entity entity, final float deltaTime){
        if(ECSEngine.playerMapper.get(entity).id == 1) {
            pos1 = ECSEngine.bodyMapper.get(entity).body.getPosition();
        }
        if(ECSEngine.playerMapper.get(entity).id == 2) {
            pos2 = ECSEngine.bodyMapper.get(entity).body.getPosition();
        }
        if(pos1 != null && pos2 != null ) {
            if (pos1.y < pos2.y) {
                camera.position.set(Poi.WIDTH/2, pos1.y+Poi.HEIGHT/4, 0);
            }
            else{
                camera.position.set(Poi.WIDTH/2,pos2.y+Poi.HEIGHT/4, 0);
            }
        }
        else{
            camera.position.set(Poi.WIDTH/2, pos1.y+Poi.HEIGHT/4, 0);
        }
        }


    }
