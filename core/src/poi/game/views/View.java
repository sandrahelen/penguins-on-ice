package poi.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.ChangeViewController;

// Template for all views
public abstract class View {

    protected OrthographicCamera cam;
    protected ChangeViewController changeViewController;

    public View() {
        this.changeViewController = Poi.getChangeViewController();
        cam = Poi.getCamera();
    }

    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}
