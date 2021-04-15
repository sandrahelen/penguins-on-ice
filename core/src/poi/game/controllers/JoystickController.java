package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import poi.game.Poi;
import poi.game.models.entityComponents.JoystickComponent;

import java.util.HashMap;
import java.util.Map;

public class JoystickController {
    public JoystickComponent joystick1;
    public JoystickComponent joystick2;
    private Vector3 touchPos;
    private Map<Integer, Float> touches = new HashMap<>();

    public JoystickController() {
        touchPos = new Vector3();
        joystick1 = new JoystickComponent(35, 45);
        joystick2 = new JoystickComponent(555,45);

        for (int i=0; i<5; i++) {   // Adding possible fingers to Hashmap
            touches.put(i, touchPos.x);
            touches.put(i, touchPos.y);
        }
    }

    public void handleInput(){
        joystick1.setJoystickTouched(false);
        joystick2.setJoystickTouched(false);

        for (int i = 0; i < 5; i++) {
            if (Gdx.input.isTouched(i)) {
                Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                touchPos.set(touchTransformed.x, touchTransformed.y, 0);

                if (joystick1.getBoundsJoystick().contains(touchPos.x, touchPos.y)) {
                    joystick1.setJoystickTouched(true);
                    if (touchPos.x < joystick1.getPosition() + (joystick1.getBoundsJoystick().getWidth() / 2)) {
                        joystick1.setMoveLeft(true);
                    }
                    else if (touchPos.x > joystick1.getPosition() + (joystick1.getBoundsJoystick().getWidth() / 2)) {
                        joystick1.setMoveLeft(false);
                    }
                    System.out.println("Joystick1 touched");
                }
                if (joystick2.getBoundsJoystick().contains(touchTransformed.x, touchTransformed.y)) {
                    joystick2.setJoystickTouched(true);
                    if (touchPos.x < joystick2.getPosition() + (joystick2.getBoundsJoystick().getWidth() / 2)) {
                        joystick2.setMoveLeft(true);
                    }
                    else if (touchPos.x > joystick2.getPosition() + (joystick2.getBoundsJoystick().getWidth() / 2)) {
                        joystick2.setMoveLeft(false);
                    }
                    System.out.println("Joystick2 touched");
                }
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
