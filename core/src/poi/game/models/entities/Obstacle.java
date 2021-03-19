package poi.game.models.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.SpriteAnimation;
import poi.game.models.templates.ObstacleTemplate;

public class Obstacle extends ObstacleTemplate {


    private int MOVEMENT = 0;

    public Obstacle(float x, float y, String texture, int rows, int columns){
        super(x, y, texture, rows, columns);
    }

    /*
    public Obstacle(float x, float y, Texture texture){
        super(x, y, texture);

    }
    */

    @Override
    public void update(float dt) {}


    @Override
    public Vector2 setVelocity() {
        return null;
    }

    //sprite
    //position
    //velocity
    //animation
}
