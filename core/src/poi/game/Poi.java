package poi.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.controllers.MenuController;
import poi.game.views.MenuView;
import poi.game.views.View;


public class Poi extends Game {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private static final String TAG = Poi.class.getSimpleName();
	private SpriteBatch spriteBatch;
	private SpriteBatch batch;
	//Texture img;
	private BitmapFont text;
	private MenuController controller;
	private View view;
	private Texture playButton;

	private final Leaderboard leaderboard;

	public Poi(Leaderboard leaderboard) {
		this.leaderboard = leaderboard;
	}


	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
		controller = new MenuController();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		controller.push(new MenuView(controller));

		leaderboard.FirstFireBaseTest();	// Testing Firebase database
		leaderboard.submitScore("Royce", 50);	// Submitting score to database
		leaderboard.submitScore("Moira", 600);
		leaderboard.submitScore("Marit", 30);
		leaderboard.submitScore("Sandra", 0);
		leaderboard.submitScore("Endre", 700);	// Not displaying when printed, ok
		leaderboard.submitScore("Rose", 3);
		leaderboard.setOnValueChangedListener();	// Limited database to display the 5 lowest scores
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(225/255f, 251/255f, 249/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		controller.update(Gdx.graphics.getDeltaTime());
		controller.render(spriteBatch);
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}

}

