package poi.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.Poi;
import poi.game.controllers.ColorController;
import poi.game.models.factories.ViewFactory;

public class HelpView extends View implements ViewFactory {
    private ColorController controller;
    private BitmapFont text;
    private Texture buttonBack;

    public HelpView (GameView gameView){
        super();
        controller = new ColorController(gameView);
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

        sb.end();
    }

    public void dispose() {
        buttonBack.dispose();
    }
}
