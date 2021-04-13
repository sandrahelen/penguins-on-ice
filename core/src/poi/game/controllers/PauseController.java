package poi.game.controllers;

import com.badlogic.gdx.Gdx;

import poi.game.models.entityComponents.PauseComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

public class PauseController {

    private PauseComponent pauseComponent;

    public  PauseController(){
        pauseComponent = new PauseComponent();
    }

    public void handleInput(MenuController controller, GameView gameView) {
        if (Gdx.input.justTouched()) {

            if (pauseComponent.getBoundsPause().contains(Gdx.input.getX(), Gdx.input.getY())) {
                pauseComponent.setPaused(true);
                // Change view to SettingsView with this (existing gameView) because then the player do not need to start new game if resumed
                controller.set(new SettingsView(controller, gameView));
            }

        }
    }

    public PauseComponent getPauseComponent(){return pauseComponent;}
}
