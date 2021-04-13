package poi.game.views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.controllers.ChangeViewController;
import poi.game.controllers.MenuController;
import poi.game.Poi;
import poi.game.models.factories.ViewFactory;

public class MenuView extends View implements ViewFactory{

    private Texture titlePoI;
    private Texture penguin;
    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;
    private Rectangle boundsPlay;
    private Rectangle boundsHighscore;
    private Rectangle boundsSettings;

    public MenuView(ChangeViewController controller) {
        super(controller);
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);

        titlePoI = new Texture("general/titlePoI.png");
        penguin = new Texture("general/pingvin.png");
        buttonPlay = new Texture("general/buttonPlay.png");
        buttonHighscore = new Texture("general/buttonHighscore.png");
        buttonSettings = new Texture("general/buttonSettings.png");
        boundsPlay = new Rectangle(Poi.WIDTH/2-buttonPlay.getWidth()/2, Poi.HEIGHT*3/6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsHighscore = new Rectangle(Poi.WIDTH/2-buttonHighscore.getWidth()/2,Poi.HEIGHT*2/6, buttonHighscore.getWidth(), buttonHighscore.getHeight());
        boundsSettings = new Rectangle(Poi.WIDTH/2-buttonSettings.getWidth()/2,Poi.HEIGHT/6, buttonSettings.getWidth(), buttonSettings.getHeight());
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchTransformed = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            // Checks if buttons are pressed before changing view
            if (boundsPlay.contains(touchTransformed.x, touchTransformed.y)) {
                controller.set(new GameView(controller));
            }
            else if (boundsHighscore.contains(touchTransformed.x, touchTransformed.y)) {
                controller.set(new HighscoreView(controller));
            }
            else if (boundsSettings.contains(touchTransformed.x, touchTransformed.y)) {
                controller.set(new SettingsView(controller, new GameView(controller)));
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
        sb.draw(titlePoI, Poi.WIDTH/8, Poi.HEIGHT - titlePoI.getHeight()*2);
        sb.draw(penguin, Poi.WIDTH/3, Poi.HEIGHT*6/8);
        sb.draw(buttonPlay, Poi.WIDTH/2-buttonPlay.getWidth()/2, Poi.HEIGHT*3/6);
        sb.draw(buttonHighscore, Poi.WIDTH/2-buttonHighscore.getWidth()/2,Poi.HEIGHT*2/6);
        sb.draw(buttonSettings, Poi.WIDTH/2-boundsSettings.getWidth()/2,Poi.HEIGHT/6);
        sb.end();

    }

    public void dispose() {
        titlePoI.dispose();
        buttonPlay.dispose();
        buttonHighscore.dispose();
        buttonSettings.dispose();
    }
}
