package poi.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import poi.game.controllers.ChangeViewController;
import poi.game.models.clientServer.Datahandler;
import poi.game.models.clientServer.Leaderboard;
import poi.game.views.MenuView;

public class Poi extends Game {

	public static final int WIDTH = 640;
	public static final int HEIGHT = 360;

	private static OrthographicCamera camera;
	private static OrthographicCamera cameraGame;

	private SpriteBatch spriteBatch;
	private static ChangeViewController changeViewController;

	private Leaderboard leaderboard;
	private Datahandler datahandler;

	private static AssetManager assetmanager;


	private static final float FIXED_TIME_STEP = 1/60f;
	private float accumulator;

	private static SoundManager soundManager;
	private static String mapLocation;

	private static boolean tutorial = true;

	public Poi(Leaderboard leaderboard) {
		this.leaderboard = leaderboard;
		datahandler = new Datahandler();
	}

	@Override
	public void create () {
		accumulator = 0;

		Gdx.gl.glClearColor(1, 0, 0, 1);

		camera = new OrthographicCamera(WIDTH, HEIGHT);
		camera.setToOrtho(false, WIDTH, HEIGHT);
		cameraGame = new OrthographicCamera(WIDTH, HEIGHT);

		spriteBatch = new SpriteBatch();
		changeViewController = new ChangeViewController(leaderboard, datahandler);
		// Sett MenuView as first view when opening the app
		changeViewController.push(new MenuView());

		//leaderboard.FirstFireBaseTest();	// Testing Firebase database
		//leaderboard.submitScore("R", 25);	// Try to submit score

		assetmanager = new AssetManager(new InternalFileHandleResolver());
		assetmanager.setLoader(TiledMap.class, new TmxMapLoader(assetmanager.getFileHandleResolver()));

		mapLocation = "Map/Map1.tmx";

		soundManager = SoundManager.getInstance();
		soundManager.play();
	}

	@Override
	public void render(){
		accumulator += Math.min(0.25f,Gdx.graphics.getDeltaTime());
		while(accumulator >= FIXED_TIME_STEP){
			changeViewController.update(FIXED_TIME_STEP);
			accumulator -= FIXED_TIME_STEP;
		}
		Gdx.gl.glClearColor(225/255f, 251/255f, 249/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		changeViewController.render(spriteBatch);
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public static OrthographicCamera getCamera(){
		return camera;
	}

	public static String getMapLocation(){
		return mapLocation;
	}

	public static void setMapLocation(String setMap){ mapLocation = setMap; }

	public static OrthographicCamera getCameraGame(){
		return cameraGame;
	}

	public static ChangeViewController getChangeViewController(){
		return changeViewController;
	}

	public static AssetManager getAssetManager(){return assetmanager;}

	public static SoundManager getSoundManager(){return soundManager;}

	public static boolean getTutorial() {
		return tutorial;
	}

	public static void setTutorial() {
		tutorial = false;
	}
}

