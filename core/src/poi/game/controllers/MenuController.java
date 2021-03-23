package poi.game.controllers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.views.View;
import poi.game.views.GameView;
import poi.game.views.HighscoreView;
import poi.game.views.MenuView;

import java.util.Stack;

public class MenuController {

    //private final Poi game;
    /*
    private GameView gameView = new GameView();
    private MenuView menuView = new MenuView();
    private HighscoreView scoreView = new HighscoreView();
    //private Poi poi = new Poi();
    private String prevDestination;
    private String currDestination = "MENU";
    */
    private Stack<View> views;

    public MenuController(){
        views = new Stack<View>();
    }

    public void push(View view){
        views.push(view);
    }

    public void pop(){
        views.pop().dispose();
    }

    public void set(View view){
        views.pop().dispose();
        views.push(view);
    }

    public void update(float dt){
        views.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        views.peek().render(sb);
    }

    /*
    public MenuController(Poi game) {
        this.game = game;
    }
    */

    /*public String getDestination() {
        return currDestination;
    }

    public void updateDestination(String destination) { // oppdaterer ikke
        currDestination = destination;
        Gdx.app.log("MenuController", "updateDestination " + currDestination);

    }

    public void navigateToView(SpriteBatch sb) {
        Gdx.app.log("MenuController", "navigateToVIEW " + currDestination);
        if (currDestination.equals("GAME")) {
            if (prevDestination != "GAME") {
                gameView.create();
            }
            //prevDestination = "GAME";
            //currDestination = "GAME";
            prevDestination = currDestination;
            gameView.render(sb);
        }
        else if (currDestination.equals("MENU")) {
            //Gdx.app.log("MenuController", "MENU before goToMenu");
            //goToMenu(sb);
            //Gdx.app.log("MenuControler", "MENU");
            if (prevDestination !="MENU") {
                menuView.create();
            }
            //menuView.create();
            //prevDestination = "MENU";
            //currDestination = "MENU";
            prevDestination = currDestination;
            menuView.render(sb);
        }

        else if (currDestination.equals("HIGHSCORE")) {
            if (prevDestination != "HIGHSCORE") {
                scoreView.create();
            }
            //prevDestination = "HIGHSCORE";
            //currDestination = "HIGHSCORE";
            prevDestination = currDestination;
            scoreView.render(sb);
        }
    }*/

    /*public void changeView(String destination, SpriteBatch sb) {
        Gdx.app.log("MenuController", "changeView " + destination);

        if (destination.equals("GAME")) {
            //goToGame(sb);

            if (prevDestination != "GAME") {
                gameView.create();
            }
            prevDestination = "GAME";
            gameView.render(sb);
        }
        else if (destination.equals("MENU")) {
            //Gdx.app.log("MenuController", "MENU before goToMenu");
            //goToMenu(sb);
            //Gdx.app.log("MenuControler", "MENU");
            if (prevDestination !="MENU") {
                menuView.create();
            }
            //menuView.create();
            prevDestination = "MENU";
            menuView.render(sb);
        }

        else if (destination.equals("HIGHSCORE")) {
            //goToHighscore();
            if (prevDestination != "HIGHSCORE") {
                scoreView.create();
            }
            scoreView.render(sb);
            prevDestination = "HIGHSCORE";
        }*/
        /*else if (destination.eqauls("SETTINGS")) {
            goToSettings();
        }
        else {
            game.setView(new MenuView(sb));
        }*/

    //}
    /*public void goToMenu(SpriteBatch sb) {
        Gdx.app.log("MenuController", "GoToMenu");
        if (prevDestination != "MENU") {
            menuView.create(); // må ha med en gang, men den kjøres mange ganger
        }
        menuView.render(sb);
        prevDestination = "MENU";
        //game.setView(new GameView());
    }
    public void goToGame(SpriteBatch sb) {
        if (prevDestination != "GAME") {
            gameView.create(); // må ha med en gang, men den kjøres mange ganger
        }
        gameView.render(sb);
        prevDestination = "GAME";
        //game.setView(new GameView());
    }*//*
    public void goToHighscore() {
        game.setView(new HighscoreView(this, model));
    }
    public void goToSettings() {
        game.setView(new SettingsView(this, model));
    }*/
}
