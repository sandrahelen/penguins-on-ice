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
import poi.game.models.entityComponents.TextureComponent;

public class GoalSystem extends IteratingSystem {

    private boolean reachedFinish1;
    private boolean reachedFinish2;
    private boolean reachedFinish;


    private final TiledMap tiledMap;

    public GoalSystem(TiledMap tiledMap) {
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.tiledMap = tiledMap;
        reachedFinish = false;

    }


    @Override
    public void processEntity(final Entity entity, final float deltaTime) {
        final TextureComponent textureComponent = ECSEngine.textureMapper.get(entity);
        final MapLayer goalLayer = tiledMap.getLayers().get("Goal");
        for(MapObject object : goalLayer.getObjects()) {
            final Rectangle goal = ((RectangleMapObject) object).getRectangle();
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                if (ECSEngine.bodyMapper.get(entity).body.getPosition().y > goal.y) {
                    reachedFinish1 = true;
                    ECSEngine.bodyMapper.get(entity).body.setLinearVelocity(0,0);
                    setReachedFinish();
                }
            }
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                if (ECSEngine.bodyMapper.get(entity).body.getPosition().y > 600goal.y) {
                    reachedFinish2 = true;
                    ECSEngine.bodyMapper.get(entity).body.setLinearVelocity(0,0);
                    setReachedFinish();
                }
            }
            if(reachedFinish1){
                textureComponent.textureAnimation = textureComponent.animate("players/p1-finish.png", 6, 3);
            }
        }
    }

    private void setReachedFinish(){
        if(reachedFinish1 && reachedFinish2){
            reachedFinish = true;
        }
    }
    public boolean isFinished(){
        return reachedFinish;
    }
}
