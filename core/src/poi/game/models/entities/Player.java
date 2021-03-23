package poi.game.models.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import poi.game.Poi;
import poi.game.models.entityComponents.SpriteAnimation;
import poi.game.models.templates.PenguinTemplate;

public class Player extends PenguinTemplate {

    private int MOVEMENT = 50;

    private Rectangle bounds;

    private static final int HEIGHT = 48, WIDTH = 48;
    private SpriteAnimation animation;

    public Player(float x, float y, String texture, int rows, int columns) {
        super(x, y, texture, rows, columns);
        this.animation = new SpriteAnimation(this.texture, this.rows, this.columns);
        this.setVelocity();
    }

    @Override
    /*
    public void update(float dt) {
        animation.update(dt);
    }
    */
    public void update(float dt, OrthographicCamera cam) {
        if(!this.checkBorder(cam)){
            this.position.add(0, MOVEMENT * dt);
        }
        //this.position.add(0, MOVEMENT * dt);
        //this.velocity.y = MOVEMENT * dt;
    }

    @Override
    public void setVelocity() {
        this.velocity.x = 100;
        this.velocity.y = (float) Math.random() * 50;
    }

    @Override
    public TextureRegion getAnimation() {
        return this.animation.setupSprite();
    }

    @Override
    public boolean checkBorder(OrthographicCamera cam) {
        //Collision at left border
        if (this.getPosition().x <= 0) {
            this.getPosition().x = 0;
            return true;
        }

        //Collision at right border
        else if (this.getPosition().x >= Gdx.graphics.getWidth() - WIDTH) {
            this.getPosition().x = Gdx.graphics.getWidth() - WIDTH;
            return true;
        }

        //Collision at bottom
        if (this.getPosition().y <= 0 /*cam.position.y*/) {
            this.getPosition().y = 0 /*cam.position.y*/;
            this.position.add(0, MOVEMENT);
            //this.position.y *= 2;
            return true;
        }

        //Collision at top
        else if (this.getPosition().y >= Gdx.graphics.getHeight() + cam.position.y /*+ cam.viewportHeight*/ - HEIGHT ) {
            this.getPosition().y = Gdx.graphics.getHeight() /*+ cam.position.y + cam.viewportHeight*/ - HEIGHT;
            this.position.add(0, -MOVEMENT);
            return true;
        }
        return false;
    }


    //position
    //Velocity
    //Sprite
    //boost
    //collidable
    //animation
}
