package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import poi.game.models.factories.ViewFactory;

public class MenuView implements ViewFactory {

    private Texture playButton;
    private BitmapFont text;
    private Rectangle bounds;

    public void create() {
        playButton = new Texture("button.png");
        text = new BitmapFont();
        bounds = new Rectangle(50, 50, playButton.getWidth(), playButton.getHeight());
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.draw(playButton, 50, 50);
        text.setColor(0,0,0,1);
        text.draw(sb, "MENU", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        text.draw(sb, "Bounds (" + bounds.x + ", " + bounds.y + ")", Gdx.graphics.getWidth()*2/3,Gdx.graphics.getHeight()/2);
        if (Gdx.input.isTouched()) {
            text.draw(sb, "Trykket (" + Gdx.input.getX() + ", " + Gdx.input.getY() + ")" , Gdx.graphics.getWidth()/3,Gdx.graphics.getHeight()/2);
            if (bounds.contains(Gdx.input.getX(), Gdx.input.getY())) {
                text.draw(sb, "Boks", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/3);
            }

        }
        //update(Gdx.graphics.getDeltaTime());

    }

    public void update(float dt){
        if (Gdx.input.isTouched()) {
            System.out.println("[Gdx.input.getX(), Gdx.input.getY()]");
        }
    }

    public void dispose() {

    }
}
