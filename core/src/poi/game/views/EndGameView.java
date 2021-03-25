package poi.game.views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.Poi;
import poi.game.controllers.MenuController;
import poi.game.models.factories.ViewFactory;

public class EndGameView extends View implements ViewFactory {

    private BitmapFont text;

    public EndGameView(MenuController controller) {
        super(controller);
        text = new BitmapFont();
        cam.setToOrtho(false, Poi.WIDTH/2, Poi.HEIGHT/2);
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Gdx.app.log("EndGameView", "render");
        sb.begin();
        text.draw(sb, "EndGame", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        sb.end();
    }

    public void dispose() {
        text.dispose();
    }
}
