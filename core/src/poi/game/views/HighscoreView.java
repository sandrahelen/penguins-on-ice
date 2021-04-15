package poi.game.views;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import poi.game.Datahandler;
import poi.game.Poi;
import poi.game.controllers.ChangeViewController;
import poi.game.controllers.HighscoreController;
import poi.game.controllers.MenuController;
import poi.game.models.factories.ViewFactory;


public class HighscoreView extends View implements ViewFactory {

    HighscoreController controller;

    private Texture titleHighscore;
    private Texture boardHighscore;
    private Texture buttonMenu;
    private BitmapFont text;
    private Datahandler datahandler;

    public HighscoreView () {
        super();
        controller = new HighscoreController();
        cam = Poi.getCamera();
        titleHighscore = new Texture("general/titleHighscore.png");
        boardHighscore = new Texture("general/boardHighscore.png");
        buttonMenu = new Texture("general/buttonMenu.png");
        text = new BitmapFont();

        datahandler = changeViewController.getDatahandler();
        changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
        changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
        //System.out.println("Highscore " + datahandler.getScores());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleHighscore, Poi.WIDTH/2-titleHighscore.getWidth()/2, Poi.HEIGHT - titleHighscore.getHeight()*2);
        sb.draw(boardHighscore, Poi.WIDTH/2-boardHighscore.getWidth()/2/*/10*/,Poi.HEIGHT*3/16);
        sb.draw(buttonMenu, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/16);
        text.draw(sb, "Name", Poi.WIDTH/10, Poi.HEIGHT-Poi.HEIGHT*3/16);
        text.draw(sb, "Time", Poi.WIDTH/10+Poi.WIDTH/5, Poi.HEIGHT-Poi.HEIGHT*3/16);

        // Printing out scores from Firebase database on to the scoreboard
        int i = Poi.HEIGHT*3/16;
        for (String score : changeViewController.getDatahandler().getScores().keySet()) {
            text.draw(sb, score, Poi.WIDTH/10, Poi.HEIGHT-Poi.HEIGHT*3/16-i);
            text.draw(sb, String.valueOf(changeViewController.getDatahandler().getScores().get(score)), Poi.WIDTH/10+Poi.WIDTH/5, Poi.HEIGHT-Poi.HEIGHT*3/16-i);
            i += 20;
        }
        sb.end();
    }

    public void dispose() {
        titleHighscore.dispose();
        boardHighscore.dispose();
        buttonMenu.dispose();
        text.dispose();
    }
}
