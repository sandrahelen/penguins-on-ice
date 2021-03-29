package poi.game.models.entityComponents;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class SpriteAnimation {
    private Animation<TextureRegion> spriteAnimation;
    private float maxFrameTime; // 100ms
    private float currentFrameTime;
    private int frameCount; //antall frames
    private int frame; // current frame
    private float stateTime;

    public SpriteAnimation(Texture texture, int rows, int columns) {
        //this.stateTime = 0f;
        //this.animate(texture, rows, columns);
    }

    public void update(float dt) {
        this.currentFrameTime += dt;
        if (this.currentFrameTime > this.maxFrameTime) {
            this.frame++;
            this.currentFrameTime = 0;
        }
        if (this.frame >= this.frameCount) {
            this.frame = 0;
        }
    }

    /*
    public Texture getFrame() {
        return this.frames.get(this.frame);
    }
     */

    public void animate(Texture texture, int rows, int columns) {
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
        this.spriteAnimation = new Animation<TextureRegion>(0.1f, frames);
    }


    public Animation<TextureRegion> getAnimation() {
        return this.spriteAnimation;
    }

    //Setup for sprite and frames
    public TextureRegion setupSprite() {
        this.setStateTime(Gdx.graphics.getDeltaTime());
        return this.getAnimation().getKeyFrame(this.getStateTime(), true);
    }

    public void setStateTime(float time) {
        stateTime += time;
    }

    public float getStateTime() {
        return stateTime;
    }

}
