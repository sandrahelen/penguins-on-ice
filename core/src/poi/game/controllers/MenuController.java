package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.HighscoreView;
import poi.game.views.SettingsView;

public class MenuController {

    private ChangeViewController changeViewController;

    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;
    private final Rectangle boundsPlay;
    private final Rectangle boundsHighscore;
    private final Rectangle boundsSettings;
    Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

    private ButtonComponent buttonComponent;

    public MenuController(){
        changeViewController = Poi.getController();
        buttonComponent = new ButtonComponent();
        buttonPlay = buttonComponent.getButtonPlay();
        buttonHighscore = buttonComponent.getButtonHighscore();
        buttonSettings = buttonComponent.getButtonSettings();
        boundsPlay = buttonComponent.getBoundsPlay();
        boundsHighscore = buttonComponent.getBoundsHighscore();
        boundsSettings = buttonComponent.getBoundsSettings();
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonPlay() { return buttonPlay; }
    public Texture getButtonHighscore() { return buttonHighscore; }
    public Texture getButtonSettings() { return buttonSettings; }

    public void handleInput() {
        if(Gdx.input.justTouched()){
            // Checks if buttons are pressed before changing view
            if (boundsPlay.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new GameView(changeViewController));
            }
            else if (boundsHighscore.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new HighscoreView(changeViewController));
            }
            else if (boundsSettings.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new SettingsView(changeViewController, new GameView(changeViewController)));
            }
        }
        if (boundsPlay.contains(touchTransformed.x, touchTransformed.y)) {
            Gdx.app.log("Button", "Play");
        }
    }
}
