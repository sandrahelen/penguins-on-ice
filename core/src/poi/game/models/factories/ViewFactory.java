package poi.game.models.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ViewFactory {
    //public void handleInput();
    public void update(float dt);
    public void render(SpriteBatch sb);
    public void dispose();
}
