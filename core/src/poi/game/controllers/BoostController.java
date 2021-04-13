package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BoostController {

    public Texture boostButton;
    public Texture boostButtonUnCharged;
    private Rectangle boundsBoost;
    private BitmapFont boostFont;
    private float charge = 100;
    private double period = 0.1;
    private boolean buttonClicked = false;


    public BoostController(float startPosX, float startPosY) {
        boostButton = new Texture("boost/transparentDark47.png");
        boostButtonUnCharged = new Texture("boost/shadedDark49.png");
        boundsBoost = new Rectangle(startPosX, startPosY, boostButton.getWidth(), boostButton.getHeight());
        boostFont = new BitmapFont();
    }


    public Rectangle getBoundsBoost(){return boundsBoost;}

    public Texture getBoostButton(){return boostButton;}

    public Texture getBoostButtonUnCharged(){return boostButtonUnCharged;}


    public BitmapFont getBoostFont() {
        return boostFont;
    }

    public boolean getButtonClicked(){return buttonClicked;}

    public void setButtonClicked(boolean buttonClicked){
        this.buttonClicked = buttonClicked;
    }

    public float getCharge() {
        return charge;
    }
    public void setCharge(float charge){
        this.charge = charge;
    }


    public void startTimer(){
        charge += Gdx.graphics.getRawDeltaTime();
        if(charge < 100){
            charge += period;
        }
    }



}
