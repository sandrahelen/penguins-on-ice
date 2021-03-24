package poi.game.controllers;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.views.View;

import java.util.Stack;

public class MenuController {

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
}
