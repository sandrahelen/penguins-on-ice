package poi.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import poi.game.Poi;
import poi.game.models.entities.Obstacle;
import poi.game.models.entities.Player;

public class GameView {

    public OrthographicCamera cam;
    private Obstacle obstacle;
    private BitmapFont text;

    private Array<Player> players = new Array<Player>();
    private Array<TextureRegion> currentFrames = new Array<TextureRegion>();


    public GameView(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);
        obstacle = new Obstacle(50,50, "p1-bak.png", 1, 1);
        players.add(new Player(50, 50, "p1-bak.png", 1, 3));
        players.add(new Player(100, 50, "p1-front.png", 1, 3));
        players.add(new Player(0, 50, "p1-hoyre.png", 1, 3));
        players.add(new Player(25, 50, "p1-venstre.png", 1, 3));
        players.add(new Player(75, 50, "p1-skli-bak.png", 1, 3));
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
        for (Player player : players) {
            TextureRegion currentFrame = player.setupSprite();
            currentFrames.add(currentFrame);
        }
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //text.draw(sb, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //sb.draw(obstacle.getSprite(), obstacle.getPosition().x, obstacle.getPosition().y);
        //sb.draw(player.getSprite(), player.getPosition().x, player.getPosition().y);
        for (int i = 0; i < players.size; i++) {
            sb.draw(currentFrames.get(i), players.get(i).getPosition().x, players.get(i).getPosition().y);
        }
        sb.end();
        currentFrames.clear();
    }

    public void dispose() {
        for (Player player : players) {
            player.getTexture().dispose();
        }
    }
}
