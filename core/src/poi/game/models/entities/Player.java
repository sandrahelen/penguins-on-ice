package poi.game.models.entities;

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
    /*public void update(float dt) {
        animation.update(dt);
    }
    */
    public void update(float dt) {}

    @Override
    public void setVelocity() {
        this.velocity.x = (float) Math.random() * 50 + 50;
        this.velocity.y = (float) Math.random() * 50 + 50;
    }

    @Override
    public Animation<TextureRegion> getAnimation() {
        return this.animation.getAnimation();
    }

    @Override
    public void checkBorder() {

    }


    //position
    //Velocity
    //Sprite
    //boost
    //collidable
    //animation
}
