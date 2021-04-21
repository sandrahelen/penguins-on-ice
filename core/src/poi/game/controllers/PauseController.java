package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.entityComponents.PauseComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

// Controller to pause the game
public class PauseController {

    private PauseComponent pauseComponent;
    private ChangeViewController changeViewController;
    private Texture buttonPause;

    public  PauseController(){

        pauseComponent = new PauseComponent();
        changeViewController = Poi.getChangeViewController();
        buttonPause = pauseComponent.getButtonPause();
    }

    public void handleInput(GameView gameView) {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (Gdx.input.justTouched()) {
            if (pauseComponent.getBoundsPause().contains(touchTransformed.x, touchTransformed.y)) {
                pauseComponent.setPaused(true);
                // Change view to SettingsView with this (existing gameView) because then the player do not need to start new game if resumed
                changeViewController.set(new SettingsView(gameView));
            }

        }
    }

    public PauseComponent getPauseComponent(){return pauseComponent;}
    public Texture getButtonPause() {
        return buttonPause;
    }
    public boolean getIsPaused() {
        return pauseComponent.getIsPaused();
    }
}
