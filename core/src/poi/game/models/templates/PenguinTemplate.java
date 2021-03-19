package poi.game.models.templates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
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
        protected Texture texture;
        protected SpriteAnimation animation;
        protected int rows, columns;
        protected Sprite sprite;

        protected PenguinTemplate(float x, float y, String textures, int rows, int columns) {
                this.position = new Vector2(x, y);
                this.texture = new Texture(textures);
                this.sprite = new Sprite(this.texture);
                this.rows = rows;
                this.columns = columns;
                //animation = new SpriteAnimation(this.texture, rows, columns);
        }

        public abstract void update(float dt);
        public abstract Vector2 setVelocity();

        /*public Texture getTexture(){
                return this.texture;
        }*/

        public Sprite getSprite() {
                return this.sprite;
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
