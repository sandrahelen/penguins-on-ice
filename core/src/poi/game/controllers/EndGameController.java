package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

import poi.game.Datahandler;
import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.models.entityComponents.TextFieldComponent;
import poi.game.views.HighscoreView;

public class EndGameController {
    private ChangeViewController changeViewController;
    private ButtonComponent buttonComponent;
    private Texture buttonSubmit;
    private Rectangle boundsSubmit;
    private TextFieldComponent textfield;

    public EndGameController() {
        changeViewController = Poi.getChangeViewController();
        buttonComponent = new ButtonComponent();
        textfield = new TextFieldComponent();
        textfield.setPosition(Poi.WIDTH/2-textfield.getTextBox().getWidth()/2+20, Poi.HEIGHT/2);
        buttonSubmit = buttonComponent.getButtonSubmit();
        boundsSubmit = buttonComponent.getBoundsSubmit();
    }

    public void handleInput(String name, int time, Datahandler data) {
        if (Gdx.input.justTouched()) {
            Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (textfield.getBoundsTextBox().contains(touchTransformed.x, touchTransformed.y)) {
                // TODO Display keyboard when click

                /* // Not working
                stage.setKeyboardFocus(textfield);
                Gdx.input.setOnscreenKeyboardVisible(true);
                */

                System.out.println("Keyboard?");
            }
            if (boundsSubmit.contains(touchTransformed.x, touchTransformed.y)) {
                //username = textfield.getText();
                //endTime = new Random().nextInt(101);    // Placeholder for time-score, random int between 0-100
                changeViewController.getLeaderboard().submitScore(name, time);     // Submits the score to Firebase database
                changeViewController.getLeaderboard().setOnValueChangedListener(data);
                Gdx.input.setOnscreenKeyboardVisible(false);    // Disable displaying keyboard
                changeViewController.set(new HighscoreView());
            }
            // Not possible to remove keyboard if no written text
            if (!name.isEmpty()) {
                // Try to unfocus instead? Possibly removing keyboard?
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        }
    }

    public TextFieldComponent getTextfield() {
        return textfield;
    }
    public void drawTextField() {
        textfield.draw();
    }
    public void disposeTextField() {
        textfield.dispose();
    }

    public int getButtonWidth() {
        return buttonComponent.getButtonWidth();
    }
    public int getButtonHeight() {
        return buttonComponent.getButtonHeight();
    }
    public Texture getButtonSubmit() {
        return buttonSubmit;
    }
    public Rectangle getBoundsSubmit() {
        return boundsSubmit;
    }
}
