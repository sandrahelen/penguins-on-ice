package poi.game.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private BitmapFont font = new BitmapFont();

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
        players.add(new Player(250, 100, "p1-bak.png", 1, 3));
    }

    public void update(float dt){
        if (players.get(0).getPosition().y < players.get(1).getPosition().y){
            cam.position.y = players.get(0).getPosition().y + 100;
        }
        else if(players.get(1).getPosition().y < players.get(0).getPosition().y){
            cam.position.y = players.get(1).getPosition().y + 100;
        }
        /*if (players.get(0).getPosition().y - players.get(1).getPosition().y > 100){
            //players.get(0) må vente
        }
        else if (players.get(1).getPosition().y - players.get(0).getPosition().y > 100){
            //players.get(1) må vente
        }*/
        cam.update();
        /*obstacle.update(dt);*/
        players.get(0).update(dt, cam);
        players.get(1).update(dt, cam);
    }

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

        this.update(Gdx.graphics.getDeltaTime());

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
        font.draw(sb, "P1X: " + players.get(0).getPosition().x + " P1Y: " + players.get(0).getPosition().y, 10,/*Gdx.graphics.getHeight() +*/ cam.position.y);
        font.draw(sb, "P2X: " + players.get(1).getPosition().x + " P2Y: " + players.get(1).getPosition().y, 10,/*Gdx.graphics.getHeight() +*/ cam.position.y - 20);
        //System.out.println("Player 1: " + players.get(0).getPosition());
        //System.out.println("Player 2: " + players.get(1).getPosition());
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
