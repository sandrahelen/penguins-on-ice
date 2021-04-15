package poi.game.models.entityComponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;

import poi.game.Poi;

public class TextFieldComponent {

    private BitmapFont font;
    private FitViewport viewport;
    private OrthographicCamera cam;
    private Rectangle boundsTextBox;
    private TextField textfield;
    private TextField.TextFieldStyle style;
    private Texture textBox;
    private Stage stage;

    private float pos1;
    private float pos2;

    public TextFieldComponent() {
        cam = Poi.getCamera();
        font = new BitmapFont();
        textBox = new Texture("general/textField.png");
        //boundsTextBox = new Rectangle(pos1-20, pos2-20, textBox.getWidth(), textBox.getHeight());
        //Poi.WIDTH/2-textfieldBox.getWidth()/2, Poi.HEIGHT/2-20
        //Poi.WIDTH/2-textfieldBox.getWidth()/2+20, Poi.HEIGHT/2 ok

        viewport = new FitViewport(Poi.WIDTH, Poi.HEIGHT, cam);
        stage = new Stage(viewport);

        style = new TextField.TextFieldStyle();
        style.font = font;
        style.fontColor = Color.BLACK;

        textfield = new TextField("", style);
        textfield.setPosition(pos1, pos2);

        stage.addActor(textfield);
        stage.setKeyboardFocus(textfield);

        Gdx.input.setOnscreenKeyboardVisible(true);
        Gdx.input.setInputProcessor(stage);
    }

    public String getText() {
        return textfield.getText();
    }
    public Texture getTextBox() {
        return textBox;
    }
    public Rectangle getBoundsTextBox() {
        return boundsTextBox;
    }
    public void setPosition(float pos1, float pos2) {
        boundsTextBox = new Rectangle(pos1-20, pos2-20, textBox.getWidth(), textBox.getHeight());
    }

    public void draw() {
        stage.draw();
        stage.act();
    }
    public void dispose() {
        stage.clear();
    }
}
