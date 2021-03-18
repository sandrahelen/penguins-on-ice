package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.models.factories.ViewFactory;

public class GameView implements ViewFactory {

    private BitmapFont text;

    public void create() {
        text = new BitmapFont();
    }

    @Override
    public void render(SpriteBatch sb) {
        text.draw(sb, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);

    }

    public void dispose() {

    }
}
