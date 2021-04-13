package poi.game.controllers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entitySystems.MovementSystem;

public class GameController {
    public final int MAX_POS = 25;
    public Texture background;
    public Texture base;
    public Texture joystick;
    public final float startPosX = 275;
    public final float startPosY = 175;
    public Vector2 position;

    public GameController (float x, float y) {
        background = new Texture("joystick/background.png");
        base = new Texture("joystick/base.png");
        joystick = new Texture("joystick/joystick.png");
        position = new Vector2(x, y);
    }

    public Vector2 getPosition() {
        return position;
    }

    /*public movement() {
        if (MovementSystem.touch == 0) {
            position.x
        }
    }
    public void movement() {
        if (touchX >= position.x - (MAX_POS + startPosX)) {
            position.x = position.x - (MAX_POS + startPosX);
        }
        else if (touchX <= position.x - (startPosX - MAX_POS)) {
            position.x = position.x - (startPosX - MAX_POS);
        }
        else {
            position.x += position.x + touchX;
        }
    }*/
}
