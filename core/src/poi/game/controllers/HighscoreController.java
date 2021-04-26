package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.buttons.ButtonComponent;
import poi.game.views.MenuView;

// Controller for highscore
public class HighscoreController extends Controller {

    private Texture buttonMenu;
    private final Rectangle boundsMenu;
    private ButtonComponent buttonComponent;

    public HighscoreController(){
        buttonComponent = new ButtonComponent();
        buttonMenu = buttonComponent.getButtonMenu();
        boundsMenu = buttonComponent.getBoundsMenu();
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }

    public Texture getButtonMenu() { return buttonMenu; }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()){
            if (boundsMenu.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new MenuView());
            }
        }
    }
}
