package poi.game.views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import poi.game.controllers.MenuController;
import poi.game.Poi;
import poi.game.models.factories.ViewFactory;

public class MenuView extends View {

    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;
    private BitmapFont text;
    private Rectangle boundsPlay;
    private Rectangle boundsHighscore;
    private Rectangle boundsSettings;

    public MenuView(MenuController controller) {
        super(controller);
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
        buttonPlay = new Texture("button.png");
        buttonHighscore = new Texture("button.png");
        buttonSettings = new Texture("button.png");
        text = new BitmapFont();
        boundsPlay = new Rectangle(Poi.WIDTH/4, (Poi.HEIGHT - buttonPlay.getHeight())*3/6 - buttonPlay.getHeight()/2, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsHighscore = new Rectangle(Poi.WIDTH/4, (Poi.HEIGHT - buttonPlay.getHeight()/2)*4/6 - buttonHighscore.getHeight()/2, buttonHighscore.getWidth(), buttonHighscore.getHeight());
        boundsSettings = new Rectangle(Poi.WIDTH/4, (Poi.HEIGHT - buttonPlay.getHeight()/2)*5/6 - buttonSettings.getHeight()/2, buttonSettings.getWidth(), buttonSettings.getHeight());
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            if (boundsPlay.contains(Gdx.input.getX(), Gdx.input.getY())) {
                controller.set(new GameView(controller));
            }
            else if (boundsHighscore.contains(Gdx.input.getX(), Gdx.input.getY())) {
                controller.set(new HighscoreView(controller));
            }
            else if (boundsSettings.contains(Gdx.input.getX(), Gdx.input.getY())) {
                controller.set(new SettingsView(controller));
            }
        }
        /*
        if (boundsPlay.contains(Gdx.input.getX(), Gdx.input.getY())) {
            Gdx.app.log("GAME", "[" + Gdx.input.getX() + ", " + Gdx.input.getY() +"]");
        }
        else if (boundsHighscore.contains(Gdx.input.getX(), Gdx.input.getY())) {
            Gdx.app.log("HIGHSCORE", "[" + Gdx.input.getX() + ", " + Gdx.input.getY() +"]");
        }
        else if (boundsSettings.contains(Gdx.input.getX(), Gdx.input.getY())) {
            Gdx.app.log("SETTINGS", "[" + Gdx.input.getX() + ", " + Gdx.input.getY() +"]");
        }
         */

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Gdx.app.log("MenuView", "render");
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        text.setColor(94/255f,169/255f,186/255f,1);
        text.draw(sb, "Penguins on Ice", 40,Poi.HEIGHT - 50);
        text.setColor(1, 1, 1, 1);
        sb.draw(buttonPlay, Poi.WIDTH/4, Poi.HEIGHT*3/6);
        text.draw(sb, "Play game", Poi.WIDTH*2/5, Poi.HEIGHT*3/6 + buttonPlay.getHeight()*3/5);
        sb.draw(buttonHighscore, Poi.WIDTH/4,Poi.HEIGHT*2/6);
        text.draw(sb, "Highscore", Poi.WIDTH*2/5, Poi.HEIGHT*2/6 + buttonHighscore.getHeight()*3/5);
        sb.draw(buttonSettings, Poi.WIDTH/4,Poi.HEIGHT/6);
        text.draw(sb, "Settings", Poi.WIDTH*2/5, Poi.HEIGHT/6 + buttonSettings.getHeight()*3/5);
        sb.end();

    }

    public void dispose() {
        buttonPlay.dispose();
        buttonHighscore.dispose();
        buttonSettings.dispose();
        text.dispose();
    }
}
