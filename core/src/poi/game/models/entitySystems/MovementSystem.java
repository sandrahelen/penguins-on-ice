package poi.game.models.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;


import java.util.HashMap;
import java.util.Map;

import poi.game.Poi;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;


public class MovementSystem extends IteratingSystem{
    public static int touch = 0;
    private Vector3 touchPos;
    private final OrthographicCamera cam;
    private Map<Integer, Float> touches = new HashMap<>();


    public MovementSystem(OrthographicCamera cam) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        touchPos = new Vector3();
        this.cam = cam;
        for (int i=0; i<5; i++) {   // Adding possible fingers to Hashmap
            touches.put(i, touchPos.x);
            touches.put(i, touchPos.y);
        }
    }

    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        for (int i=0; i<=5; i++) {  // Max five finger touch simultaneously
            if (Gdx.input.isTouched(i)) {
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
            Gdx.app.log("Movement", "Touches: " + touches);
        }
    }
}

