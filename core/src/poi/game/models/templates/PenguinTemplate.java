package poi.game.models.templates;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
        protected int rows, columns;
        //protected float stateTime;

        protected PenguinTemplate(float x, float y, String textures, int rows, int columns) {
                this.position = new Vector2(x, y);
                this.velocity = new Vector2(0,0);
                this.texture = new Texture(textures);
                this.rows = rows;
                this.columns = columns;
                //this.stateTime = 0f;
        }

        public abstract void update(float dt, OrthographicCamera cam);
        public abstract void setVelocity();
        public abstract TextureRegion getAnimation();
        public abstract boolean checkBorder(OrthographicCamera cam);

        public Texture getTexture(){
                return this.texture;
        }

        /*
        public void setStateTime(float time) {
                this.stateTime += time;
        }

        public float getStateTime() {
                return this.stateTime;
        }
         */

        public Vector2 getPosition() {
                return this.position;
        }

        public Vector2 getVelocity() {
                return this.velocity;
        }

        public void setPosition(float x, float y) {
                this.position.x += x;
                this.position.y += y;
        }
}
