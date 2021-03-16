package poi.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.models.factories.ViewFactory;

public class GameView implements ViewFactory {
    private Texture playButton;



    public void create() {
        playButton = new Texture("button.png");
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(playButton, 0, 0);
    }

    public void dispose() {

    }
}
