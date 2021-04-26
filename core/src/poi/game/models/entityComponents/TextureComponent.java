package poi.game.models.entityComponents;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class TextureComponent implements Component, Pool.Poolable {

    public Animation<TextureRegion> textureAnimation;
    private float stateTime;

    public Animation<TextureRegion> animate(String textureString, int rows, int columns) {
        Texture texture = new Texture(textureString);
        TextureRegion[][] tmp = TextureRegion.split(texture,
                texture.getWidth() / columns,
                texture.getHeight() / rows);

        TextureRegion[] frames = new TextureRegion[columns * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        return textureAnimation = new Animation<TextureRegion>(0.1f, frames);
    }


    //Setup for sprite and frames
    public TextureRegion setupSprite() {
        this.setStateTime(Gdx.graphics.getDeltaTime());
        return textureAnimation.getKeyFrame(this.getStateTime(), true);
    }

    public void setStateTime(float time) {
        stateTime += time;
    }

    public float getStateTime() {
        return stateTime;
    }

    @Override
    public void reset(){
        textureAnimation= null;
    }

}
