package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.SoundManager;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.ColorView;
import poi.game.views.GameView;
import poi.game.views.MenuView;

// Controller for SettingsView
public class SettingsController {

    private ChangeViewController changeViewController;

    private Texture buttonSound;
    private Texture buttonColor;
    private Texture buttonMenu;
    private Texture buttonQuit;
    private Texture buttonResume;
    private final Rectangle boundsSound;
    private final Rectangle boundsColor;
    private final Rectangle boundsMenu;
    private final Rectangle boundsQuit;
    private final Rectangle boundsResume;

    private GameView gameView;

    private ButtonComponent buttonComponent;
    SoundManager soundController;

    public SettingsController(GameView gameView){
        this.gameView = gameView;
        changeViewController = Poi.getChangeViewController();
        buttonComponent = new ButtonComponent();
        buttonSound = buttonComponent.getButtonSound();
        buttonColor = buttonComponent.getButtonColor();
        buttonMenu = buttonComponent.getButtonMenu();
        buttonQuit = buttonComponent.getButtonQuit();
        boundsSound = buttonComponent.getBoundsSound();
        boundsColor = buttonComponent.getBoundsColor();
        boundsMenu = buttonComponent.getBoundsMenu();
        boundsQuit = buttonComponent.getBoundsQuit();
        buttonResume = new Texture("general/buttonResume.png");
        boundsResume = new Rectangle(30, Poi.HEIGHT - 30 - buttonResume.getHeight()/2, buttonResume.getWidth(), buttonResume.getHeight());
        soundController = Poi.getSoundController();
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonSound() { return buttonSound; }
    public Texture getButtonColor() { return buttonColor; }
    public Texture getButtonMenu() { return buttonMenu; }
    public Texture getButtonQuit() { return buttonQuit; }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()){
            // Checks if buttons are pressed before changing view
            if (boundsSound.contains(touchTransformed.x, touchTransformed.y)) {
                soundController.mute();
            }
            else if (boundsColor.contains(touchTransformed.x, touchTransformed.y) && !gameView.getPauseController().getIsPaused()) {
                changeViewController.set(new ColorView(gameView));
            }
            else if (boundsMenu.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new MenuView());
            }
            // Can only resume game if game is already paused
            else if (boundsResume.contains(touchTransformed.x, touchTransformed.y) && gameView.getPauseController().getIsPaused()) {
                // Change view to existing gameView
                changeViewController.set(gameView);
            }
        }
    }
}
