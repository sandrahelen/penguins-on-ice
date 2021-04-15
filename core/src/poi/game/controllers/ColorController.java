package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;


public class ColorController {

    private GameView gameView;
    private ChangeViewController changeViewController;
    private ButtonComponent buttonComponent;
    private Texture buttonBack;
    private Rectangle boundsBack;
    private SoundController soundController;

    public ColorController(GameView gameView) {
        this.gameView = gameView;
        changeViewController = Poi.getChangeViewController();
        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = buttonComponent.getBoundsBack();
        soundController = Poi.getSoundController();
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonBack() { return buttonBack; }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()){
            // Checks if buttons are pressed before changing view
            if (boundsBack.contains(touchTransformed.x, touchTransformed.y)) {
                soundController.play();
                changeViewController.set(new SettingsView(gameView));
            }
        }
    }
}
