package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

import poi.game.Datahandler;
import poi.game.Poi;
import poi.game.controllers.EndGameController;
import poi.game.models.entityComponents.TextFieldComponent;
import poi.game.models.factories.ViewFactory;

public class EndGameView extends View implements ViewFactory {

    private EndGameController controller;

    private BitmapFont text;
    private Datahandler datahandler;
    private Texture titleEndGame;
    private Texture textfieldBox;
    private Texture buttonSubmit;

    private Rectangle boundsTextfield;
    private Rectangle boundsSubmit;

    //private TextFieldComponent textfield;
    /*
    private TextField textfield;
    private FitViewport viewport;
    private Stage stage;
    private TextField.TextFieldStyle style;
    */

    private int endTime = 0;
    private String username = "";

    public EndGameView() {
        super();
        controller = new EndGameController();
        cam = Poi.getCamera();
        titleEndGame = new Texture("general/titleEndGame.png");
        //textfieldBox = new Texture("general/textField.png");
        textfieldBox = controller.getTextfield().getTextBox();
        //buttonSubmit = new Texture("general/buttonSubmit.png");
        buttonSubmit = controller.getButtonSubmit();
        text = new BitmapFont();

        //boundsTextfield = new Rectangle(Poi.WIDTH/2-textfieldBox.getWidth()/2, Poi.HEIGHT/2-20, textfieldBox.getWidth(), textfieldBox.getHeight());
        boundsTextfield = controller.getTextfield().getBoundsTextBox();
        //boundsSubmit = new Rectangle(Poi.WIDTH/2-buttonSubmit.getWidth()/2,Poi.HEIGHT/2-buttonSubmit.getHeight()*3/2, buttonSubmit.getWidth(), buttonSubmit.getHeight());
        boundsSubmit = controller.getBoundsSubmit();

        //textfield = new TextFieldComponent(Poi.WIDTH/2-textfieldBox.getWidth()/2+20, Poi.HEIGHT/2);

        /*
        // Adding a textfield to let user write some text
        viewport = new FitViewport(Poi.WIDTH, Poi.HEIGHT, cam);
        stage = new Stage(viewport);
        style = new TextField.TextFieldStyle();
        style.font = text;
        style.fontColor = Color.BLACK;

        textfield = new TextField("", style);
        textfield.setPosition(Poi.WIDTH/2-textfieldBox.getWidth()/2+20, Poi.HEIGHT/2);  // Remember to update, if updating Textfield-texture's positions
        stage.addActor(textfield);
        stage.setKeyboardFocus(textfield);

        Gdx.input.setOnscreenKeyboardVisible(true); //Displaying keyboard
        Gdx.input.setInputProcessor(stage);
        */
        // Linking to Firebase database
        datahandler = changeViewController.getDatahandler();
        changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
    }
    /*
    public void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 touchTransformed = cam.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (boundsTextfield.contains(touchTransformed.x, touchTransformed.y)) {
                // TODO Display keyboard when click

                /* // Not working
                stage.setKeyboardFocus(textfield);
                Gdx.input.setOnscreenKeyboardVisible(true);
                */
/*
                System.out.println("Keyboard?");
            }
            if (boundsSubmit.contains(touchTransformed.x, touchTransformed.y)) {
                username = textfield.getText();
                endTime = new Random().nextInt(101);    // Placeholder for time-score, random int between 0-100
                changeViewController.getLeaderboard().submitScore(username, endTime);     // Submits the score to Firebase database
                changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
                Gdx.input.setOnscreenKeyboardVisible(false);    // Disable displaying keyboard
                changeViewController.set(new HighscoreView());
            }
            // Not possible to remove keyboard if no written text
            if (!textfield.getText().isEmpty()) {
                // Try to unfocus instead? Possibly removing keyboard?
                Gdx.input.setOnscreenKeyboardVisible(false);
            }
        }
    }
    */
    @Override
    public void update(float dt) {
        controller.handleInput(username, endTime, datahandler);
        //handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Gdx.app.log("EndGameView", "render");
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleEndGame, Poi.WIDTH/2-titleEndGame.getWidth()/2, Poi.HEIGHT - titleEndGame.getHeight()*2);
        text.draw(sb, "Please enter your name: ", Poi.WIDTH/2-100, Poi.HEIGHT*9/12);
        //sb.draw(textfieldBox, Poi.WIDTH/2-textfieldBox.getWidth()/2, Poi.HEIGHT/2-20 /*textfieldBox.getHeight()/2*/);
        sb.draw(textfieldBox, Poi.WIDTH/2-textfieldBox.getWidth()/2, Poi.HEIGHT/2-20 /*textfieldBox.getHeight()/2*/);
        sb.draw(buttonSubmit, Poi.WIDTH/2-buttonSubmit.getWidth()/2,Poi.HEIGHT/2-buttonSubmit.getHeight()*3/2);
        sb.end();

        //stage.draw();
        //stage.act();
        controller.drawTextField();
    }

    public void dispose() {
        text.dispose();
        titleEndGame.dispose();
        buttonSubmit.dispose();
        //stage.clear();
        controller.disposeTextField();
    }

    // To save the endTime from GameView?
    public void setEndTime(int time) {
        endTime = time;
    }
}
