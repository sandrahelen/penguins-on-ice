package poi.game.models.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;

public class GoalSystem extends IteratingSystem {

    private boolean reachedFinish;

    private final TiledMap tiledMap;

    public GoalSystem(TiledMap tiledMap) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.tiledMap = tiledMap;
        reachedFinish = false;

    }


    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final MapLayer goalLayer = tiledMap.getLayers().get("Goal");
        for(MapObject object : goalLayer.getObjects()) {
            final Rectangle goal = ((RectangleMapObject) object).getRectangle();
            if (ECSEngine.bodyMapper.get(entity).body.getPosition().y > 100 /*goal.y*/) {
                reachedFinish = true;
            }
        }
    }

    public boolean isFinished(){
        return reachedFinish;
    }
}
