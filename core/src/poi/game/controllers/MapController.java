package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

public class MapController extends Controller {

    private GameView gameView;
    private Texture buttonMap1;
    private Texture buttonMap2;
    private Texture selected;
    private Rectangle boundsMap1;
    private Rectangle boundsMap2;
    private ButtonComponent buttonComponent;
    private Texture buttonBack;
    private Rectangle boundsBack;
    public String mapLocation;


    public MapController(GameView gameView) {
        this.gameView = gameView;
        buttonMap1 = new Texture("general/Map1.png");
        buttonMap2 = new Texture("general/Map2.png");
        selected = new Texture("general/selectedMap.png");
        boundsMap1 = new Rectangle(Poi.WIDTH*3/16, Poi.HEIGHT*4/9, selected.getWidth(), selected.getHeight());
        boundsMap2 = new Rectangle(Poi.WIDTH*9/16, Poi.HEIGHT*4/9, selected.getWidth(), selected.getHeight());
        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = buttonComponent.getBoundsBack();
    }
    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonMap1() { return buttonMap1; }
    public Texture getButtonMap2() { return buttonMap2; }
    public Texture getButtonBack() { return buttonBack; }
    public Texture getSelected() { return selected; }
    public String getMapLocation() { return mapLocation; }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()) {
            if (boundsMap1.contains(touchTransformed.x, touchTransformed.y)) {
                Poi.setMapLocation("Map/Map1.tmx");
            } else if (boundsMap2.contains(touchTransformed.x, touchTransformed.y)) {
                Poi.setMapLocation("Map/Map2.tmx");
            } else if (boundsBack.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new SettingsView(gameView));
            }
        }
    }
}
