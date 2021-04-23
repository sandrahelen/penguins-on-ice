package poi.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.ColorController;

import java.util.ArrayList;

// View for change penguin color
public class ColorView extends View {

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
        text.draw(sb, "Player 1", Poi.WIDTH*7/40, Poi.HEIGHT-Poi.HEIGHT/4);
        text.draw(sb, "Player 2", Poi.WIDTH*30/40, Poi.HEIGHT-Poi.HEIGHT/4);
        // Draw background to the chosen penguin type and color
        if (controller.penguinTypeP1 == 0) {
            sb.draw(selectedPenguin, Poi.WIDTH/10 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP1 == 1) {
            sb.draw(selectedPenguin, Poi.WIDTH*((float)1.75)/10 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP1 == 2) {
            sb.draw(selectedPenguin, Poi.WIDTH*((float)2.5)/10 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP1 == 3) {
            sb.draw(selectedPenguin, Poi.WIDTH*((float)3.25)/10 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        if (controller.penguinTypeP2 == 0) {
            sb.draw(selectedPenguin, Poi.WIDTH*4/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP2 == 1) {
            sb.draw(selectedPenguin, Poi.WIDTH*((float)4.45)/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP2 == 2) {
            sb.draw(selectedPenguin, Poi.WIDTH*((float)4.9)/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        else if (controller.penguinTypeP2 == 3) {
            sb.draw(selectedPenguin, Poi.WIDTH*((float)5.35)/6 - selectedPenguin.getWidth()/3,
                    Poi.HEIGHT-Poi.HEIGHT/6 - selectedPenguin.getHeight()/5, selectedPenguin.getWidth(), selectedPenguin.getHeight());
        }
        if (controller.getColorP1() == 0) {
            sb.draw(selectedColor, Poi.WIDTH/10 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP1() == 1) {
            sb.draw(selectedColor, Poi.WIDTH*2/10 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP1() == 2) {
            sb.draw(selectedColor, Poi.WIDTH/10 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP1() == 3) {
            sb.draw(selectedColor, Poi.WIDTH*2/10 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP1() == 4) {
            sb.draw(selectedColor, Poi.WIDTH*3/10 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5);
        }
        if (controller.getColorP2() == 0) {
            sb.draw(selectedColor, Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP2() == 1) {
            sb.draw(selectedColor, Poi.WIDTH*((float)4.65)/6 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP2() == 2) {
            sb.draw(selectedColor, Poi.WIDTH*4/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP2() == 3) {
            sb.draw(selectedColor, Poi.WIDTH*((float)4.65)/6 - selectedColor.getWidth()/3, Poi.HEIGHT*4/10 - selectedColor.getHeight()/5);
        }
        else if (controller.getColorP2() == 4) {
            sb.draw(selectedColor, Poi.WIDTH*((float)5.3)/6 - selectedColor.getWidth()/3, Poi.HEIGHT*11/20 - selectedColor.getHeight()/5);
        }

        // Player 1
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(0), Poi.WIDTH/10, Poi.HEIGHT*11/20);
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(1), Poi.WIDTH*2/10, Poi.HEIGHT*11/20);
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(2), Poi.WIDTH/10, Poi.HEIGHT*4/10);
        sb.draw(penguinColors.get(controller.penguinTypeP1).get(3), Poi.WIDTH*2/10, Poi.HEIGHT*4/10);
        if (penguinColors.get(controller.penguinTypeP1).size() >= 5) {
            sb.draw(penguinColors.get(controller.penguinTypeP1).get(4), Poi.WIDTH*3/10, Poi.HEIGHT*11/20);
        }

        sb.draw(penguinTypes.get(0),Poi.WIDTH/10, Poi.HEIGHT-Poi.HEIGHT/6);
        sb.draw(penguinTypes.get(1),Poi.WIDTH*((float)1.75)/10, Poi.HEIGHT-Poi.HEIGHT/6);
        sb.draw(penguinTypes.get(2),Poi.WIDTH*((float)2.5)/10, Poi.HEIGHT-Poi.HEIGHT/6);
        sb.draw(penguinTypes.get(3),Poi.WIDTH*((float)3.25)/10, Poi.HEIGHT-Poi.HEIGHT/6);

        // Player 2
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(0), Poi.WIDTH*4/6, Poi.HEIGHT*11/20);
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(1), Poi.WIDTH*((float)4.65)/6, Poi.HEIGHT*11/20);
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(2), Poi.WIDTH*4/6, Poi.HEIGHT*4/10);
        sb.draw(penguinColors.get(controller.penguinTypeP2).get(3), Poi.WIDTH*((float)4.65)/6, Poi.HEIGHT*4/10);
        if (penguinColors.get(controller.penguinTypeP2).size() >= 5) {
            sb.draw(penguinColors.get(controller.penguinTypeP2).get(4), Poi.WIDTH*((float)5.3)/6, Poi.HEIGHT*11/20);
        }

        sb.draw(penguinTypes.get(0),Poi.WIDTH*4/6, Poi.HEIGHT-Poi.HEIGHT/6);
        sb.draw(penguinTypes.get(1),Poi.WIDTH*((float)4.45)/6, Poi.HEIGHT-Poi.HEIGHT/6);
        sb.draw(penguinTypes.get(2),Poi.WIDTH*((float)4.9)/6, Poi.HEIGHT-Poi.HEIGHT/6);
        sb.draw(penguinTypes.get(3),Poi.WIDTH*((float)5.35)/6, Poi.HEIGHT-Poi.HEIGHT/6);

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
