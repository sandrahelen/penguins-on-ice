package poi.game.models.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.SpriteAnimation;
import poi.game.models.templates.PenguinTemplate;

public class Player extends PenguinTemplate {

    private int MOVEMENT = 0;

    /*
    private SpriteAnimation animation;
    private Texture textures;
     */
    private Rectangle bounds;

    public Player(float x, float y, String texture, int rows, int columns) {
        super(x, y, texture, rows, columns);
        //animation = new Animation(textures, rows, columns);
    }

    @Override
    public void update(float dt) {
        animation.update(dt);
    }

    @Override
    public Vector2 setVelocity() {
        return null;
    }


    //position
    //Velocity
    //Sprite
    //boost
    //collidable
    //animation
}
