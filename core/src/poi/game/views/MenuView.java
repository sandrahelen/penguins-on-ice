package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import poi.game.controllers.MenuController;
import poi.game.models.factories.ViewFactory;

public class MenuView implements ViewFactory {

    private MenuController controller;
    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;
    private BitmapFont text;
    private Rectangle boundsPlay;
    private Rectangle boundsHighscore;
    private Rectangle boundsSettings;
    //private Vector2 position;

    public void create() {
        controller = new MenuController();
        buttonPlay = new Texture("button.png");
        buttonHighscore = new Texture("button.png");
        buttonSettings = new Texture("button.png");
        text = new BitmapFont();
        //position = new Vector2(50, 50);
        boundsPlay = new Rectangle(50, 50, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsHighscore = new Rectangle(50, 150, buttonHighscore.getWidth(), buttonHighscore.getHeight());
        boundsSettings = new Rectangle(50, 250, buttonSettings.getWidth(), buttonSettings.getHeight());

    }

    @Override
    public void render(SpriteBatch sb) { // sb virker ikke
        Gdx.app.log("MenuView render", "render start");
        text.setColor(0,0,0,1);
        text.draw(sb, "HEI", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
        //sb.draw(buttonPlay, boundsPlay.x,Gdx.graphics.getHeight() - boundsPlay.y - buttonPlay.getHeight());
        //sb.draw(buttonHighscore, boundsHighscore.x,Gdx.graphics.getHeight() - boundsHighscore.y - buttonHighscore.getHeight());
        //sb.draw(buttonSettings, boundsSettings.x,Gdx.graphics.getHeight() - boundsSettings.y - buttonSettings.getHeight());
        Gdx.app.log("MenuController render", "After draw buttons");

        //text.draw(sb, "MENU", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //text.draw(sb, "Bounds (" + bounds.x + ", " + bounds.y + ")", Gdx.graphics.getWidth()*2/3,Gdx.graphics.getHeight()/2);
        if (Gdx.input.isTouched()) {

            //text.draw(sb, "Trykket (" + Gdx.input.getX() + ", " + Gdx.input.getY() + ")" , Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/2);
            if (boundsPlay.contains(Gdx.input.getX(), Gdx.input.getY())) {
                text.draw(sb, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
                controller.navigateToView("GAME", sb);
            }
            else if (boundsHighscore.contains(Gdx.input.getX(), Gdx.input.getY())) {
                text.draw(sb, "Highscore", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
            }
            else if (boundsSettings.contains(Gdx.input.getX(), Gdx.input.getY())) {
                text.draw(sb, "Settings", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
            }

        }
        //update(Gdx.graphics.getDeltaTime());

    }

    public void update(float dt){
        if (Gdx.input.isTouched()) {
            System.out.println("[Gdx.input.getX(), Gdx.input.getY()]");
        }
    }

    public void dispose() {

    }
}
