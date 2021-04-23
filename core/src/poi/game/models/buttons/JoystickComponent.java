package poi.game.models.buttons;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class JoystickComponent {

    public Texture joystickBase;
    public Texture joystick;
    private Rectangle boundsJoystick;
    private boolean joystickTouched = false;
    private boolean moveLeft = false;
    private float posX;
    private final int MAX_POS = 25;

    public JoystickComponent(float startPosX, float startPosY) {
        joystickBase = new Texture("joystick/base.png");
        joystick = new Texture("joystick/joystick.png");
        boundsJoystick = new Rectangle(startPosX - 15, startPosY, (float) joystickBase.getWidth()/2, (float) joystick.getHeight()/2);
        posX = startPosX;
    }

    public Rectangle getBoundsJoystick() {
        return boundsJoystick;
    }

    public float getPosition() {
        return posX;
    }

    public Texture getJoystickBase() {
        return joystickBase;
    }

    public Texture getJoystick() {
        return joystick;
    }

    public void setJoystickTouched(boolean joystickTouched) {
        this.joystickTouched = joystickTouched;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public boolean getJoystickTouched() {
        return this.joystickTouched;
    }

    public boolean getMoveLeft() {
        return this.moveLeft;
    }
}
