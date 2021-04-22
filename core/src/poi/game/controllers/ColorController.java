package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.SoundManager;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

// Controller for ColorView
public class ColorController extends Controller {

    private GameView gameView;
    private ChangeViewController changeViewController;
    private Texture buttonPenguinBlack;
    private Texture buttonPenguinPink;
    private Texture buttonPenguinGreen;
    private Texture buttonPenguinPurp;
    private Rectangle boundsPenguinBlack1;
    private Rectangle boundsPenguinPink1;
    private Rectangle boundsPenguinGreen1;
    private Rectangle boundsPenguinPurp1;
    private Rectangle boundsPenguinBlack2;
    private Rectangle boundsPenguinPink2;
    private Rectangle boundsPenguinGreen2;
    private Rectangle boundsPenguinPurp2;
    private ButtonComponent buttonComponent;
    private Texture buttonBack;
    private Rectangle boundsBack;
    private Texture selected;
    public static int colorP1 = 0;
    public static int colorP2 = 0;

    public ColorController(GameView gameView) {
        this.gameView = gameView;
        changeViewController = Poi.getChangeViewController();
        selected = new Texture("general/selected.png");
        buttonPenguinBlack = new Texture("general/svart-pingvin.png");
        buttonPenguinPink = new Texture("general/rosa-pingvin.png");
        buttonPenguinGreen = new Texture("general/grønn-pingvin.png");
        buttonPenguinPurp = new Texture("general/lilla-pingvin.png");
        boundsPenguinBlack1 = new Rectangle(Poi.WIDTH/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        boundsPenguinPink1 = new Rectangle(Poi.WIDTH*2/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        boundsPenguinGreen1 = new Rectangle(Poi.WIDTH/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        boundsPenguinPurp1 = new Rectangle(Poi.WIDTH*2/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        boundsPenguinBlack2 = new Rectangle(Poi.WIDTH*4/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        boundsPenguinPink2 = new Rectangle(Poi.WIDTH*5/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        boundsPenguinGreen2 = new Rectangle(Poi.WIDTH*4/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        boundsPenguinPurp2 = new Rectangle(Poi.WIDTH*5/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5, selected.getWidth(), selected.getHeight());
        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = buttonComponent.getBoundsBack();
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonPenguinBlack() { return buttonPenguinBlack; }
    public Texture getButtonPenguinPink() { return buttonPenguinPink; }
    public Texture getButtonPenguinGreen() { return buttonPenguinGreen; }
    public Texture getButtonPenguinPurp() { return  buttonPenguinPurp; }
    public Texture getButtonBack() { return buttonBack; }
    public Texture getSelected() { return selected; }

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
            else if (boundsPenguinPurp1.contains(touchTransformed.x, touchTransformed.y)) {
                colorP1 = 3;
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
            else if (boundsPenguinPurp2.contains(touchTransformed.x, touchTransformed.y)) {
                colorP2 = 3;
            }
            else if (boundsBack.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new SettingsView(gameView));
            }
        }
    }

    public static int getColorP1() {
        return colorP1;
    }
    public static int getColorP2() {
        return colorP2;
    }
}
