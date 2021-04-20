package poi.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.HelpController;
import poi.game.models.factories.ViewFactory;

public class HelpView extends View implements ViewFactory {

    private HelpController controller;
    private BitmapFont text;
    private Texture buttonBack;
    private Texture buttonNext;
    private Texture screenshotGame;
    private Texture screenshotIcecube;
    private Texture screenshotController;
    private Texture screenshotGoal;

    public HelpView (){
        super();
        controller = new HelpController();
        text = new BitmapFont();
        text.setColor(Color.BLACK);
        buttonBack = controller.getButtonBack();
        buttonNext = controller.getButtonNext();
        screenshotGame = controller.getScreenshotGame();
        screenshotIcecube = controller.getScreenshotIcecube();
        screenshotController = controller.getScreenshotController();
        screenshotGoal = controller.getScreenshotGoal();
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        if (controller.getGuideNum() == 1) {
            text.draw(sb, "Penguin on Ice is a cooperative", Poi.WIDTH/48, Poi.HEIGHT-Poi.HEIGHT/6);
            text.draw(sb, "multiplayer game. Where the", Poi.WIDTH/48, Poi.HEIGHT-Poi.HEIGHT*6/24);
            text.draw(sb, "goal is to reach the finish line", Poi.WIDTH/48, Poi.HEIGHT-Poi.HEIGHT*8/24);
            text.draw(sb, "together as fast as possible.", Poi.WIDTH/48, Poi.HEIGHT-Poi.HEIGHT*10/24);

            sb.draw(screenshotGame, Poi.WIDTH*23/48-controller.getButtonWidth()/2,Poi.HEIGHT/3, screenshotGame.getWidth()*2/3, screenshotGame.getHeight()*2/3);
        }
        else if (controller.getGuideNum() == 2) {
            text.draw(sb, "The boost button increases", Poi.WIDTH*7/24, Poi.HEIGHT-Poi.HEIGHT/6);
            text.draw(sb, "the speed.", Poi.WIDTH*7/24, Poi.HEIGHT-Poi.HEIGHT*5/24);
            text.draw(sb, "Move the penguin by moving", Poi.WIDTH*7/24, Poi.HEIGHT-Poi.HEIGHT*3/6);
            text.draw(sb, "the joystick left or right.", Poi.WIDTH*7/24, Poi.HEIGHT-Poi.HEIGHT*13/24);
            sb.draw(screenshotController, Poi.WIDTH/6-controller.getButtonWidth()/2,Poi.HEIGHT*2/6, screenshotController.getWidth()*5/4, screenshotController.getHeight()*5/4);
            text.draw(sb, "Move away from", Poi.WIDTH*39/48, Poi.HEIGHT-Poi.HEIGHT*2/6);
            text.draw(sb, "the iceblocks.", Poi.WIDTH*39/48, Poi.HEIGHT-Poi.HEIGHT*9/24);
            sb.draw(screenshotIcecube, Poi.WIDTH*3/4-controller.getButtonWidth()/2,Poi.HEIGHT/3, screenshotIcecube.getWidth()*3/2, screenshotIcecube.getHeight()*3/2);

        }
        else if (controller.getGuideNum() == 3) {
            sb.draw(screenshotGoal, Poi.WIDTH*23/48-controller.getButtonWidth()/2,Poi.HEIGHT/3, screenshotGoal.getWidth()*2/3, screenshotGoal.getHeight()*2/3);
            text.draw(sb, "Cooperate together to reach", Poi.WIDTH/48, Poi.HEIGHT-Poi.HEIGHT/6);
            text.draw(sb, "the finish line.", Poi.WIDTH/48, Poi.HEIGHT-Poi.HEIGHT*6/24);
        }
        sb.draw(buttonBack, Poi.WIDTH/4-controller.getButtonWidth()/2,Poi.HEIGHT/16);
        sb.draw(buttonNext, Poi.WIDTH*3/4-controller.getButtonWidth()/2,Poi.HEIGHT/16);
        sb.end();
    }

    public void dispose() {
        buttonBack.dispose();
        buttonNext.dispose();
    }
}
