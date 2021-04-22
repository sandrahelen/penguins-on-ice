package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.entityComponents.ButtonComponent;
import poi.game.views.GameView;
import poi.game.views.MenuView;

// Controller for tutorial
public class HelpController extends Controller {

    private boolean inGame;
    private ButtonComponent buttonComponent;
    private Texture buttonBack;
    private Rectangle boundsBack;
    private Texture buttonNext;
    private Rectangle boundsNext;
    private Texture screenshotGame;
    private Texture screenshotIcecube;
    private Texture screenshotController;
    private Texture screenshotGoal;
    private int guideNum;

    public HelpController(boolean inGame) {
        this.inGame = inGame;
        buttonComponent = new ButtonComponent();
        buttonBack = buttonComponent.getButtonBack();
        boundsBack = new Rectangle(Poi.WIDTH / 4 - buttonBack.getWidth() / 2, Poi.HEIGHT / 16, buttonBack.getWidth(), buttonBack.getHeight());
        buttonNext = buttonComponent.getButtonNext();
        boundsNext = new Rectangle(Poi.WIDTH *3/ 4 - buttonBack.getWidth() / 2, Poi.HEIGHT / 16, buttonNext.getWidth(), buttonNext.getHeight());
        screenshotGame = new Texture("screenshot/screenshotGame.png");
        screenshotGame = new Texture("screenshot/screenshotGame.png");
        screenshotIcecube = new Texture("screenshot/screenshotIcecube.png");
        screenshotController = new Texture("screenshot/screenshotController.png");
        screenshotGoal = new Texture("screenshot/screenshotGoal.png");
        guideNum = 1;
    }

    public int getButtonWidth() { return buttonComponent.getButtonWidth(); }
    public int getButtonHeight() { return buttonComponent.getButtonHeight(); }

    public Texture getButtonBack() {
        return buttonBack;
    }
    public Texture getButtonNext() {
        return buttonNext;
    }

    public Texture getScreenshotGame() {
        return screenshotGame;
    }
    public Texture getScreenshotIcecube() {
        return screenshotIcecube;
    }
    public Texture getScreenshotController() {
        return screenshotController;
    }
    public Texture getScreenshotGoal() {
        return screenshotGoal;
    }

    public void setNextGuideNum() {
        if (guideNum + 1 <= 3) {
            guideNum ++;
        }
        else {
            guideNum = 1;
        }
    }

    public void setPrevGuideNum() {
        if (guideNum > 1) {
            guideNum --;
        }
    }

    public int getGuideNum() {
        return guideNum;
    }

    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (Gdx.input.justTouched()) {
            if (boundsBack.contains(touchTransformed.x, touchTransformed.y) && getGuideNum() == 1) {
                changeViewController.set(new MenuView());
            }
            else if (boundsBack.contains(touchTransformed.x, touchTransformed.y) && getGuideNum() > 1) {
                setPrevGuideNum();
            }
            else if (boundsNext.contains(touchTransformed.x, touchTransformed.y) && getGuideNum() <= 2) {
                setNextGuideNum();
            }
            else if (boundsNext.contains(touchTransformed.x, touchTransformed.y) && getGuideNum() == 3) {
                setNextGuideNum();
                if (Poi.getTutorial() && inGame) {
                    Poi.setTutorial();
                    changeViewController.set(new GameView());
                }
                else {
                    Poi.setTutorial();
                    changeViewController.set(new MenuView());
                }
            }
        }
    }
}
