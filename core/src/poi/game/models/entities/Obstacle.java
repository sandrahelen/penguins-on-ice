package poi.game.models.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.SpriteAnimation;
import poi.game.models.templates.ObstacleTemplate;

public class Obstacle extends ObstacleTemplate {


    private int MOVEMENT = 0;
    private static final int HEIGHT = 27, WIDTH = 20;
    private SpriteAnimation animation;

    public Obstacle(float x, float y, String texture, int rows, int columns){
        super(x, y, texture, rows, columns);
        this.animation = new SpriteAnimation(this.texture, this.rows, this.columns);
        this.setVelocity();
    }

    @Override
    public void update(float dt) {}


    @Override
    public void setVelocity() {
        this.velocity.x = 50;
        this.velocity.y = 50;
    }

    @Override
    public TextureRegion getAnimation() {
        return this.animation.setupSprite();
    }

    //sprite
    //position
    //velocity
    //animation
}
