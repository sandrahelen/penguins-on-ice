package poi.game.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

import poi.game.models.ECSEngine;


public class ObjectCreator {

    private final TiledMap tiledMap;
    private final World world;
    private final ECSEngine ecsEngine;

    public ObjectCreator(TiledMap tiledMap, ECSEngine ecsEngine, World world) {
        this.ecsEngine = ecsEngine;
        this.tiledMap = tiledMap;
        this.world = world;
        spawnGameObjects();
    }


    private void spawnGameObjects() {
        final MapLayer objectsLayer = tiledMap.getLayers().get("Obstacles");
        if (objectsLayer == null) {
            Gdx.app.log("Empty", "No obstacles in layer");
        }
        else{
            for(MapObject object : objectsLayer.getObjects()){
                final Rectangle iceBlock = ((RectangleMapObject) object).getRectangle();
                ecsEngine.createObstacle((int) iceBlock.x, (int) iceBlock.y, world);
            }
        }
    }
}