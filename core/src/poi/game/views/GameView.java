package poi.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import poi.game.Map.ObjectCreator;

import poi.game.Poi;
import poi.game.WorldContactListener;
import poi.game.controllers.BoostController;
import poi.game.controllers.PauseController;
import poi.game.controllers.JoystickController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.TextureComponent;
import poi.game.models.entitySystems.GoalSystem;
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
    public final JoystickController joystickController;
    public final BoostController boostController;
    public final PauseController pauseController;
    private final AssetManager assetmanager;
    public MapRenderer mapRenderer;
    private ObjectCreator objectCreator;
    private BitmapFont timeFont;

    public GameView() {
        super();
        timeFont = new BitmapFont();
        world = new World(new Vector2(0, 200.0f), true);
        world.setContactListener(new WorldContactListener());
        camera = Poi.getCameraGame();
        assetmanager = new AssetManager(new InternalFileHandleResolver());
        assetmanager.setLoader(TiledMap.class, new TmxMapLoader(assetmanager.getFileHandleResolver()));
        assetmanager.load("Map/Map1.tmx", TiledMap.class);

        pauseController = new PauseController();

        Box2D.init();

        //Setup Engine
        assetmanager.finishLoading();
        ecsEngine = new ECSEngine(world, camera, assetmanager.get("Map/Map1.tmx", TiledMap.class));
        joystickController = ecsEngine.getJoystickController();
        boostController = ecsEngine.getBoostContoller();

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

    protected void handleInput() {
        /*if (Gdx.input.justTouched()) {
            if (boundsPause.contains(Gdx.input.getX(), Gdx.input.getY())) {
                setIsPaused(true);
                // Change view to SettingsView with this (existing gameView) because then the player do not need to start new game if resumed
                changeViewController.set(new SettingsView(changeViewController, this));
            }
            if (joystickController1.getBounds().contains(Gdx.input.getX(), Gdx.input.getY())) {
                System.out.println("Joystick touched");
            }
            else {
                System.out.println("NOT touched");
            }

       }*/
    }

    @Override
    public void update(float dt){
        ecsEngine.update(dt);
        camera.update();
        world.step(dt, 6, 2);
        joystickController.handleInput();
        boostController.handleInput();
        pauseController.handleInput( this);
        //handleInput();

        if(ecsEngine.getSystem(GoalSystem.class).isFinished() == true){
            changeViewController.set(new MenuView());
        }
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

        sb.begin();

        sb.draw(pauseController.getButtonPause(), camera.position.x - 290, camera.position.y + 140);
        //Draw controller player 1
        sb.draw(joystickController.getJoystick1().getJoystickBase(), camera.position.x - 300, camera.position.y - 150,
                (float)joystickController.joystick1.joystickBase.getWidth()/2,
                (float)joystickController.joystick1.joystickBase.getHeight()/2);
        if (joystickController.joystick1.getJoystickTouched() && joystickController.joystick1.getMoveLeft()) {
            sb.draw(joystickController.getJoystick1().getJoystick(), camera.position.x - 300, camera.position.y - 135,
                    (float)joystickController.joystick1.joystick.getWidth()/2,
                    (float)joystickController.joystick1.joystick.getHeight()/2);
        }
        else if (joystickController.joystick1.getJoystickTouched() && !joystickController.joystick1.getMoveLeft()) {
            sb.draw(joystickController.getJoystick1().getJoystick(), camera.position.x - 270, camera.position.y - 135,
                    (float)joystickController.joystick1.joystick.getWidth()/2,
                    (float)joystickController.joystick1.joystick.getHeight()/2);
        }
        else {
            sb.draw(joystickController.getJoystick1().getJoystick(), camera.position.x - 285, camera.position.y - 135,
                    (float)joystickController.joystick1.joystick.getWidth()/2,
                    (float)joystickController.joystick1.joystick.getHeight()/2);
        }
        //Draw controller player 2
        sb.draw(joystickController.getJoystick2().getJoystickBase(), camera.position.x + 220, camera.position.y - 150,
                (float)joystickController.joystick2.joystickBase.getWidth()/2,
                (float)joystickController.joystick2.joystickBase.getHeight()/2);
        if (joystickController.joystick2.getJoystickTouched() && joystickController.joystick2.getMoveLeft()) {
            sb.draw(joystickController.getJoystick2().getJoystick(), camera.position.x + 220, camera.position.y - 135,
                    (float)joystickController.joystick2.joystick.getWidth()/2,
                    (float)joystickController.joystick2.joystick.getHeight()/2);
        }
        else if (joystickController.joystick2.getJoystickTouched() && !joystickController.joystick2.getMoveLeft()) {
            sb.draw(joystickController.getJoystick2().getJoystick(), camera.position.x + 250, camera.position.y - 135,
                    (float)joystickController.joystick2.joystick.getWidth()/2,
                    (float)joystickController.joystick2.joystick.getHeight()/2);
        }
        else {
            sb.draw(joystickController.getJoystick2().getJoystick(), camera.position.x + 235, camera.position.y - 135,
                    (float)joystickController.joystick2.joystick.getWidth()/2,
                    (float)joystickController.joystick2.joystick.getHeight()/2);
        }

        boostController.setValues();
        boostController.startTimer();
        //Draw boost button player 1
        if(boostController.getBoostComponent1().getButtonClicked()){
            sb.draw(boostController.getBoostComponent1().getBoostButton(), camera.position.x-100, camera.position.y-100);
        }
        else{
            sb.draw(boostController.getBoostComponent1().getBoostButtonUnCharged(), camera.position.x-100, camera.position.y-100);
        }
        boostController.getBoostComponent1().getBoostFont().draw(sb, (int) boostController.getBoostComponent1().getCharge() + "%", camera.position.x-100, camera.position.y-100);
        //Draw boost button player 2
        if(boostController.getBoostComponent2().getButtonClicked()){
            sb.draw(boostController.getBoostComponent2().getBoostButton(), camera.position.x, camera.position.y-100);
        }
        else{
            sb.draw(boostController.getBoostComponent2().getBoostButtonUnCharged(), camera.position.x, camera.position.y-100);
        }
        boostController.getBoostComponent2().getBoostFont().draw(sb, (int) boostController.getBoostComponent2().getCharge() + "%", camera.position.x, camera.position.y-100);

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
        //buttonPause.dispose();
    }

    /*public boolean isPaused() {
        return isPaused;
    }

    public void setIsPaused(boolean bool) {
        isPaused = bool;
    }*/
    public PauseController getPauseController(){return pauseController;}
}