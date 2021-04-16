package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.SettingsView;

import java.awt.*;

public class HelpController {

    private ChangeViewController changeViewController;
    private ButtonComponent buttonComponent;
    private Texture buttonBack;
    private Rectangle boundsBack;

    public HelpController() {
        changeViewController = Poi.getChangeViewController();
        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = buttonComponent.getBoundsBack();
    }
    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonBack() {
        return buttonBack;
    }
    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (Gdx.input.justTouched()) {
            if (boundsBack.contains(touchTransformed.x, touchTransformed.y)) {
                changeViewController.set(new MenuView());
            }
        }
    }
}
