package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.controllers.ChangeViewController;
import poi.game.controllers.MenuController;
import poi.game.Poi;
import poi.game.models.factories.ViewFactory;

public class MenuView extends View implements ViewFactory{

    private MenuController controller;

    private Texture titlePoI;
    private Texture penguin;
    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;

    public MenuView() {
        super();
        controller = new MenuController();
        titlePoI = new Texture("general/titlePoI.png");
        penguin = new Texture("general/svart-pingvin.png");
        buttonPlay = controller.getButtonPlay();
        buttonHighscore = controller.getButtonHighscore();
        buttonSettings = controller.getButtonSettings();
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titlePoI, Poi.WIDTH/8, Poi.HEIGHT - titlePoI.getHeight()*2);
        sb.draw(penguin, Poi.WIDTH/3, Poi.HEIGHT*6/8);
        sb.draw(buttonPlay, Poi.WIDTH/2-controller.getButtonWidth()/2, Poi.HEIGHT*3/6);
        sb.draw(buttonHighscore, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT*2/6);
        sb.draw(buttonSettings, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        sb.end();
    }

    public void dispose() {
        titlePoI.dispose();
        buttonPlay.dispose();
        buttonHighscore.dispose();
        buttonSettings.dispose();
    }
}
