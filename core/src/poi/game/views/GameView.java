package poi.game.views;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import poi.game.Map.ObjectCreator;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.TimerTask;
import poi.game.Poi;
import poi.game.WorldContactListener;
import poi.game.controllers.BoostController;
import poi.game.controllers.JoystickController;
import poi.game.controllers.MenuController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.TextureComponent;
import poi.game.models.entitySystems.TimerSystem;


import static poi.game.Poi.HEIGHT;
import static poi.game.Poi.WIDTH;

public class GameView extends View {

    private ECSEngine ecsEngine;
    private World world;
    private final ImmutableArray<Entity> animatedEntities;
    private final OrthographicCamera camera;
    private final GLProfiler profiler;
    private final Box2DDebugRenderer box2DDebugRenderer;
    public final JoystickController joystickController1;
    public final JoystickController joystickController2;
    public final BoostController boostController1;
    public final BoostController boostController2;
    private final AssetManager assetmanager;
    public MapRenderer mapRenderer;
    private ObjectCreator objectCreator;
    private BitmapFont timeFont;

    private Texture buttonPause;
    private Rectangle boundsPause;
    private boolean isPaused;


    public GameView(MenuController controller) {
        super(controller);
        timeFont = new BitmapFont();
        world = new World(new Vector2(0, 200.0f), true);
        world.setContactListener(new WorldContactListener());
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        assetmanager = new AssetManager(new InternalFileHandleResolver());
        assetmanager.setLoader(TiledMap.class, new TmxMapLoader(assetmanager.getFileHandleResolver()));
        assetmanager.load("Map/Map1.tmx", TiledMap.class);




        buttonPause = new Texture("general/buttonPause.png");
        boundsPause = new Rectangle(20, 30 - buttonPause.getHeight()/2, buttonPause.getWidth(), buttonPause.getHeight());

        isPaused = false;


        Box2D.init();

        //Setup Engine
        ecsEngine = new ECSEngine(world, camera);
        joystickController1 = ecsEngine.getGameController();
        joystickController2 = ecsEngine.getGameController();
        boostController1 = ecsEngine.getBoostContoller1();
        boostController2 = ecsEngine.getBoostContoller2();


        //Create Entities
        ecsEngine.createPlayer(200, 300, world, 1);
        ecsEngine.createPlayer(400, 300, world, 2);
        assetmanager.finishLoading();
        objectCreator = new ObjectCreator(assetmanager.get("Map/Map1.tmx", TiledMap.class), ecsEngine, world);

        //For boxbody testing
        profiler = new GLProfiler(Gdx.graphics);
        profiler.enable();
        if (profiler.isEnabled()) {
            box2DDebugRenderer = new Box2DDebugRenderer();

        } else {
            box2DDebugRenderer = null;
        }


        animatedEntities = ecsEngine.getEntitiesFor(Family.all(AnimationComponent.class, BodyComponent.class).get());

    }

    private void boost(){
        //Denne blir kanskje unødvendig hvis vi har controller i movementsystem?
        //velocity += 10;
    }


    private void renderEntity(Entity entity, SpriteBatch sb) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        final TextureComponent textureComponent = ECSEngine.textureMapper.get(entity);
        if (profiler.isEnabled()) {
            box2DDebugRenderer.render(world, camera.combined);
        }
        sb.begin();
        timeFont.draw(sb, ecsEngine.getSystem(TimerSystem.class).getStringTime(), camera.position.x+ WIDTH/2-100, camera.position.y+ HEIGHT/2-40);
        bodyComponent.renderPosition.lerp(bodyComponent.body.getPosition(), Gdx.graphics.getDeltaTime());
        sb.draw(textureComponent.setupSprite(), bodyComponent.body.getPosition().x - bodyComponent.width * 0.5f, bodyComponent.body.getPosition().y - bodyComponent.height * 0.5f, bodyComponent.width, bodyComponent.height);
        sb.end();
    }


    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            if (boostController1.getBoundsBoost().contains(Gdx.input.getX(), Gdx.input.getY())) {
                if (boostController1.getCharge() == 100) {
                    //boost();
                    boostController1.setButtonClicked(true);
                    boostController1.setCharge(0);
                }
                System.out.println("Button1 touched");
            }
            if (boostController2.getBoundsBoost().contains(Gdx.input.getX(), Gdx.input.getY())) {
                if (boostController2.getCharge() == 100) {
                    //boost();
                    boostController2.setButtonClicked(true);
                    boostController2.setCharge(0);
                }
                System.out.println("Button2 touched");
            }
            if (boundsPause.contains(Gdx.input.getX(), Gdx.input.getY())) {
                setIsPaused(true);
                // Change view to SettingsView with this (existing gameView) because then the player do not need to start new game if resumed
                controller.set(new SettingsView(controller, this));
            }
            /*if (joystickController1.getBounds().contains(Gdx.input.getX(), Gdx.input.getY())) {
                System.out.println("Joystick touched");
            }
            else {
                System.out.println("NOT touched");
            }*/

       }
    }

    @Override
    public void update(float dt){
        ecsEngine.update(dt);
        camera.update();
        world.step(dt, 6, 2);
        gamecontroller.handleinput();
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {
        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(camera.combined);
        assetmanager.finishLoading();
        mapRenderer = new OrthogonalTiledMapRenderer(assetmanager.get("Map/Map1.tmx", TiledMap.class), 1f, sb);
        mapRenderer.setView(camera);
        mapRenderer.render();


        /*if(boostController1.getButtonClicked()){
            boostController1.startTimer();
        }*/
        if(boostController1.getCharge() > 99){
            boostController1.setButtonClicked(false);
            boostController1.setCharge(100);
        }
        /*if(boostController2.getButtonClicked()){
            boostController2.startTimer();
        }*/
        if(boostController2.getCharge() > 99){
            boostController2.setButtonClicked(false);
            boostController2.setCharge(100);
        }
        sb.begin();
        sb.draw(buttonPause, camera.position.x - 300, camera.position.y + 200);
        //Draw controller player 1
        sb.draw(joystickController1.base, camera.position.x - 300, camera.position.y - 300, joystickController1.joystick.getWidth()/2, joystickController1.joystick.getHeight()/2);
        sb.draw(joystickController1.background, camera.position.x - 300, camera.position.y - 300, joystickController1.joystick.getWidth()/2, joystickController1.joystick.getHeight()/2);
        sb.draw(joystickController1.joystick, camera.position.x - joystickController1.getPosition().x, camera.position.y - joystickController1.getPosition().y, joystickController1.joystick.getWidth()/2, joystickController1.joystick.getHeight()/2);
        //Draw controller player 2
        sb.draw(joystickController2.base, camera.position.x, camera.position.y - 200, joystickController2.joystick.getWidth()/2, joystickController2.joystick.getHeight()/2);
        sb.draw(joystickController2.background, camera.position.x, camera.position.y - 200, joystickController2.joystick.getWidth()/2, joystickController2.joystick.getHeight()/2);
        sb.draw(joystickController2.joystick, camera.position.x - joystickController2.getPosition().x, camera.position.y - joystickController2.getPosition().y, 50, 50);
        if(boostController1.getButtonClicked()){
            boostController1.startTimer();
            sb.draw(boostController1.getBoostButton(), camera.position.x-100, camera.position.y-100);
        }
        else{
            sb.draw(boostController1.getBoostButtonUnCharged(), camera.position.x-100, camera.position.y-100);
        }
        if(boostController2.getButtonClicked()){
            boostController2.startTimer();
            sb.draw(boostController2.getBoostButton(), camera.position.x, camera.position.y-100);
        }
        else{
            sb.draw(boostController2.getBoostButtonUnCharged(), camera.position.x, camera.position.y-100);
        }

        boostController1.getBoostFont().draw(sb, (int)boostController1.getCharge() + "%", camera.position.x-100, camera.position.y-100);
        boostController2.getBoostFont().draw(sb, (int)boostController2.getCharge() + "%", camera.position.x, camera.position.y-100);
        sb.end();

        for (final Entity entity : animatedEntities) {
            renderEntity(entity, sb);
        }
    }

    @Override
    public void dispose() {
        //boostController1.dispose();
        /*for (final Entity entity : animatedEntities){
            entity.dispose(); // må lage en dispose funksjon for disse(?)
        }*/
        buttonPause.dispose();
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean bool) {
        isPaused = bool;
    }
}