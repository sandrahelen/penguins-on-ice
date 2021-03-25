package poi.game.views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import poi.game.Leaderboard;
import poi.game.Poi;
import poi.game.controllers.MenuController;
import poi.game.models.factories.ViewFactory;


public class HighscoreView extends View implements ViewFactory, Leaderboard {

    private Texture titleHighscore;
    private Texture boardHighscore;
    private Texture buttonMenu;
    private Rectangle boundsMenu;

    public HighscoreView (MenuController controller) {
        super(controller);
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
        titleHighscore = new Texture("titleHighscore.png");
        boardHighscore = new Texture("boardHighscore.png");
        buttonMenu = new Texture("buttonMenu.png");
        boundsMenu = new Rectangle(Poi.WIDTH/4, (Poi.HEIGHT - buttonMenu.getHeight()/2)*15/16 - buttonMenu.getHeight()/2, buttonMenu.getWidth(), buttonMenu.getHeight());

    }
    public void submitScore(String user, int score) {
        Gdx.app.log("Html5Leaderboard", "would have submitted score for user " + user + ": " + score);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.justTouched()){
            if (boundsMenu.contains(Gdx.input.getX(), Gdx.input.getY())) {
                controller.set(new MenuView(controller));
            }
        }

        if (boundsMenu.contains(Gdx.input.getX(), Gdx.input.getY())) {
            Gdx.app.log("MENU", "[" + Gdx.input.getX() + ", " + Gdx.input.getY() +"]");
        }

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        //Gdx.app.log("Highscore", "render");
        sb.setProjectionMatrix(cam.combined);
        submitScore("user1", 50);
        sb.begin();
        sb.draw(titleHighscore, Poi.WIDTH/4, Poi.HEIGHT - titleHighscore.getHeight()*2);
        sb.draw(boardHighscore, Poi.WIDTH/10,Poi.HEIGHT*3/16);
        sb.draw(buttonMenu, Poi.WIDTH/4,Poi.HEIGHT/16);
        sb.end();
    }

    public void dispose() {
        titleHighscore.dispose();
        boardHighscore.dispose();
        buttonMenu.dispose();
    }

}
