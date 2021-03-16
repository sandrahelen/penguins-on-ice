package poi.game.models.templates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.Sprite;
//position
//Velocity
//Sprite
//boost
//collidable
//animation

abstract class PenguinTemplate{
        protected Vector2 position;
        protected Vector2 velocity;
        protected Texture texture;

        protected PenguinTemplate(float x, float y) {
                this.position = new Vector2(x, y);
        }

        public abstract void update(float dt);
        public abstract Texture getTextureSheet();
        public abstract Vector2 setVelocity();

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
