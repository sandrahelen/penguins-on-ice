package poi.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.SettingsController;
import poi.game.models.factories.ViewFactory;

// View for settings
public class SettingsView extends View implements ViewFactory {

    private SettingsController controller;
    private GameView gameView;
    private Texture titleSettings;
    private Texture buttonSound;
    private Texture buttonColor;
    private Texture buttonMap;
    private Texture buttonMenu;
    private Texture buttonQuit;
    private Texture buttonResume;


    public SettingsView (GameView gameView) {
        super();
        controller = new SettingsController(gameView);
        this.gameView = gameView;
        titleSettings = new Texture("general/titleSettings.png");
        buttonSound = controller.getButtonSound();
        buttonColor = controller.getButtonColor();
        buttonMap = controller.getButtonMap();
        buttonMenu = controller.getButtonMenu();
        buttonQuit = controller.getButtonQuit();
        buttonResume = new Texture("general/buttonResume.png");
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleSettings, Poi.WIDTH/2-titleSettings.getWidth()/2, Poi.HEIGHT - titleSettings.getHeight()*3/2);
        sb.draw(buttonSound, Poi.WIDTH/2-controller.getButtonWidth()/2, Poi.HEIGHT*10/16);


        // Only draw resume button if game is paused
        if (gameView.getPauseController().getPauseComponent().getIsPaused()) {
            sb.draw(buttonResume, 30,Poi.HEIGHT - 30 - buttonResume.getHeight()/2);
            sb.draw(buttonQuit, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        }
        else {
            sb.draw(buttonColor, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT*7/16);
            sb.draw(buttonMap, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT*4/16);
            sb.draw(buttonMenu, (int)(Poi.WIDTH/2-(controller.getButtonWidth() + 0.1)/2),Poi.HEIGHT*1/16);
        }
        sb.end();
    }

    public void dispose() {
        titleSettings.dispose();
        buttonSound.dispose();
        buttonColor.dispose();
        buttonMenu.dispose();
    }
}
