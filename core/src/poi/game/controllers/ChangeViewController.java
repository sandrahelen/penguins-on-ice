package poi.game.controllers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

import poi.game.Datahandler;
import poi.game.Leaderboard;
import poi.game.views.View;

public class ChangeViewController {

    private Stack<View> views;
    private Leaderboard leaderboard;
    private Datahandler datahandler;

    public ChangeViewController(Leaderboard leaderboard, Datahandler datahandler){
        views = new Stack<View>();
        this.leaderboard = leaderboard;
        this.datahandler = datahandler;
    }

    // Add new view
    public void push(View view){
        views.push(view);
    }

    // Remove view
    public void pop(){
        views.pop().dispose();
    }

    // Change to new view
    public void set(View view){
        views.pop().dispose();
        views.push(view);
    }

    // Update chosen view
    public void update(float dt){
        views.peek().update(dt);
    }

    // Render chosen view
    public void render(SpriteBatch sb){
        views.peek().render(sb);
    }

    public Leaderboard getLeaderboard() {
        return leaderboard;
    }
    public Datahandler getDatahandler() {
        return datahandler;
    }
}
