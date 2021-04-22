package poi.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import poi.game.Poi;
import poi.game.controllers.BoostController;
import poi.game.controllers.GameController;
import poi.game.controllers.PauseController;
import poi.game.controllers.JoystickController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.TextureComponent;
import poi.game.models.entitySystems.GoalSystem;
import poi.game.models.entitySystems.TimerSystem;
import poi.game.models.factories.ViewFactory;

import static poi.game.Poi.HEIGHT;
import static poi.game.Poi.WIDTH;

// View for game
public class GameView extends View implements ViewFactory {

    private GameController controller;
    private World world;
    private BitmapFont timeFont;
    private BitmapFont timeFontBig;
    private final OrthographicCamera camera;
    private ECSEngine ecsEngine;
    private final AssetManager assetmanager;
    public final JoystickController joystickController;
    public final BoostController boostController;
    public final PauseController pauseController;
    private final GLProfiler profiler;
    private final Box2DDebugRenderer box2DDebugRenderer;
    private final ImmutableArray<Entity> animatedEntities;
    public MapRenderer mapRenderer;

    public GameView() {
        super();
        controller = new GameController(this);
        world = controller.getWorld();
        timeFont = new BitmapFont();
        timeFontBig = new BitmapFont();
        timeFontBig.getData().setScale(2.0f);
        camera = Poi.getCameraGame();
        ecsEngine = controller.getECSEngine();  // ECS pattern
        assetmanager = Poi.getAssetManager();
        joystickController = controller.getJoystickController();
        boostController = controller.getBoostController();
        pauseController = controller.getPauseController();

        //For boxbody testing
        profiler = new GLProfiler(Gdx.graphics);
        profiler.disable();
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
        mapRenderer = new OrthogonalTiledMapRenderer(assetmanager.get(Poi.getMapLocation(), TiledMap.class), 1f, sb);
        mapRenderer.setView(camera);
        mapRenderer.render();

        sb.begin();
        //Draw pausebutton
        sb.draw(pauseController.getButtonPause(), camera.position.x - 290, camera.position.y + 140);

        //Draw joystick-controller player 1
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
        //Draw joystick-controller player 2
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

        //Boost controllers
        boostController.setValues();
        //Draw boost button player 1
        if(boostController.getBoostComponent1().getButtonClicked()){
            sb.draw(boostController.getBoostComponent1().getBoostButton(), camera.position.x-280, camera.position.y-50,
                    (float)boostController.getBoostComponent1().getBoostButton().getWidth()/2,
                    (float)boostController.getBoostComponent1().getBoostButton().getHeight()/2);
        }
        else{
            sb.draw(boostController.getBoostComponent1().getBoostButtonUnCharged(), camera.position.x-280, camera.position.y-50,
                    (float)boostController.getBoostComponent1().getBoostButtonUnCharged().getWidth()/2,
                    (float)boostController.getBoostComponent1().getBoostButtonUnCharged().getHeight()/2);
        }
        boostController.getBoostComponent1().getBoostFont().draw(sb, (int) boostController.getBoostComponent1().getCharge() + "%", camera.position.x-230, camera.position.y-30);

        //Draw boost button player 2
        if(boostController.getBoostComponent2().getButtonClicked()){
            sb.draw(boostController.getBoostComponent2().getBoostButton(), camera.position.x+240, camera.position.y-50,
                    (float)boostController.getBoostComponent2().getBoostButton().getWidth()/2,
                    (float)boostController.getBoostComponent2().getBoostButton().getHeight()/2);
        }
        else{
            sb.draw(boostController.getBoostComponent2().getBoostButtonUnCharged(), camera.position.x+240, camera.position.y-50,
                    (float)boostController.getBoostComponent2().getBoostButtonUnCharged().getWidth()/2,
                    (float)boostController.getBoostComponent2().getBoostButtonUnCharged().getHeight()/2);
        }
        boostController.getBoostComponent2().getBoostFont().draw(sb, (int) boostController.getBoostComponent2().getCharge() + "%", camera.position.x+195, camera.position.y-30);

        //Draw time
        if(ecsEngine.getSystem(GoalSystem.class).isFinished()){
            timeFontBig.draw(sb, ecsEngine.getSystem(TimerSystem.class).getStringTime(), camera.position.x-70,camera.position.y);
        }
        else{
            timeFont.draw(sb, ecsEngine.getSystem(TimerSystem.class).getStringTime(), camera.position.x+ WIDTH/2-100, camera.position.y+ HEIGHT/2-40);
            timeFont.draw(sb, String.valueOf(Gdx.graphics.getFramesPerSecond()), camera.position.x, camera.position.y+ HEIGHT/2-40);
        }
        sb.end();

        for (final Entity entity : animatedEntities) {
            renderEntity(entity, sb);
        }
    }

    @Override
    public void dispose() {
        /*timeFont.dispose();
        timeFontBig.dispose();
        pauseController.getButtonPause().dispose();
        joystickController.getJoystick1().getJoystickBase().dispose();
        joystickController.getJoystick1().getJoystick().dispose();
        joystickController.getJoystick2().getJoystickBase().dispose();
        joystickController.getJoystick2().getJoystick().dispose();
        boostController.boostComponent1.getBoostButton().dispose();
        boostController.boostComponent1.getBoostButtonUnCharged().dispose();
        boostController.boostComponent1.getBoostFont().dispose();
        boostController.boostComponent2.getBoostButton().dispose();
        boostController.boostComponent2.getBoostButtonUnCharged().dispose();
        boostController.boostComponent2.getBoostFont().dispose();*/
    }

    public PauseController getPauseController() {return pauseController;}
}