package poi.game.models.entityComponents;

public class Velocity {
    private float x;
    private float y;

    public Velocity(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getVelocityX() {
        return this.x;
    }

    public float getVelocityY() {
        return this.y;
    }

    public void setVelocityX(float x) {
        this.x = x;
    }

    public void setVelocityY(float y) {
        this.y = y;
    }
}
