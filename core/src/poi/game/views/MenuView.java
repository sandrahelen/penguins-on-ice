package poi.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.models.factories.ViewFactory;

public class MenuView implements ViewFactory {

    private Texture playButton;
    public void create() {
        playButton = new Texture("button.png");
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(playButton, 50, 50);
        sb.end();
    }

    public void dispose() {

    }
}
