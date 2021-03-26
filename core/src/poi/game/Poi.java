package poi.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.ReflectionException;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.EnumMap;

import poi.game.models.ECSEngine;
import poi.game.views.GameRenderer;
import poi.game.views.ScreenType;

public class Poi extends Game {
	private static final String TAG = Poi.class.getSimpleName();

	private EnumMap<ScreenType, Screen> screenCache;
	private World world;

	private ECSEngine ecsEngine;
	private SpriteBatch spriteBatch;
	private GameRenderer gameRenderer;
	private OrthographicCamera camera;
	FitViewport viewport;







	@Override
	public void create () {
		screenCache = new EnumMap<ScreenType, Screen>(ScreenType.class);
		spriteBatch = new SpriteBatch();

		Box2D.init();
		world = new World(new Vector2(0,-9.81f), true);

		camera = new OrthographicCamera();
		viewport = new FitViewport(800,450, camera);


		ecsEngine = new ECSEngine(this);
		gameRenderer = new GameRenderer(this);

		setScreen(ScreenType.TEST);

	}

	@Override
	public void render(){
		super.render();
		viewport.apply(false);

		gameRenderer.render(Gdx.graphics.getDeltaTime());
		ecsEngine.update(Gdx.graphics.getDeltaTime());

	}

	@Override
	public void resize(final int width, final int height){
		viewport.update(width, height, true);
	}

	public void setScreen(final ScreenType screenType){
		final Screen screen = screenCache.get(screenType);
		if(screen == null){
			try {
				Gdx.app.debug(TAG, "Creating new Screen"+ screenType);
				final Screen newScreen = (Screen) ClassReflection.getConstructor(screenType.getScreenClass(), Poi.class).newInstance(this);
				screenCache.put(screenType, newScreen);
				setScreen(newScreen);
			} catch (ReflectionException e) {
				throw new GdxRuntimeException("Screen" + screenType + "could not be created", e);
			}
		} else{
			Gdx.app.debug(TAG, "Switching to screen"+ screenType);
			setScreen(screen);
		}
	}

	public ECSEngine getEcsEngine() {
		return ecsEngine;
	}

	public World getWorld() {
		return world;
	}

	public SpriteBatch getSpriteBatch(){
		return spriteBatch;
	}

	public GameRenderer getRenderSystem(){
		return gameRenderer;
	}


	public OrthographicCamera getCamera(){return camera;}

	public FitViewport getViewport() {
		return viewport;
	}
}

