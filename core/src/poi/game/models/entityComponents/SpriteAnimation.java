package poi.game.models.entityComponents;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class SpriteAnimation {
        private Texture textures;
        private float maxFrameTime; // 100ms
        private float currentFrameTime;
        private int frameCount; //antall frames
        private int frame; // current frame

        public SpriteAnimation(Texture textures, int frameCount, int maxFrameTime) {
            //sheet = new Texture("heliAnim.png"); Definer i Player!
            this.textures = textures;
            this.frame = 0;
            this.frameCount = frameCount;
            this.maxFrameTime = maxFrameTime; //0.5f
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

        public Texture getFrame() {
            return this.frames.get(this.frame);
        }

        public Animation<TextureRegion> animate(Texture spriteSheet, int frameCol, int frameRow) {
            TextureRegion[][] tmp = TextureRegion.split(spriteSheet,
                    spriteSheet.getWidth() / frameCol,
                    spriteSheet.getHeight() / frameRow);

            TextureRegion[] frames = new TextureRegion[frameCol * frameRow];
            int index = 0;
            for (int i = 0; i < frameRow; i++) {
                for (int j = 0; j < frameCol; j++) {
                    frames[index++] = tmp[i][j];
                }
            }
            spriteAnimation = new Animation<TextureRegion>(0.1f, frames);
        }


    @Override
    public Animation<TextureRegion> getAnimation() {
        return flyAnimation;
    }

    @Override
    public Texture getSheet() {
        return spriteSheet;
    }

}
