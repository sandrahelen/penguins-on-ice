package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.buttons.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

import java.util.ArrayList;

// Controller for ColorView
public class ColorController extends Controller {

    private GameView gameView;
    private ButtonComponent buttonComponent;
    private Rectangle boundsLeftType0;
    private Rectangle boundsRightType0;
    private Rectangle boundsLeftType1;
    private Rectangle boundsRightType1;
    private Rectangle boundsLeftType2;
    private Rectangle boundsRightType2;
    private Rectangle boundsLeftType3;
    private Rectangle boundsRightType3;

    private Rectangle boundsPenguinBlack1;
    private Rectangle boundsPenguinPink1;
    private Rectangle boundsPenguinGreen1;
    private Rectangle boundsPenguinPurp1;
    private Rectangle boundsPenguinGrey1;
    private Rectangle boundsPenguinBlack2;
    private Rectangle boundsPenguinPink2;
    private Rectangle boundsPenguinGreen2;
    private Rectangle boundsPenguinPurp2;
    private Rectangle boundsPenguinGrey2;
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
    private ArrayList<Texture> colorType2 = new ArrayList<>();
    private ArrayList<Texture> colorType3 = new ArrayList<>();

    public ColorController(GameView gameView) {
        this.gameView = gameView;
        changeViewController = Poi.getChangeViewController();
        selectedColor = new Texture("general/selected.png");
        selectedPenguin = new Texture("general/selected.png");
        setTextures();

        boundsLeftType0 = new Rectangle(Poi.WIDTH/10 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsLeftType1 = new Rectangle(Poi.WIDTH*((float)1.75)/10 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsLeftType2 = new Rectangle(Poi.WIDTH*((float)2.5)/10 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsLeftType3 = new Rectangle(Poi.WIDTH*((float)3.25)/10 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsRightType0 = new Rectangle(Poi.WIDTH*4/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsRightType1 = new Rectangle(Poi.WIDTH*((float)4.45)/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6- selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsRightType2 = new Rectangle(Poi.WIDTH*((float)4.9)/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6- selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        boundsRightType3 = new Rectangle(Poi.WIDTH*((float)5.35)/6 - selectedPenguin.getWidth()/3,
                Poi.HEIGHT-Poi.HEIGHT/6- selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());

        boundsPenguinBlack1 = new Rectangle(Poi.WIDTH/10 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPink1 = new Rectangle(Poi.WIDTH*2/10 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinGreen1 = new Rectangle(Poi.WIDTH/10 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPurp1 = new Rectangle(Poi.WIDTH*2/10 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinGrey1 = new Rectangle(Poi.WIDTH*3/10 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinBlack2 = new Rectangle(Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPink2 = new Rectangle(Poi.WIDTH*((float)4.65)/6 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinGreen2 = new Rectangle(Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinPurp2 = new Rectangle(Poi.WIDTH*((float)4.65)/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());
        boundsPenguinGrey2 = new Rectangle(Poi.WIDTH*((float)5.3)/6 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5, selectedColor.getWidth(), selectedColor.getHeight());

        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = buttonComponent.getBoundsBack();
    }

    private void setTextures() {
        penguinTypes.add(new Texture("penguin/svart-pingvin.png"));
        penguinTypes.add(new Texture("penguin/ny-pingvin-svart.png"));
        penguinTypes.add(new Texture("penguin/svart-type3.png"));
        penguinTypes.add(new Texture("penguin/svart-type4.png"));

        colorType0.add(new Texture("penguin/svart-pingvin.png"));
        colorType0.add(new Texture("penguin/rosa-pingvin.png"));
        colorType0.add(new Texture("penguin/grønn-pingvin.png"));
        colorType0.add(new Texture("penguin/lilla-pingvin.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-svart.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-rosa.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-grønn.png"));
        colorType1.add(new Texture("penguin/ny-pingvin-lilla.png"));
        colorType2.add(new Texture("penguin/svart-type3.png"));
        colorType2.add(new Texture("penguin/rosa-type3.png"));
        colorType2.add(new Texture("penguin/grønn-type3.png"));
        colorType2.add(new Texture("penguin/lilla-type3.png"));
        colorType2.add(new Texture("penguin/grå-type3.png"));
        colorType3.add(new Texture("penguin/svart-type4.png"));
        colorType3.add(new Texture("penguin/rosa-type4.png"));
        colorType3.add(new Texture("penguin/grønn-type4.png"));
        colorType3.add(new Texture("penguin/lilla-type4.png"));
        colorType3.add(new Texture("penguin/grå-type4.png"));

        penguinColors.add(colorType0);
        penguinColors.add(colorType1);
        penguinColors.add(colorType2);
        penguinColors.add(colorType3);
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
            if (boundsLeftType0.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP1 = 0;
                colorP1 = 0;
            }
            else if (boundsLeftType1.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP1 = 1;
                colorP1 = 0;
            }
            else if (boundsLeftType2.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP1 = 2;
                colorP1 = 0;
            }
            else if (boundsLeftType3.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP1 = 3;
                colorP1 = 0;
            }
            if (boundsRightType0.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP2 = 0;
                colorP2 = 0;
            }
            else if (boundsRightType1.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP2 = 1;
                colorP2 = 0;
            }
            else if (boundsRightType2.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP2 = 2;
                colorP2 = 0;
            }
            else if (boundsRightType3.contains(touchTransformed.x, touchTransformed.y)) {
                penguinTypeP2 = 3;
                colorP2 = 0;
            }
            if (boundsPenguinBlack1.contains(touchTransformed.x, touchTransformed.y)) {
                colorP1 = 0;
            }
            else if (boundsPenguinPink1.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP1).size() >= 2) {
                colorP1 = 1;
            }
            else if (boundsPenguinGreen1.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP1).size() >= 3) {
                colorP1 = 2;
            }
            else if (boundsPenguinPurp1.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP1).size() >= 4) {
                colorP1 = 3;
            }
            else if (boundsPenguinGrey1.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP1).size() >= 5) {
                colorP1 = 4;
            }
            else if (boundsPenguinBlack2.contains(touchTransformed.x, touchTransformed.y)) {
                colorP2 = 0;
            }
            else if (boundsPenguinPink2.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP2).size() >= 2) {
                colorP2 = 1;
            }
            else if (boundsPenguinGreen2.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP2).size() >= 3) {
                colorP2 = 2;
            }
            else if (boundsPenguinPurp2.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP2).size() >= 4) {
                colorP2 = 3;
            }
            else if (boundsPenguinGrey2.contains(touchTransformed.x, touchTransformed.y) && penguinColors.get(penguinTypeP2).size() >= 5) {
                colorP2 = 4;
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
