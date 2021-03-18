package poi.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.Poi;
import poi.game.models.entities.Obstacle;
import poi.game.models.entities.Player;

public class GameView {

    public OrthographicCamera cam;
    private Obstacle obstacle;
    private Player player;

    public GameView(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
        obstacle = new Obstacle(50, 10, "badlogic.jpg", 1,1);
        player = new Player(0,0,"p1-bak.png", 1,3);
    }

    public void update(float dt){
        obstacle.update(dt);
        cam.update();
    }

    public void render(SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(obstacle.getTexture(), obstacle.getPosition().x, obstacle.getPosition().y);
        sb.end();
    }
}
