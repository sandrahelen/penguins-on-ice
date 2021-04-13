package poi.game.controllers;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class JoystickController {
    private final int MAX_POS = 25;
    public Texture background;
    public Texture base;
    public Texture joystick;
    public float startPosX;
    private float startPosY;
    private Vector2 position;
    private OrthographicCamera cam;
    private final int id;
    private Rectangle boundsJoystick;

    public JoystickController(OrthographicCamera cam, int id) {
        this.cam = cam;
        this.id = id;
        background = new Texture("joystick/background.png");
        base = new Texture("joystick/base.png");
        joystick = new Texture("joystick/joystick.png");
        setStartPosition();
        boundsJoystick = new Rectangle(50, 400 - ((joystick.getHeight()/2)/2), joystick.getWidth()/2, joystick.getHeight()/2);
    }

    private void setStartPosition() {
        if (this.id == 1) {
            startPosX = 275 - this.cam.position.x;
            startPosY = 300 - this.cam.position.y;
            position = new Vector2(startPosX, startPosY);
        }
        if (this.id == 2) {
            startPosX = 500 - this.cam.position.x;
            startPosY = 300 - this.cam.position.y;
            position = new Vector2(startPosX, startPosY);
        }
    }

    public Rectangle getBounds() {
        return boundsJoystick;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getId() {
        return this.id;
    }

    public void setPosition(int movement) {
        if (movement == 0) {
            position.x = startPosX;
        }
        if (movement == 1) {
            position.x = startPosX - MAX_POS;
        }
        if (movement == 2) {
            position.x = startPosX + MAX_POS;
        }
    }
}
