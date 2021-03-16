package poi.game.models.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.SpriteAnimation;
import poi.game.models.templates.PenguinTemplate;

public class Player extends PenguinTemplate {

    private int MOVEMENT = 0;


    private SpriteAnimation Animation;
    private Texture textures;
    private Rectangle bounds;

    protected Player(float x, float y) {
        super(x, y);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public Texture getTextureSheet() {
        return null;
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
