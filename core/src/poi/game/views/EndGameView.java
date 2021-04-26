package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;

import poi.game.Poi;
import poi.game.controllers.EndGameController;

// View after penguins have reached the finish line and the score is registered
public class EndGameView extends View {

    private EndGameController controller;
    private int endTime;
    private BitmapFont font;
    private Texture titleEndGame;
    private Texture textfieldBox;
    private Texture buttonSubmit;
    private Rectangle boundsTextfield;
    private TextField textfield;
    private FitViewport viewport;
    private Stage stage;
    private TextField.TextFieldStyle style;

    public EndGameView(int endTime) {
        super();
        controller = new EndGameController(endTime);
        this.endTime = endTime;
        cam = Poi.getCamera();
        font = new BitmapFont();
        font.setColor(Color.BLACK);
        titleEndGame = new Texture("general/titleEndGame.png");
        textfieldBox = new Texture("general/textField.png");
        buttonSubmit = controller.getButtonSubmit();
        boundsTextfield = new Rectangle(Poi.WIDTH/2-textfieldBox.getWidth()/2, Poi.HEIGHT/2-20, textfieldBox.getWidth(), textfieldBox.getHeight());

        // Adding a textfield to let user write some text
        viewport = new FitViewport(Poi.WIDTH, Poi.HEIGHT, cam);
        stage = new Stage(viewport);
        style = new TextField.TextFieldStyle();
        style.font = font;
        style.fontColor = Color.BLACK;

        textfield = new TextField("", style);
        textfield.setPosition(Poi.WIDTH/2-textfieldBox.getWidth()/2+20, Poi.HEIGHT/2+20);  // Remember to update, if updating Textfield-texture's positions
        stage.addActor(textfield);
        stage.setKeyboardFocus(textfield);

        Gdx.input.setOnscreenKeyboardVisible(true); //Displaying keyboard
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void update(float dt) {
        controller.handleInput(textfield.getText());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleEndGame, Poi.WIDTH/2-titleEndGame.getWidth()/2, Poi.HEIGHT - titleEndGame.getHeight()*2);
        font.draw(sb, "FINAL TIME: " + endTime, Poi.WIDTH/2-50, Poi.HEIGHT*9/12+20);
        font.draw(sb, "Please enter your name: ", Poi.WIDTH/2-100, Poi.HEIGHT*9/12);
        sb.draw(textfieldBox, Poi.WIDTH/2-textfieldBox.getWidth()/2, Poi.HEIGHT/2-20 );
        sb.draw(buttonSubmit, Poi.WIDTH/2-buttonSubmit.getWidth()/2,Poi.HEIGHT/2-buttonSubmit.getHeight()*3/2);
        sb.end();

        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        font.dispose();
        titleEndGame.dispose();
        buttonSubmit.dispose();
        stage.clear();
    }
}
