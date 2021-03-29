package poi.game.models.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationSystem /*extends IteratingSystem*/ {
    private float stateTime = 0f;

    public void setStateTime(float time) {
        stateTime += time;
    }

    public float getStateTime() {
        return stateTime;
    }

    //Setup for sprite and frames
    /*public TextureRegion setupSprite() {
        this.setStateTime(Gdx.graphics.getDeltaTime());
        return this.getAnimation().getKeyFrame(this.getStateTime(), true);
    }*/

    /*@Override
    protected void processEntity(Entity entity, float deltaTime) {*/

    //}
}
