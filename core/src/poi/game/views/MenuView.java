package poi.game.views;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.controllers.ColorController;
import poi.game.controllers.MenuController;
import poi.game.Poi;
import poi.game.models.factories.ViewFactory;

public class MenuView extends View implements ViewFactory{

    private MenuController controller;

    private Texture titlePoI;
    private Texture penguin1;
    private Texture penguin2;
    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;
    private Texture buttonHelp;

    public MenuView() {
        super();
        controller = new MenuController();
        titlePoI = new Texture("general/titlePoI.png");
        // Penguin 1
        if (ColorController.colorP1 == 0) {
            penguin1 = new Texture("general/svart-pingvin.png");
        }
        else if (ColorController.colorP1 == 1) {
            penguin1 = new Texture("general/rosa-pingvin.png");
        }
        else if (ColorController.colorP1 == 2) {
            penguin1 = new Texture("general/grønn-pingvin.png");
        }
        else if (ColorController.colorP1 == 3) {
            penguin1 = new Texture("general/lilla-pingvin.png");
        }
        // Penguin 2
        if (ColorController.colorP2 == 0) {
            penguin2 = new Texture("general/svart-pingvin.png");
        }
        else if (ColorController.colorP2 == 1) {
            penguin2 = new Texture("general/rosa-pingvin.png");
        }
        else if (ColorController.colorP2 == 2) {
            penguin2 = new Texture("general/grønn-pingvin.png");
        }
        else if (ColorController.colorP2 == 3) {
            penguin2 = new Texture("general/lilla-pingvin.png");
        }

        buttonPlay = controller.getButtonPlay();
        buttonHighscore = controller.getButtonHighscore();
        buttonSettings = controller.getButtonSettings();
        buttonHelp = controller.getButtonHelp();
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titlePoI, 20+Poi.WIDTH/4, Poi.HEIGHT - titlePoI.getHeight()*2);
        sb.draw(penguin1, Poi.WIDTH/4-70, Poi.HEIGHT/3, penguin1.getWidth()*4, penguin1.getHeight()*4);
        sb.draw(penguin2, Poi.WIDTH-160, Poi.HEIGHT/3, penguin2.getWidth()*4, penguin2.getHeight()*4);
        sb.draw(buttonPlay, Poi.WIDTH/2-controller.getButtonWidth()/2, Poi.HEIGHT*3/6);
        sb.draw(buttonHighscore, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT*2/6);
        sb.draw(buttonSettings, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        sb.draw(buttonHelp, Poi.WIDTH - buttonHelp.getWidth() - 20, Poi.HEIGHT - buttonHelp.getHeight() - 20);
        sb.end();
    }

    public void dispose() {
        titlePoI.dispose();
        buttonPlay.dispose();
        buttonHighscore.dispose();
        buttonSettings.dispose();
        buttonHelp.dispose();
    }
}
