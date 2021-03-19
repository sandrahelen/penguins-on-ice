package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.models.factories.ViewFactory;

public class GameView implements ViewFactory {

    private BitmapFont text;

    public void create() {
        Gdx.app.log("GameView", "create");
        text = new BitmapFont();


    }

    @Override
    public void render(SpriteBatch sb) {
        //sb.begin();
        Gdx.app.log("GameView", "render");
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        text.draw(sb, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //sb.end();
    }

    public void dispose() {
        text.dispose();
    }
}
