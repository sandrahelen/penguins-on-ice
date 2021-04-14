package poi.game.views;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.PauseController;
import poi.game.controllers.ChangeViewController;
import poi.game.controllers.SettingsController;
import poi.game.models.factories.ViewFactory;

public class SettingsView extends View implements ViewFactory {

    private SettingsController controller;
    private GameView gameView;

    private Texture titleSettings;
    private Texture buttonSound;
    private Texture buttonColor;
    private Texture buttonMenu;
    private Texture buttonResume;


    public SettingsView (GameView gameView) {
        super();
        controller = new SettingsController(gameView);
        this.gameView = gameView;
        titleSettings = new Texture("general/titleSettings.png");
        buttonSound = controller.getButtonSound();
        buttonColor = controller.getButtonColor();
        buttonMenu = controller.getButtonMenu();
        buttonResume = new Texture("general/buttonResume.png");
    }

    /*@Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchTransformed = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (boundsMenu.contains(touchTransformed.x, touchTransformed.y)) {
                gameView.getPauseController().getPauseComponent().setPaused(false);
                controller.set(new MenuView(controller));
            }
            // Can only resume game if game is already paused
            else if (boundsResume.contains(touchTransformed.x, touchTransformed.y) && gameView.getPauseController().getPauseComponent().getIsPaused()) {
                // Change view to existing gameView
                controller.set(gameView);
            }
        }
    }*/


    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleSettings, Poi.WIDTH/2-titleSettings.getWidth()/2 /*5/16*/, Poi.HEIGHT - titleSettings.getHeight()*3);
        sb.draw(buttonSound, Poi.WIDTH/2-controller.getButtonWidth()/2, Poi.HEIGHT*3/6);
        sb.draw(buttonColor, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT*2/6);
        sb.draw(buttonMenu, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        // Only draw resume button if game is paused
        if (gameView.getPauseController().getPauseComponent().getIsPaused()) {
            sb.draw(buttonResume, 30,Poi.HEIGHT - 30 - buttonResume.getHeight()/2);
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
