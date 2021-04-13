package poi.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.controllers.ChangeViewController;

public abstract class View {

    protected OrthographicCamera cam;
    protected ChangeViewController changeViewController;

    public View(ChangeViewController changeViewController) {
        this.changeViewController = changeViewController;
    }

    //protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

}
