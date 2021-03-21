package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import poi.game.Poi;
import poi.game.models.entities.Obstacle;
import poi.game.models.entities.Player;

public class GameView {

    public OrthographicCamera cam;

    private Array<Player> players = new Array<Player>();
    private Array<Obstacle> obstacles = new Array<Obstacle>();
    private Array<TextureRegion> playerFrames = new Array<TextureRegion>();
    private Array<TextureRegion> obstacleFrames = new Array<TextureRegion>();


    public GameView(){
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Poi.WIDTH, Poi.HEIGHT);

        for (int i = 0; i < 10; i ++) {
            obstacles.add(new Obstacle((float)((Math.random() * (Poi.WIDTH - 0)) + 0),(float)((Math.random() * (Poi.HEIGHT - 0)) + 0), "ice-sprites.png", 4, 3));
        }
        players.add(new Player(400, 250, "p1-bak.png", 1, 3));
        players.add(new Player(250, 250, "p1-bak.png", 1, 3));
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
            TextureRegion playerFrame = player.getAnimation();
            playerFrames.add(playerFrame);
        }
        for (Obstacle obstacle : obstacles) {
            TextureRegion obstacleFrame = obstacle.getAnimation();
            obstacleFrames.add(obstacleFrame);
        }

        //Player 1
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_LEFT))
            players.get(0).setPosition(-(Gdx.graphics.getDeltaTime() * players.get(0).getVelocity().x), 0);
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_RIGHT))
            players.get(0).setPosition(Gdx.graphics.getDeltaTime() * players.get(0).getVelocity().x, 0);

        //Player 2
        if(Gdx.input.isKeyPressed(Input.Keys.A))
            players.get(1).setPosition(-(Gdx.graphics.getDeltaTime() * players.get(0).getVelocity().x), 0);
        if(Gdx.input.isKeyPressed(Input.Keys.D))
            players.get(1).setPosition(Gdx.graphics.getDeltaTime() * players.get(0).getVelocity().x, 0);

        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        //text.draw(sb, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        //sb.draw(obstacle.getSprite(), obstacle.getPosition().x, obstacle.getPosition().y);
        //sb.draw(player.getSprite(), player.getPosition().x, player.getPosition().y);
        for (int i = 0; i < players.size; i++) {
            sb.draw(playerFrames.get(i), players.get(i).getPosition().x, players.get(i).getPosition().y);
        }
        for (int i = 0; i < obstacles.size; i++) {
            sb.draw(obstacleFrames.get(i), obstacles.get(i).getPosition().x, obstacles.get(i).getPosition().y);
        }
        sb.end();
        obstacleFrames.clear();
        playerFrames.clear();
    }

    public void dispose() {
        for (Player player : players) {
            player.getTexture().dispose();
        }
        for (Obstacle obstacle : obstacles) {
            obstacle.getTexture().dispose();
        }
    }
}
