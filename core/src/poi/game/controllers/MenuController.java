package poi.game.controllers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.views.GameView;
import poi.game.views.MenuView;

public class MenuController {

    //private final Poi game;
    private GameView gameView = new GameView();
    private MenuView menuView = new MenuView();
    /*
    public MenuController(Poi game) {
        this.game = game;
    }
    */
    public void navigateToView(String destination, SpriteBatch sb) {
        Gdx.app.log("MenuController", "navigateToVIEW");
        if (destination.equals("GAME")) {
            goToGame(sb);

        }
        else if (destination.equals("MENU")) {
            Gdx.app.log("MenuController", "MENU before goToMenu");
            goToMenu(sb);
            Gdx.app.log("MenuControler", "MENU");
        }
        /*
        else if (destination.eqauls("HIGHSCORE")) {
            goToHighscore();
        }
        else if (destination.eqauls("SETTINGS")) {
            goToSettings();
        }*/
        /*else {
            game.setView(new MenuView(sb));
        }*/

    }
    public void goToMenu(SpriteBatch sb) {
        Gdx.app.log("MenuController", "GoToMenu");
        menuView.create(); // må ha med en gang, men den kjøres mange ganger
        menuView.render(sb);
        //game.setView(new GameView());
    }
    public void goToGame(SpriteBatch sb) {
        gameView.render(sb);
        //game.setView(new GameView());
    }/*
    public void goToHighscore() {
        game.setView(new HighscoreView(this, model));
    }
    public void goToSettings() {
        game.setView(new SettingsView(this, model));
    }*/
}
