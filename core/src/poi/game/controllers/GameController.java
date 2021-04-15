package poi.game.controllers;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import poi.game.Map.ObjectCreator;
import poi.game.Poi;
import poi.game.WorldContactListener;
import poi.game.models.ECSEngine;
import poi.game.models.entitySystems.AnimationSystem;
import poi.game.models.entitySystems.GoalSystem;
import poi.game.models.entitySystems.MovementSystem;
import poi.game.models.entitySystems.TimerSystem;
import poi.game.views.EndGameView;
import poi.game.views.GameView;

public class GameController {

    private ChangeViewController changeViewController;
    private GameView gameView;
    private ECSEngine ecsEngine;
    private World world;
    private boolean finishLine = false;
    private int countStart = 100;
    private double period = 0.1;
    private final OrthographicCamera camera;
    private final AssetManager assetmanager;
    private final JoystickController joystickController;
    public final BoostController boostController;
    public final PauseController pauseController;
    private final ObjectCreator objectCreator;
    private final SoundController soundController;

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
        joystickController = ecsEngine.getJoystickController();
        boostController = ecsEngine.getBoostContoller();
        pauseController = new PauseController();

        //Create Entities
        ecsEngine.createPlayer(200, 300, world, 1);
        ecsEngine.createPlayer(400, 300, world, 2);
        assetmanager.finishLoading();
        objectCreator = new ObjectCreator(assetmanager.get("Map/Map1.tmx", TiledMap.class), ecsEngine, world);

        soundController = Poi.getSoundController();
        soundController.play();

    }

    public void update(float dt){
        ecsEngine.update(dt);
        camera.update();
        world.step(dt, 6, 2);
        joystickController.handleInput();
        boostController.handleInput();
        pauseController.handleInput(gameView);

        if(ecsEngine.getSystem(GoalSystem.class).isFinished() == true){
            ecsEngine.getSystem(AnimationSystem.class).setFinish();
            ecsEngine.getSystem(MovementSystem.class).setFinish();
            ecsEngine.getSystem(TimerSystem.class).setFinish();
            startTimer();
            if(countStart < 1){
                changeViewController.set(new EndGameView(ecsEngine.getSystem(TimerSystem.class).getTime()));
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
    public JoystickController getJoystickController() {return joystickController;}
    public BoostController getBoostController() {return boostController;}
    public PauseController getPauseController() {return pauseController;}
    public ECSEngine getECSEngine() {return ecsEngine;}
    public SoundController getSoundController() {return soundController;}

}
