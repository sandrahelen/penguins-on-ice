package poi.game.models.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ViewFactory {
    void update(float dt);
    void render(SpriteBatch sb);
    void dispose();
}
