package poi.game.models.templates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class ObstacleTemplate {

    protected Vector2 position;
    protected Vector2 velocity;
    protected Texture texture;
    protected int rows, columns;
    //protected Sprite sprite;

    protected ObstacleTemplate(float x, float y, String textures, int rows, int columns) {
        this.position = new Vector2(x, y);
        this.velocity = new Vector2(0, 0);
        this.texture = new Texture(textures);
        //this.sprite = new Sprite(this.texture);
        this.rows = rows;
        this.columns = columns;
    }

    /*
    protected ObstacleTemplate(float x, float y, Texture texture) {
        this.position = new Vector2(x, y);
        sprite = new Sprite(texture);
    }
    */

    public abstract void update(float dt);
    public abstract void setVelocity();
    public abstract TextureRegion getAnimation();

    /*
    public Sprite getSprite() {
        return this.sprite;
    }
     */

    public Texture getTexture() {
        return this.texture;
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


