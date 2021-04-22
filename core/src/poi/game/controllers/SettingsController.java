package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.ButtonComponent;
import poi.game.views.ColorView;
import poi.game.views.GameView;
import poi.game.views.MapChangerView;
import poi.game.views.MenuView;

// Controller for SettingsView
public class SettingsController extends Controller {

    private Texture buttonSound;
    private Texture buttonColor;
    private Texture buttonMap;
    private Texture buttonMenu;
    private Texture buttonQuit;
    private Texture buttonResume;
    private final Rectangle boundsSound;
    private final Rectangle boundsColor;
    private final Rectangle boundsMap;
    private final Rectangle boundsMenu;
    private final Rectangle boundsResume;
    private ButtonComponent buttonComponent;

    private GameView gameView;


    public SettingsController(GameView gameView){
        this.gameView = gameView;
        buttonComponent = new ButtonComponent();
        buttonSound = buttonComponent.getButtonSound();
        buttonColor = buttonComponent.getButtonColor();
        buttonMap = buttonComponent.getButtonMap();
        buttonMenu = buttonComponent.getButtonMenu();
        buttonQuit = buttonComponent.getButtonQuit();
        boundsSound = buttonComponent.getBoundsSound();
        boundsColor = buttonComponent.getBoundsColor();
        boundsMap = buttonComponent.getBoundsMap();
        boundsMenu = buttonComponent.getBoundsMenu();
        buttonResume = new Texture("general/buttonResume.png");
        boundsResume = new Rectangle(30, Poi.HEIGHT - 30 - buttonResume.getHeight()/2, buttonResume.getWidth(), buttonResume.getHeight());
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonSound() { return buttonSound; }
    public Texture getButtonColor() { return buttonColor; }
    public Texture getButtonMap() { return buttonMap; }
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
            else if (boundsMap.contains(touchTransformed.x, touchTransformed.y) && !gameView.getPauseController().getIsPaused()) {
                changeViewController.set(new MapChangerView(gameView));
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
