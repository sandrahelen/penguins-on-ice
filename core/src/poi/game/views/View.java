package poi.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.controllers.ChangeViewController;

public abstract class View {

    protected OrthographicCamera cam;
    protected ChangeViewController controller;

    public View(ChangeViewController controller) {
        this.controller = controller;
        //cam = new OrthographicCamera();
    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();

    /*protected MenuController controller;
    protected final Stage stage;

    View(Controller controller) {
        this.controller = controller;

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    public void render() {
        stage.draw();
    }

    public void dispose () {
        stage.dispose();
    }*/
}
