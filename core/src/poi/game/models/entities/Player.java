package poi.game.models.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.SpriteAnimation;
import poi.game.models.templates.PenguinTemplate;

public class Player extends PenguinTemplate {

    private int MOVEMENT = 0;

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
    public void update(float dt) {}

    @Override
    public void setVelocity() {
        this.velocity.x = 100;
        this.velocity.y = 0;
    }

    @Override
    public TextureRegion getAnimation() {
        return this.animation.setupSprite();
    }

    @Override
    public void checkBorder() {
        //Collision at left border
        if (this.getPosition().x <= 0) {
            this.getPosition().x = 0;
            this.setVelocity();
        }

        //Collision at right border
        else if (this.getPosition().x >= Gdx.graphics.getWidth() - WIDTH) {
            this.getPosition().x = Gdx.graphics.getWidth() - WIDTH;
            this.setVelocity();
        }

        //Collision at bottom
        if (this.getPosition().y <= 0) {
            this.getPosition().y = 0;
            this.setVelocity();
        }

        //Collision at top
        else if (this.getPosition().y >= Gdx.graphics.getHeight() - HEIGHT ) {
            this.getPosition().y = Gdx.graphics.getHeight() - HEIGHT;
            this.setVelocity();
        }
    }


    //position
    //Velocity
    //Sprite
    //boost
    //collidable
    //animation
}
