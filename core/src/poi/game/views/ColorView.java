package poi.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.ColorController;
import poi.game.models.factories.ViewFactory;

import java.util.ArrayList;

// View for change penguin color
public class ColorView extends View implements ViewFactory {

    private ColorController controller;
    private BitmapFont text;
    private Texture buttonBack;
    private Texture selectedColor;
    private Texture selectedPenguin;

    private ArrayList<ArrayList<Texture>> penguinColors;
    private ArrayList<Texture> penguinTypes;

    public ColorView (GameView gameView){
        super();
        controller = new ColorController(gameView);
        text = new BitmapFont();
        text.setColor(Color.BLACK);
        penguinTypes = controller.getButtonPenguinType();
        penguinColors = controller.getButtonPenguin();
        buttonBack = controller.getButtonBack();
        selectedColor = controller.getSelectedColor();
        selectedPenguin = controller.getSelectedPenguin();
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
        // Draw background to the chosen penguin and color
        if (controller.penguinTypeP1 == 0) {
            sb.draw(selectedPenguin, Poi.WIDTH/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP1 == 1) {
            sb.draw(selectedPenguin, Poi.WIDTH*(2)/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        if (controller.penguinTypeP2 == 0) {
            sb.draw(selectedPenguin, Poi.WIDTH*4/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP2 == 1) {
            sb.draw(selectedPenguin, Poi.WIDTH*(5)/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT*1/9 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        if (controller.getColorP1() == 0) {
            sb.draw(selectedColor, Poi.WIDTH/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP1() == 1) {
            sb.draw(selectedColor, Poi.WIDTH*2/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP1() == 2) {
            sb.draw(selectedColor, Poi.WIDTH/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP1() == 3) {
            sb.draw(selectedColor, Poi.WIDTH*2/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5);
        }
        if (controller.getColorP2() == 0) {
            sb.draw(selectedColor, Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP2() == 1) {
            sb.draw(selectedColor, Poi.WIDTH*5/6 - selectedColor.getWidth()/3, Poi.HEIGHT*6/9 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP2() == 2) {
            sb.draw(selectedColor, Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP2() == 3) {
            sb.draw(selectedColor, Poi.WIDTH*5/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/9 - selectedColor.getHeight()/5);
        }

        // Player 1
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(0), Poi.WIDTH/6, Poi.HEIGHT*6/9);
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(1), Poi.WIDTH*2/6, Poi.HEIGHT*6/9);
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(2), Poi.WIDTH/6, Poi.HEIGHT*4/9);
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(3), Poi.WIDTH*2/6, Poi.HEIGHT*4/9);

        sb.draw(penguinTypes.get(0),Poi.WIDTH/6, Poi.HEIGHT/9);
        sb.draw(penguinTypes.get(1),Poi.WIDTH*(2)/6, Poi.HEIGHT*1/9);

        // Player 2
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(0), Poi.WIDTH*4/6, Poi.HEIGHT*6/9);
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(1), Poi.WIDTH*5/6, Poi.HEIGHT*6/9);
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(2), Poi.WIDTH*4/6, Poi.HEIGHT*4/9);
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(3), Poi.WIDTH*5/6, Poi.HEIGHT*4/9);

        sb.draw(penguinTypes.get(0),Poi.WIDTH*4/6, Poi.HEIGHT*1/9);
        sb.draw(penguinTypes.get(1),Poi.WIDTH*(5)/6, Poi.HEIGHT*1/9);

        sb.draw(buttonBack, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        sb.end();
    }

    public void dispose() {
        for (int i = 0; i < penguinTypes.size(); i++) {
            penguinTypes.get(i).dispose();
        }
        for (int i = 0; i < penguinColors.size(); i++) {
            for (int j = 0; j < penguinColors.size(); j++) {
                penguinColors.get(i).get(j).dispose();
            }
        }
        buttonBack.dispose();
    }
}
