package poi.game.controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.views.View;

import java.util.Stack;

//Contoller for changing views
public class MenuController {

    private Stack<View> views;

    public MenuController(){
        views = new Stack<View>();
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
}
