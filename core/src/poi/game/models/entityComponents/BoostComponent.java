package poi.game.models.entityComponents;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class BoostComponent {

    public Texture boostButton;
    public Texture boostButtonUnCharged;
    private Rectangle boundsBoost;
    private BitmapFont boostFont;
    private float charge = 100;
    private boolean buttonClicked = false;
    private boolean boost = false;


    public BoostComponent(float startPosX, float startPosY) {
        boostButton = new Texture("boost/transparentDark47.png");
        boostButtonUnCharged = new Texture("boost/shadedDark49.png");
        boundsBoost = new Rectangle(startPosX, startPosY, boostButton.getWidth()/2, boostButton.getHeight()/2);
        boostFont = new BitmapFont();
    }

    public void startTimer(float dt){
        charge += dt;
        if(charge < 100){
            charge += dt*10;
        }
    }

    public void setBoost(boolean boost){this.boost = boost;}
    public boolean getBoost(){return boost;}

    public Rectangle getBoundsBoost(){return boundsBoost;}

    public Texture getBoostButton(){return boostButton;}

    public Texture getBoostButtonUnCharged(){return boostButtonUnCharged;}

    public BitmapFont getBoostFont(){return boostFont;}

    public boolean getButtonClicked(){return buttonClicked;}

    public void setButtonClicked(boolean buttonClicked){this.buttonClicked = buttonClicked;}

    public float getCharge(){return charge;}

    public void setCharge(float charge){this.charge = charge;}

}
