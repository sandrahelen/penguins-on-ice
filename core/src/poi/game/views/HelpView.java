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

    public HelpView (){
        super();
        controller = new HelpController();
        text = new BitmapFont();
        text.setColor(Color.BLACK);
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
        text.draw(sb, "Player 1", Poi.WIDTH*9/40, Poi.HEIGHT-Poi.HEIGHT/8);
        sb.draw(buttonBack, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/6);
        sb.end();
    }

    public void dispose() {
        buttonBack.dispose();
    }
}
