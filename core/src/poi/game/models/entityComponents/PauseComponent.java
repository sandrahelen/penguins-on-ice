package poi.game.models.entityComponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import poi.game.Poi;

public class PauseComponent {

    private Texture buttonPause;
    private Rectangle boundsPause;
    private boolean isPaused = false;

    public PauseComponent(){
        buttonPause = new Texture("general/buttonPause.png");
        boundsPause = new Rectangle(30, Poi.HEIGHT - 30 - buttonPause.getHeight()/2, buttonPause.getWidth(), buttonPause.getHeight());
    }

    public Rectangle getBoundsPause(){return boundsPause;}

    public void setPaused(boolean paused){this.isPaused = paused;}

    public boolean getIsPaused(){return isPaused;}

    public Texture getButtonPause(){return buttonPause;}
}
