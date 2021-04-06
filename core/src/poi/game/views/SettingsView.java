package poi.game.views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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

    public SettingsView (MenuController controller) {
        super(controller);
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
        titleSettings = new Texture("titleSettings.png");
        buttonSound = new Texture("buttonSound.png");
        buttonColor = new Texture("buttonColor.png");
        buttonMenu = new Texture("buttonMenu.png");
        buttonResume = new Texture("buttonResume.png");
        boundsSound = new Rectangle(Poi.WIDTH/4, (Poi.HEIGHT - buttonSound.getHeight())*3/6 - buttonSound.getHeight()/2, buttonSound.getWidth(), buttonSound.getHeight());
        boundsColor = new Rectangle(Poi.WIDTH/4, (Poi.HEIGHT - buttonColor.getHeight()/2)*4/6 - buttonColor.getHeight()/2, buttonColor.getWidth(), buttonColor.getHeight());
        boundsMenu = new Rectangle(Poi.WIDTH/4, (Poi.HEIGHT - buttonMenu.getHeight()/2)*5/6 - buttonMenu.getHeight()/2, buttonMenu.getWidth(), buttonMenu.getHeight());
        boundsResume = new Rectangle(20, 30, buttonResume.getWidth(), buttonResume.getHeight());
}

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            if (boundsMenu.contains(Gdx.input.getX(), Gdx.input.getY())) {
                controller.set(new MenuView(controller));
            }
            else if (boundsResume.contains(Gdx.input.getX(), Gdx.input.getY())) {
                controller.set(new GameView(controller));
            }
        }

        if (boundsResume.contains(Gdx.input.getX(), Gdx.input.getY())) {
            Gdx.app.log("GAME", "[" + Gdx.input.getX() + ", " + Gdx.input.getY() +"]");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Gdx.app.log("SettingsView", "render");
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleSettings, Poi.WIDTH*5/16, Poi.HEIGHT - titleSettings.getHeight()*3);
        sb.draw(buttonSound, Poi.WIDTH/4, Poi.HEIGHT*3/6);
        sb.draw(buttonColor, Poi.WIDTH/4,Poi.HEIGHT*2/6);
        sb.draw(buttonMenu, Poi.WIDTH/4,Poi.HEIGHT/6);
        sb.draw(buttonResume, 20,Poi.HEIGHT - 30 - buttonResume.getHeight());
        sb.end();
    }

    public void dispose() {
        titleSettings.dispose();
        buttonSound.dispose();
        buttonColor.dispose();
        buttonMenu.dispose();
    }
}
