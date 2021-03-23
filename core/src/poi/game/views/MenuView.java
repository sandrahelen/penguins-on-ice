package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import poi.game.controllers.MenuController;
import poi.game.Poi;
import poi.game.models.factories.ViewFactory;

public class MenuView extends View {

    //private MenuController controller;
    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;
    private BitmapFont text;
    private Vector2 playVector;
    private Rectangle boundsPlay;
    private Rectangle boundsHighscore;
    private Rectangle boundsSettings;
    //private String destination;
    //private Vector2 position;

    public MenuView(MenuController controller) {
        super(controller);
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);

        buttonPlay = new Texture("button_back.png");
        //buttonHighscore = new Texture("button.png");
        //buttonSettings = new Texture("button.png");
        text = new BitmapFont();
        playVector = new Vector2(50, 50);
        boundsPlay = new Rectangle(Poi.WIDTH / 2 + buttonPlay.getWidth() / 2 + 120, Poi.HEIGHT / 2 + 25, buttonPlay.getWidth(), buttonPlay.getHeight());
        //boundsHighscore = new Rectangle(50, 150, buttonHighscore.getWidth(), buttonHighscore.getHeight());
        //boundsSettings = new Rectangle(50, 250, buttonSettings.getWidth(), buttonSettings.getHeight());
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            if (boundsPlay.contains(Gdx.input.getX(), Gdx.input.getY())) {
                controller.set(new GameView(controller));
            }
        }
        if (boundsPlay.contains(Gdx.input.getX(), Gdx.input.getY())) {
            Gdx.app.log("GAME", "[" + Gdx.input.getX() + ", " + Gdx.input.getY() +"]");
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    /*public void create() {
        controller = new MenuController();
        buttonPlay = new Texture("button.png");
        buttonHighscore = new Texture("button.png");
        buttonSettings = new Texture("button.png");
        text = new BitmapFont();
        boundsPlay = new Rectangle(50, 50, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsHighscore = new Rectangle(50, 150, buttonHighscore.getWidth(), buttonHighscore.getHeight());
        boundsSettings = new Rectangle(50, 250, buttonSettings.getWidth(), buttonSettings.getHeight());
    }*/

    @Override
    public void render(SpriteBatch sb) { // sb virker ikke
        //Gdx.app.log("MenuView", "render");
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        text.setColor(0,0,0,1);
        //text.draw(sb, "HEI", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
        //Gdx.app.log("BoundsPlay", "[" + boundsPlay.x + ", "+ boundsPlay.y);
        Gdx.app.log("CAM", "[" + cam.position.x + ", " + cam.position.y +"]");
        //sb.draw(buttonPlay, playVector.x, playVector.y);
        sb.draw(buttonPlay, Poi.WIDTH / 2 + buttonPlay.getWidth() / 2 + 120, Poi.HEIGHT / 2 + 25, buttonPlay.getWidth(), buttonPlay.getHeight());
        //Gdx.app.log("ButtonPlay", "["+buttonPlay.);
        //Gdx.app.log("Input", "[" + Gdx.input.getX() + ", "+ Gdx.input.getY());
        //sb.draw(buttonPlay, boundsPlay.getWidth(),cam.position.y - boundsPlay.getHeight() - buttonPlay.getHeight());
        //sb.draw(buttonPlay, 0, 0);
        //sb.draw(buttonHighscore, boundsHighscore.x,Gdx.graphics.getHeight() - boundsHighscore.y - buttonHighscore.getHeight());
        //sb.draw(buttonSettings, boundsSettings.x,Gdx.graphics.getHeight() - boundsSettings.y - buttonSettings.getHeight());
        sb.end();
        //Gdx.app.log("MenuController render", "After draw buttons");

        //text.draw(sb, "MENU", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //text.draw(sb, "Bounds (" + bounds.x + ", " + bounds.y + ")", Gdx.graphics.getWidth()*2/3,Gdx.graphics.getHeight()/2);
        /*if (Gdx.input.justTouched()) {

            //text.draw(sb, "Trykket (" + Gdx.input.getX() + ", " + Gdx.input.getY() + ")" , Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/2);
            if (boundsPlay.contains(Gdx.input.getX(), Gdx.input.getY())) {
                text.draw(sb, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
                //controller.navigateToView("GAME", sb);
                controller.updateDestination("GAME");
                controller.navigateToView(sb);
                controller.changeView("GAME", sb);
                //destination = "GAME";
            }
            else if (boundsHighscore.contains(Gdx.input.getX(), Gdx.input.getY())) {
                text.draw(sb, "Highscore", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
                //controller.navigateToView("HIGHSCORE", sb);
                controller.updateDestination("HIGHSCORE");
            }
            else if (boundsSettings.contains(Gdx.input.getX(), Gdx.input.getY())) {
                text.draw(sb, "Settings", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
            }

        }*/
        //controller.navigateToView(destination, sb);
        //update(Gdx.graphics.getDeltaTime());

    }

    /*public void update(float dt){
        if (Gdx.input.isTouched()) {
            System.out.println("[Gdx.input.getX(), Gdx.input.getY()]");
        }
    }*/

    public void dispose() {
        buttonPlay.dispose();
        //buttonHighscore.dispose();
        //buttonSettings.dispose();
        //text.dispose();
    }
}
