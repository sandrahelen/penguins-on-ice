package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

import java.util.ArrayList;

// Controller for ColorView
public class ColorController extends Controller {

    private GameView gameView;
    private ButtonComponent buttonComponent;
    private Rectangle boundsPenguinDef1;
    private Rectangle boundsPenguinDef2;
    private Rectangle boundsPenguinNew1;
    private Rectangle boundsPenguinNew2;
    private Rectangle boundsPenguinBlack1;
    private Rectangle boundsPenguinPink1;
    private Rectangle boundsPenguinGreen1;
    private Rectangle boundsPenguinPurp1;
    private Rectangle boundsPenguinBlack2;
    private Rectangle boundsPenguinPink2;
    private Rectangle boundsPenguinGreen2;
    private Rectangle boundsPenguinPurp2;
    private Texture buttonBack;
    private Rectangle boundsBack;
    private Texture selectedColor;
    private Texture selectedPenguin;
    public static int colorP1 = 0;
    public static int colorP2 = 0;
    public static int penguinTypeP1 = 0;
    public static int penguinTypeP2 = 0;
    private ArrayList<ArrayList<Texture>> penguinColors = new ArrayList<>();
    private ArrayList<Texture> penguinTypes = new ArrayList<>();
    private ArrayList<Texture> colorType0 = new ArrayList<>();
    private ArrayList<Texture> colorType1 = new ArrayList<>();

    public ColorController(GameView gameView) {
        this.gameView = gameView;
        changeViewController = Poi.getChangeViewController();
        selectedColor = new Texture("general/selected.png");
        selectedPenguin = new Texture("general/selected.png");
        setTextures();

        boundsPenguinDef1 = new Rectangle(Poi.WIDTH/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsPenguinNew1 = new Rectangle(Poi.WIDTH*((float)1.5)/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsPenguinDef2 = new Rectangle(Poi.WIDTH*((float)4.5)/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsPenguinNew2 = new Rectangle(Poi.WIDTH*5/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());

        boundsPenguinBlack1 = new Rectangle(Poi.WIDTH/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPink1 = new Rectangle(Poi.WIDTH*2/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinGreen1 = new Rectangle(Poi.WIDTH/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPurp1 = new Rectangle(Poi.WIDTH*2/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinBlack2 = new Rectangle(Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPink2 = new Rectangle(Poi.WIDTH*5/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinGreen2 = new Rectangle(Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPurp2 = new Rectangle(Poi.WIDTH*5/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = buttonComponent.getBoundsBack();
    }

    private void setTextures() {
        penguinTypes.add(new Texture("penguin/svart-pingvin.png"));
        penguinTypes.add(new Texture("penguin/ny-pingvin-svart.png"));

        colorType0.add(new Texture("penguin/svart-pingvin.png"));
        colorType0.add(new Texture("penguin/rosa-pingvin.png"));
        colorType0.add(new Texture("penguin/grønn-pingvin.png"));
        colorType0.add(new Texture("penguin/lilla-pingvin.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-svart.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-rosa.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-grønn.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-lilla.png"));

        penguinColors.add(colorType0);
        penguinColors.add(colorType1);
    }

    public int getButtonWidth() {return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }


    public ArrayList<ArrayList<Texture>> getButtonPenguin() { return penguinColors; }
    public ArrayList<Texture> getButtonPenguinType() { return penguinTypes; }
    public Texture getButtonBack() { return buttonBack; }
    public Texture getSelectedColor() { return selectedColor; }
    public Texture getSelectedPenguin() { return selectedPenguin; }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()){
            // Checks if buttons are pressed before changing view
            if (boundsPenguinDef1.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP1 = 0;
            }
            else if (boundsPenguinNew1.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP1 = 1;
            }
            if (boundsPenguinDef2.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP2 = 0;
            }
            else if (boundsPenguinNew2.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP2 = 1;
            }
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
