package poi.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.Poi;
import poi.game.models.entities.Obstacle;
import poi.game.models.entities.Player;

public class GameView {

    public OrthographicCamera cam;
    private Obstacle obstacle;
    private Player player;
    private BitmapFont text;


    public GameView(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
        obstacle = new Obstacle(50,50, "p1-bak.png", 1, 1);
        player = new Player(100,70,"p1-bak.png", 1,1);
    }


    /*public void update(float dt){
        obstacle.update(dt);
        player.update(dt);
        cam.update();
    }*/

    /*public void create() {
        text = new BitmapFont();
    }*/

    public void render(SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //text.draw(sb, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //sb.draw(obstacle.getSprite(), obstacle.getPosition().x, obstacle.getPosition().y);
        sb.draw(obstacle.getSprite(), obstacle.getPosition().x, obstacle.getPosition().y);
        //sb.draw(player.getSprite(), player.getPosition().x, player.getPosition().y);
        sb.end();
    }

    //Setup for sprite and frames
    /*public TextureRegion setupSprite(Player player) {
        player.setStateTime(Gdx.graphics.getDeltaTime());
        return player.getAnimation().getKeyFrame(player.getStateTime(), true);
    }*/

}
