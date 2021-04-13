package poi.game.views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.controllers.MenuController;
import poi.game.models.factories.ViewFactory;

public class SettingsView extends View implements ViewFactory {

    private Texture titleSettings;
    private Texture buttonSound;
    private Texture buttonColor;
    private Texture buttonMenu;
    private Texture buttonResume;
    private Rectangle boundsSound;
    private Rectangle boundsColor;
    private Rectangle boundsMenu;
    private Rectangle boundsResume;
    private GameView gameView;

    public SettingsView (MenuController controller, GameView gameView) {
        super(controller);
        this.gameView = gameView;
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
<<<<<<< core/src/poi/game/views/SettingsView.java
        titleSettings = new Texture("general/titleSettings.png");
        buttonSound = new Texture("general/buttonSound.png");
        buttonColor = new Texture("general/buttonColor.png");
        buttonMenu = new Texture("general/buttonMenu.png");
        buttonResume = new Texture("general/buttonResume.png");
        boundsSound = new Rectangle(Poi.WIDTH/2-buttonSound.getWidth()/2, Poi.HEIGHT*3/6, buttonSound.getWidth(), buttonSound.getHeight());
        boundsColor = new Rectangle(Poi.WIDTH/2-buttonColor.getWidth()/2,Poi.HEIGHT*2/6, buttonColor.getWidth(), buttonColor.getHeight());
        boundsMenu = new Rectangle(Poi.WIDTH/2-buttonMenu.getWidth()/2,Poi.HEIGHT/6, buttonMenu.getWidth(), buttonMenu.getHeight());
        boundsResume = new Rectangle(20, 30 - buttonResume.getHeight()/2, buttonResume.getWidth(), buttonResume.getHeight());
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchTransformed = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (boundsMenu.contains(touchTransformed.x, touchTransformed.y)) {
                gameView.setIsPaused(false);
                controller.set(new MenuView(controller));
            }
            // Can only resume game if game is already paused
            else if (boundsResume.contains(touchTransformed.x, touchTransformed.y) && gameView.isPaused()) {
                // Change view to existing gameView
                controller.set(gameView);
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleSettings, Poi.WIDTH/2-titleSettings.getWidth()/2 /*5/16*/, Poi.HEIGHT - titleSettings.getHeight()*3);
        sb.draw(buttonSound, Poi.WIDTH/2-buttonSound.getWidth()/2, Poi.HEIGHT*3/6);
        sb.draw(buttonColor, Poi.WIDTH/2-buttonColor.getWidth()/2,Poi.HEIGHT*2/6);
        sb.draw(buttonMenu, Poi.WIDTH/2-buttonMenu.getWidth()/2,Poi.HEIGHT/6);

        // Only draw resume button if game is paused
        if (gameView.isPaused()) {
            sb.draw(buttonResume, 20,Poi.HEIGHT - 30 - buttonResume.getHeight()/2);
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
