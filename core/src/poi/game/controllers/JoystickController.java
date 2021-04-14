package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.JoystickComponent;

public class JoystickController {
    public JoystickComponent joystick1;
    public JoystickComponent joystick2;

    public JoystickController() {
        joystick1 = new JoystickComponent(35, 265);
        joystick2 = new JoystickComponent(555,265);
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            if (joystick1.getBoundsJoystick().contains(Gdx.input.getX(), Gdx.input.getY())) {
                System.out.println("Joystick1 touched");
            }
            else if (joystick2.getBoundsJoystick().contains(Gdx.input.getX(), Gdx.input.getY())) {
                System.out.println("Joystick2 touched");
            }
            else {
                System.out.println("Joystick NOT touched");
            }
        }
    }

    public JoystickComponent getJoystick1() {
        return joystick1;
    }

    public JoystickComponent getJoystick2() {
        return joystick2;
    }

    /*
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
     */
}
