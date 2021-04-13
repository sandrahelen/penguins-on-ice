package poi.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.controllers.ChangeViewController;
import poi.game.views.MenuView;

public class Poi extends Game {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;

	private static OrthographicCamera camera;
	private static OrthographicCamera cameraGame;

	private SpriteBatch spriteBatch;
	private static ChangeViewController controller;

	private Leaderboard leaderboard;
	private Datahandler datahandler;

	public Poi(Leaderboard leaderboard) {
		this.leaderboard = leaderboard;
		datahandler = new Datahandler();
	}

	@Override
	public void create () {
		Gdx.gl.glClearColor(1, 0, 0, 1);

		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);
		cameraGame = new OrthographicCamera(WIDTH, HEIGHT);

		spriteBatch = new SpriteBatch();
		controller = new ChangeViewController(leaderboard, datahandler);
		// Sett MenuView as first view when opening the app
		controller.push(new MenuView(controller));

		leaderboard.FirstFireBaseTest();	// Testing Firebase database
		leaderboard.submitScore("R", 25);
	}

	@Override
	public void render(){
		Gdx.gl.glClearColor(225/255f, 251/255f, 249/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		controller.update(Gdx.graphics.getDeltaTime());
		controller.render(spriteBatch);
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public static OrthographicCamera getCamera(){
		return camera;
	}

	public static OrthographicCamera getCameraGame(){
		return cameraGame;
	}

	public static ChangeViewController getController(){
		return controller;
	}
}

