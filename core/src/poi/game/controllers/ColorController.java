package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

import java.util.ArrayList;


public class ColorController {

    private GameView gameView;
    private ChangeViewController changeViewController;
    private Texture buttonPenguinBlack;
    private Texture buttonPenguinPink;
    private Texture buttonPenguinGreen;
    private Rectangle boundsPenguinBlack1;
    private Rectangle boundsPenguinPink1;
    private Rectangle boundsPenguinGreen1;
    private Rectangle boundsPenguinBlack2;
    private Rectangle boundsPenguinPink2;
    private Rectangle boundsPenguinGreen2;
    private ButtonComponent buttonComponent;
    private Texture buttonBack;
    private Rectangle boundsBack;
    private SoundController soundController;
    private ArrayList<String> texturesP1;
    private ArrayList<String> texturesP2;
    public static int colorP1 = 0;
    public static int colorP2 = 0;

    public ColorController(GameView gameView) {
        this.gameView = gameView;
        texturesP1 = new ArrayList<String>();
        changeViewController = Poi.getChangeViewController();
        buttonPenguinBlack = new Texture("general/svart-pingvin.png");
        buttonPenguinPink = new Texture("general/rosa-pingvin.png");
        buttonPenguinGreen = new Texture("general/grønn-pingvin.png");
        boundsPenguinBlack1 = new Rectangle(Poi.WIDTH / 6, Poi.HEIGHT *6/9, buttonPenguinBlack.getWidth(), buttonPenguinBlack.getHeight());
        boundsPenguinPink1 = new Rectangle(Poi.WIDTH *2/6, Poi.HEIGHT *6/9, buttonPenguinBlack.getWidth(), buttonPenguinBlack.getHeight());
        boundsPenguinGreen1 = new Rectangle(Poi.WIDTH / 6, Poi.HEIGHT *4/9, buttonPenguinBlack.getWidth(), buttonPenguinBlack.getHeight());
        boundsPenguinBlack2 = new Rectangle(Poi.WIDTH *4/6, Poi.HEIGHT *6/9, buttonPenguinBlack.getWidth(), buttonPenguinBlack.getHeight());
        boundsPenguinPink2 = new Rectangle(Poi.WIDTH *5/6, Poi.HEIGHT *6/9, buttonPenguinBlack.getWidth(), buttonPenguinBlack.getHeight());
        boundsPenguinGreen2 = new Rectangle(Poi.WIDTH *4/6, Poi.HEIGHT *4/9, buttonPenguinBlack.getWidth(), buttonPenguinBlack.getHeight());
        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = buttonComponent.getBoundsBack();
        soundController = Poi.getSoundController();
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonPenguinBlack() { return buttonPenguinBlack; }
    public Texture getButtonPenguinPink() { return buttonPenguinPink; }
    public Texture getButtonPenguinGreen() { return buttonPenguinGreen; }
    public Texture getButtonBack() { return buttonBack; }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()){
            // Checks if buttons are pressed before changing view
            if (boundsPenguinBlack1.contains(touchTransformed.x, touchTransformed.y)) {
                colorP1 = 0;
            }
            else if (boundsPenguinPink1.contains(touchTransformed.x, touchTransformed.y)) {
                colorP1 = 1;
            }
            else if (boundsPenguinGreen1.contains(touchTransformed.x, touchTransformed.y)) {
                colorP1 = 2;
            }
            else if (boundsPenguinBlack2.contains(touchTransformed.x, touchTransformed.y)) {
                colorP2 = 0;
            }
            else if (boundsPenguinPink2.contains(touchTransformed.x, touchTransformed.y)) {
                colorP2 = 1;
            }
            else if (boundsPenguinGreen2.contains(touchTransformed.x, touchTransformed.y)) {
                colorP2 = 2;
            }
            else if (boundsBack.contains(touchTransformed.x, touchTransformed.y)) {
                soundController.play();
                changeViewController.set(new SettingsView(gameView));
            }
        }
    }
}