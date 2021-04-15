package poi.game.controllers;

import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import poi.game.Map.ObjectCreator;
import poi.game.Poi;
import poi.game.WorldContactListener;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entitySystems.AnimationSystem;
import poi.game.models.entitySystems.GoalSystem;
import poi.game.models.entitySystems.MovementSystem;
import poi.game.views.GameView;
import poi.game.views.MenuView;

public class GameController {

    private ChangeViewController changeViewController;
    private GameView gameView;
    private ECSEngine ecsEngine;
    private World world;
    private boolean finishLine = false;
    private int countStart = 100;
    private double period = 0.1;

    //private final GLProfiler profiler;
    //private final Box2DDebugRenderer box2DDebugRenderer;
    private final OrthographicCamera camera;
    private final AssetManager assetmanager;
    private final JoystickController joystickController1;
    private final JoystickController joystickController2;
    public final BoostController boostController;
    public final PauseController pauseController;
    private final ObjectCreator objectCreator;

    public GameController(GameView gameView) {
        changeViewController = Poi.getChangeViewController();
        this.gameView = gameView;
        world = new World(new Vector2(0, 20.0f), true);
        world.setContactListener(new WorldContactListener());
        camera = Poi.getCameraGame();
        assetmanager = Poi.getAssetManager();
        assetmanager.load("Map/Map1.tmx", TiledMap.class);
        Box2D.init();
        //Setup Engine
        assetmanager.finishLoading();
        ecsEngine = new ECSEngine(world, camera, assetmanager.get("Map/Map1.tmx", TiledMap.class));
        joystickController1 = ecsEngine.getGameController();
        joystickController2 = ecsEngine.getGameController();
        boostController = ecsEngine.getBoostContoller();
        pauseController = new PauseController();

        //Create Entities
        ecsEngine.createPlayer(200, 300, world, 1);
        ecsEngine.createPlayer(400, 300, world, 2);
        assetmanager.finishLoading();
        objectCreator = new ObjectCreator(assetmanager.get("Map/Map1.tmx", TiledMap.class), ecsEngine, world);

    }

    public void update(float dt){
        ecsEngine.update(dt);
        camera.update();
        world.step(dt, 6, 2);
        boostController.handleInput();
        pauseController.handleInput(gameView);

        if(ecsEngine.getSystem(GoalSystem.class).isFinished() == true){
            ecsEngine.getSystem(AnimationSystem.class).setFinishAnimation();
            ecsEngine.getSystem(MovementSystem.class).setFinishAnimation();
            startTimer();
            if(countStart < 1){
                changeViewController.set(new MenuView());
            }

        }
    }
    public void startTimer(){
        countStart += Gdx.graphics.getRawDeltaTime();
        if(countStart > 0){
            countStart -= period;
        }
    }
    public World getWorld() {return world;}
    public JoystickController getJoystickController1() {return joystickController1;}
    public JoystickController getJoystickController2() {return joystickController2;}
    public BoostController getBoostController() {return boostController;}
    public PauseController getPauseController() {return pauseController;}
    public ECSEngine getECSEngine() {return ecsEngine;}

}
