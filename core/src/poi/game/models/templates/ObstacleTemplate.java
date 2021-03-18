package poi.game.models.templates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public abstract class ObstacleTemplate {

    protected Vector2 position;
    protected Vector2 velocity;
    protected Texture textures;
    protected int rows, columns;

    protected ObstacleTemplate(float x, float y, String textures, int rows, int columns) {
        this.position = new Vector2(x, y);
        this.textures = new Texture(textures);
        this.rows = rows;
        this.columns = columns;
    }

    public abstract void update(float dt);
    public abstract Vector2 setVelocity();

    public Texture getTexture() {
        return this.textures;
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


