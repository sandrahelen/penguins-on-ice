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

        // Adding possible fingers to Hashmap
        for (int i=0; i<5; i++) {
            touches.put(i, touchPos.x);
            touches.put(i, touchPos.y);
        }
    }

    public void handleInput(){
        // Joysticks are not touched
        joystick1.setJoystickTouched(false);
        joystick2.setJoystickTouched(false);

        for (int i = 0; i < 5; i++) {
            if (Gdx.input.isTouched(i)) {
                Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                touchPos.set(touchTransformed.x, touchTransformed.y, 0);

                // Joystick1 is touched
                if (joystick1.getBoundsJoystick().contains(touchPos.x, touchPos.y)) {
                    joystick1.setJoystickTouched(true);

                    // Move joystick1 left
                    if (touchPos.x < joystick1.getPosition() + (joystick1.getBoundsJoystick().getWidth() / 2)) {
                        joystick1.setMoveLeft(true);
                    }
                    // Move joystick1 right
                    else if (touchPos.x > joystick1.getPosition() + (joystick1.getBoundsJoystick().getWidth() / 2)) {
                        joystick1.setMoveLeft(false);
                    }
                }
                // Joystick2 is touched
                else if (joystick2.getBoundsJoystick().contains(touchPos.x, touchPos.y)) {
                    joystick2.setJoystickTouched(true);

                    // Move joystick2 left
                    if (touchPos.x < joystick2.getPosition() + (joystick2.getBoundsJoystick().getWidth() / 2)) {
                        joystick2.setMoveLeft(true);
                    }
                    // Move joystick2 right
                    else if (touchPos.x > joystick2.getPosition() + (joystick2.getBoundsJoystick().getWidth() / 2)) {
                        joystick2.setMoveLeft(false);
                    }
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
}
