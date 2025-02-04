package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import poi.game.models.clientServer.Datahandler;

import poi.game.Poi;
import poi.game.models.buttons.ButtonComponent;
import poi.game.views.HighscoreView;

// Controller for EndGameView
public class EndGameController extends Controller {

    private Texture buttonSubmit;
    private Rectangle boundsSubmit;
    private String username = "";
    private int endTime = 0;
    Datahandler datahandler;
    private ButtonComponent buttonComponent;

    public EndGameController(int endTime) {
        this.endTime = endTime;
        buttonComponent = new ButtonComponent();
        buttonSubmit = buttonComponent.getButtonSubmit();
        boundsSubmit = buttonComponent.getBoundsSubmit();

        // Linking to Firebase database
        datahandler = changeViewController.getDatahandler();
        changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
        soundController.stop();
    }
    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }

    public Texture getButtonSubmit() { return buttonSubmit; }

    public void handleInput(String input) {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()){
            if (boundsSubmit.contains(touchTransformed.x, touchTransformed.y)) {
                username = input;
                if (username.isEmpty()) {   // Not possible to enter an empty string
                    return;
                }
                changeViewController.getLeaderboard().submitScore(username, endTime);     // Submits the score to Firebase database
                changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
                Gdx.input.setOnscreenKeyboardVisible(false);    // Disable displaying keyboard
                changeViewController.set(new HighscoreView());
            }
        }
    }
}
