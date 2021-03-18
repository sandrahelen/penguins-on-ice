package poi.game.models.entityComponents;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class SpriteAnimation {
    private final Texture spriteSheet;
    private final int FRAME_ROW;
    private final int FRAME_COL;
    private Animation<TextureRegion> spriteAnimation;
    private float maxFrameTime; // 100ms
    private float currentFrameTime;
    private int frameCount; //antall frames
    private int frame; // current frame

    public SpriteAnimation(Texture spriteSheet, int FRAME_ROW, int FRAME_COL) {
        //sheet = new Texture("heliAnim.png"); Definer i Player!
        this.spriteSheet = spriteSheet;
        this.frame = 0;
        this.FRAME_ROW = FRAME_ROW;
        this.FRAME_COL = FRAME_COL;
        //this.frameCount = frameCount;
        //this.maxFrameTime = maxFrameTime; //0.5f
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

    public void animate() {
        TextureRegion[][] tmp = TextureRegion.split(this.spriteSheet,
                this.spriteSheet.getWidth() / FRAME_COL,
                this.spriteSheet.getHeight() / FRAME_ROW);

        TextureRegion[] frames = new TextureRegion[FRAME_COL * FRAME_ROW];
        int index = 0;
        for (int i = 0; i < FRAME_ROW; i++) {
            for (int j = 0; j < FRAME_COL; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        this.spriteAnimation = new Animation<TextureRegion>(0.1f, frames);
    }


    public Animation<TextureRegion> getAnimation() {
    return this.spriteAnimation;
    }
}
