package poi.game.models.templates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.Sprite;
import poi.game.models.entityComponents.SpriteAnimation;
//position
//Velocity
//Sprite
//boost
//collidable
//animation

public abstract class PenguinTemplate{
        protected Vector2 position;
        protected Vector2 velocity;
        protected Texture textures;
        protected SpriteAnimation animation;

        protected PenguinTemplate(float x, float y, String textures, int rows, int columns) {
                this.position = new Vector2(x, y);
                this.textures = new Texture(textures);
                animation = new SpriteAnimation(this.textures, rows, columns);
        }

        public abstract void update(float dt);
        public abstract Vector2 setVelocity();

        public Texture getTextureSheet(){
                return this.textures;
        }

        public Vector2 getPosition() {
                return this.position;
        }

        public Vector2 getVelocity() {
                return this.velocity;
        }

        public void setPosition(float x, float y) {
                this.position.x = x;
                this.position.y = y;
        }
}
