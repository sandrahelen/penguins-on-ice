package poi.game.models.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ViewFactory {
    public void render(SpriteBatch sb);
    public void create();
    public void dispose();
}
