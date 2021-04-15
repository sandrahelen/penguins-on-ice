package poi.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.Poi;
import poi.game.controllers.ColorController;
import poi.game.models.factories.ViewFactory;

public class ColorView extends View implements ViewFactory {

    private ColorController controller;
    private Texture buttonBack;

    public ColorView (GameView gameView){
        super();
        controller = new ColorController(gameView);
        buttonBack = controller.getButtonBack();
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(buttonBack, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        sb.end();
    }

    public void dispose() {
        buttonBack.dispose();
    }
}
