package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.MenuView;

public class HighscoreController {

    private ChangeViewController changeViewController;

    private Texture buttonMenu;
    private final Rectangle boundsMenu;
    private GameView gameView;

    private ButtonComponent buttonComponent;

    public HighscoreController(){
        this.gameView = gameView;
        changeViewController = Poi.getChangeViewController();
        buttonComponent = new ButtonComponent();
        buttonMenu = buttonComponent.getButtonMenu();
        boundsMenu = buttonComponent.getBoundsMenu();
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonMenu() { return buttonMenu; }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if(Gdx.input.justTouched()){
            if (boundsMenu.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new MenuView(changeViewController));
            }
        }
    }

}
