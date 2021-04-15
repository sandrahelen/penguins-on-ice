package poi.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.Poi;
import poi.game.controllers.ColorController;
import poi.game.models.factories.ViewFactory;

public class ColorView extends View implements ViewFactory {

    private ColorController controller;
    private Texture buttonPenguinBlack;
    private Texture buttonPenguinPink;
    private Texture buttonPenguinGreen;
    private Texture buttonPenguinPurp;
    private Texture buttonBack;

    public ColorView (GameView gameView){
        super();
        controller = new ColorController(gameView);
        buttonPenguinBlack = controller.getButtonPenguinBlack();
        buttonPenguinPink = controller.getButtonPenguinPink();
        buttonPenguinGreen = controller.getButtonPenguinGreen();
        buttonPenguinPurp = controller.getButtonPenguinPurp();
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
        sb.draw(buttonPenguinBlack, Poi.WIDTH/6, Poi.HEIGHT*6/9);
        sb.draw(buttonPenguinPink, Poi.WIDTH*2/6, Poi.HEIGHT*6/9);
        sb.draw(buttonPenguinGreen, Poi.WIDTH/6, Poi.HEIGHT*4/9);
        sb.draw(buttonPenguinPurp, Poi.WIDTH*2/6, Poi.HEIGHT*4/9);
        sb.draw(buttonPenguinBlack, Poi.WIDTH*4/6, Poi.HEIGHT*6/9);
        sb.draw(buttonPenguinPink, Poi.WIDTH*5/6, Poi.HEIGHT*6/9);
        sb.draw(buttonPenguinGreen, Poi.WIDTH*4/6, Poi.HEIGHT*4/9);
        sb.draw(buttonPenguinPurp, Poi.WIDTH*5/6, Poi.HEIGHT*4/9);
        sb.draw(buttonBack, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        sb.end();
    }

    public void dispose() {
        buttonPenguinBlack.dispose();
        buttonPenguinPink.dispose();
        buttonPenguinGreen.dispose();
        buttonBack.dispose();
    }
}
