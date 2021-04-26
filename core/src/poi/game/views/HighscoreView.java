package poi.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.models.clientServer.Datahandler;

import poi.game.Poi;
import poi.game.controllers.HighscoreController;

// View for highscore borad 
public class HighscoreView extends View {

    private HighscoreController controller;
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
        buttonMenu = controller.getButtonMenu();
        text = new BitmapFont();
        text.setColor(Color.BLACK);

        datahandler = changeViewController.getDatahandler();
        changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
        changeViewController.getLeaderboard().setOnValueChangedListener(datahandler);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(titleHighscore, Poi.WIDTH/2-titleHighscore.getWidth()/2, Poi.HEIGHT - titleHighscore.getHeight()*2);
        sb.draw(boardHighscore, Poi.WIDTH/2-boardHighscore.getWidth()/2/*/10*/,Poi.HEIGHT*3/16);
        sb.draw(buttonMenu, Poi.WIDTH/2-controller.getButtonWidth()/2,Poi.HEIGHT/16);
        text.draw(sb, "NAME", Poi.WIDTH/3, Poi.HEIGHT-Poi.HEIGHT*4/16);
        text.draw(sb, "TIME", Poi.WIDTH/3+Poi.WIDTH/5, Poi.HEIGHT-Poi.HEIGHT*4/16);

        // Printing out scores from Firebase database on to the scoreboard
        int i = 20;
        for (String score : changeViewController.getDatahandler().getScores().keySet()) {
            text.draw(sb, score, Poi.WIDTH/3, Poi.HEIGHT-Poi.HEIGHT*5/16-i);
            text.draw(sb, String.valueOf(changeViewController.getDatahandler().getScores().get(score)), Poi.WIDTH/3+Poi.WIDTH/5, Poi.HEIGHT-Poi.HEIGHT*5/16-i);
            i += 25;
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
