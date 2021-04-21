package poi.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.ColorController;
import poi.game.models.factories.ViewFactory;

// View for change penguin color
public class ColorView extends View implements ViewFactory {

    private ColorController controller;
    private BitmapFont text;
    private Texture buttonPenguinBlack;
    private Texture buttonPenguinPink;
    private Texture buttonPenguinGreen;
    private Texture buttonPenguinPurp;
    private Texture buttonBack;
    private Texture selected;

    public ColorView (GameView gameView){
        super();
        controller = new ColorController(gameView);
        text = new BitmapFont();
        text.setColor(Color.BLACK);
        buttonPenguinBlack = controller.getButtonPenguinBlack();
        buttonPenguinPink = controller.getButtonPenguinPink();
        buttonPenguinGreen = controller.getButtonPenguinGreen();
        buttonPenguinPurp = controller.getButtonPenguinPurp();
        buttonBack = controller.getButtonBack();
        selected = controller.getSelected();
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        text.draw(sb, "Player 1", Poi.WIDTH*9/40, Poi.HEIGHT-Poi.HEIGHT/8);
        text.draw(sb, "Player 2", Poi.WIDTH*29/40, Poi.HEIGHT-Poi.HEIGHT/8);
        // Draw background to the chosen penguin color
        if (controller.getColorP1() == 0) {
            sb.draw(selected, Poi.WIDTH/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5);
        }
        else if (controller.getColorP1() == 1) {
            sb.draw(selected, Poi.WIDTH*2/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5);
        }
        else if (controller.getColorP1() == 2) {
            sb.draw(selected, Poi.WIDTH/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5);
        }
        else if (controller.getColorP1() == 3) {
            sb.draw(selected, Poi.WIDTH*2/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5);
        }
        if (controller.getColorP2() == 0) {
            sb.draw(selected, Poi.WIDTH*4/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5);
        }
        else if (controller.getColorP2() == 1) {
            sb.draw(selected, Poi.WIDTH*5/6 - selected.getWidth()/3, Poi.HEIGHT*6/9 - selected.getHeight()/5);
        }
        else if (controller.getColorP2() == 2) {
            sb.draw(selected, Poi.WIDTH*4/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5);
        }
        else if (controller.getColorP2() == 3) {
            sb.draw(selected, Poi.WIDTH*5/6 - selected.getWidth()/3, Poi.HEIGHT*4/9 - selected.getHeight()/5);
        }
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
