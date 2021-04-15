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
import poi.game.controllers.*;
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
    public final JoystickController joystickController1;
    public final JoystickController joystickController2;
    public final BoostController boostController;
    public final PauseController pauseController;
    private final AssetManager assetmanager;
    public MapRenderer mapRenderer;
    //private ObjectCreator objectCreator;
    private BitmapFont timeFont;
    private GameController controller;

    public GameView() {
        super();
        controller = new GameController(this);
        world = controller.getWorld();
        timeFont = new BitmapFont();
        camera = Poi.getCameraGame();
        ecsEngine = controller.getECSEngine();
        assetmanager = Poi.getAssetManager();
        joystickController1 = controller.getJoystickController1();
        joystickController2 = controller.getJoystickController2();
        boostController = controller.getBoostController();
        pauseController = controller.getPauseController();

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

    @Override
    public void update(float dt){
        controller.update(dt);
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
        sb.draw(joystickController1.base, camera.position.x - 300, camera.position.y - 300, joystickController1.joystick.getWidth()/2, joystickController1.joystick.getHeight()/2);
        sb.draw(joystickController1.background, camera.position.x - 300, camera.position.y - 300, joystickController1.joystick.getWidth()/2, joystickController1.joystick.getHeight()/2);
        sb.draw(joystickController1.joystick, camera.position.x - joystickController1.getPosition().x, camera.position.y - joystickController1.getPosition().y, joystickController1.joystick.getWidth()/2, joystickController1.joystick.getHeight()/2);
        //Draw controller player 2
        sb.draw(joystickController2.base, camera.position.x, camera.position.y - 200, joystickController2.joystick.getWidth()/2, joystickController2.joystick.getHeight()/2);
        sb.draw(joystickController2.background, camera.position.x, camera.position.y - 200, joystickController2.joystick.getWidth()/2, joystickController2.joystick.getHeight()/2);
        sb.draw(joystickController2.joystick, camera.position.x - joystickController2.getPosition().x, camera.position.y - joystickController2.getPosition().y, 50, 50);

        boostController.setValues();
        boostController.startTimer();
        //Draw boost button player 1
        if(boostController.getBoostComponent1().getButtonClicked()){
            sb.draw(boostController.getBoostComponent1().getBoostButton(), camera.position.x-100, camera.position.y-150,
                    (float)boostController.boostComponent1.getBoostButton().getWidth()/2, (float)boostController.boostComponent1.getBoostButton().getHeight()/2);
        }
        else{
            sb.draw(boostController.getBoostComponent1().getBoostButtonUnCharged(), camera.position.x-100, camera.position.y-150,
                    (float)boostController.boostComponent1.getBoostButtonUnCharged().getWidth()/2, (float)boostController.boostComponent1.getBoostButtonUnCharged().getHeight()/2);
        }
        boostController.getBoostComponent1().getBoostFont().draw(sb, (int) boostController.getBoostComponent1().getCharge() + "%", camera.position.x-100, camera.position.y-100);
        //Draw boost button player 2
        if(boostController.getBoostComponent2().getButtonClicked()){
            sb.draw(boostController.getBoostComponent2().getBoostButton(), camera.position.x, camera.position.y-150,
                    (float)boostController.boostComponent2.getBoostButton().getWidth()/2, (float)boostController.boostComponent2.getBoostButton().getHeight()/2);
        }
        else{
            sb.draw(boostController.getBoostComponent2().getBoostButtonUnCharged(), camera.position.x, camera.position.y-150,
                    (float)boostController.boostComponent2.getBoostButtonUnCharged().getWidth()/2, (float)boostController.boostComponent2.getBoostButtonUnCharged().getHeight()/2);
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

    public PauseController getPauseController() {return pauseController;}
}