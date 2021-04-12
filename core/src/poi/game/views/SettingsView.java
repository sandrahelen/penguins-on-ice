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
    private Rectangle boundsSound;
    private Rectangle boundsColor;
    private Rectangle boundsMenu;

    public SettingsView (MenuController controller) {
        super(controller);
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
        titleSettings = new Texture("titleSettings.png");
        buttonSound = new Texture("buttonSound.png");
        buttonColor = new Texture("buttonColor.png");
        buttonMenu = new Texture("buttonMenu.png");
        boundsSound = new Rectangle(Poi.WIDTH/2-buttonSound.getWidth()/2, Poi.HEIGHT*3/6, buttonSound.getWidth(), buttonSound.getHeight());
        boundsColor = new Rectangle(Poi.WIDTH/2-buttonColor.getWidth()/2,Poi.HEIGHT*2/6, buttonColor.getWidth(), buttonColor.getHeight());
        boundsMenu = new Rectangle(Poi.WIDTH/2-buttonMenu.getWidth()/2,Poi.HEIGHT/6, buttonMenu.getWidth(), buttonMenu.getHeight());
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            Vector3 touchTransformed = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (boundsMenu.contains(touchTransformed.x, touchTransformed.y)) {
                controller.set(new MenuView(controller));
            }
        }

        /*if (boundsMenu.contains(Gdx.input.getX(), Gdx.input.getY())) {
            Gdx.app.log("MENU", "[" + Gdx.input.getX() + ", " + Gdx.input.getY() +"]");
        }*/
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
        sb.draw(titleSettings, Poi.WIDTH/2-titleSettings.getWidth()/2 /*5/16*/, Poi.HEIGHT - titleSettings.getHeight()*3);
        sb.draw(buttonSound, Poi.WIDTH/2-buttonSound.getWidth()/2, Poi.HEIGHT*3/6);
        sb.draw(buttonColor, Poi.WIDTH/2-buttonColor.getWidth()/2,Poi.HEIGHT*2/6);
        sb.draw(buttonMenu, Poi.WIDTH/2-buttonMenu.getWidth()/2,Poi.HEIGHT/6);
        sb.end();
    }

    public void dispose() {
        titleSettings.dispose();
        buttonSound.dispose();
        buttonColor.dispose();
        buttonMenu.dispose();
    }
}
