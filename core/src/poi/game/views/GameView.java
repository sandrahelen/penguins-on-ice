package poi.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.maps.MapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.TimerTask;

import poi.game.Poi;
import poi.game.WorldContactListener;
import poi.game.controllers.MenuController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.TextureComponent;

import static poi.game.Poi.HEIGHT;
import static poi.game.Poi.WIDTH;

public class GameView extends View {

    private ECSEngine ecsEngine;
    private World world;

    private final ImmutableArray<Entity> animatedEntities;

    private final OrthographicCamera camera;
    private final GLProfiler profiler;
    private final Box2DDebugRenderer box2DDebugRenderer;

    private Texture boostButton;
    private Texture boostButtonUnCharged;
    private Rectangle boundsBoost;
    private BitmapFont boostFont;
    //private Timer boostTimer;
    private float charge = 100;
    private double period = 0.1;
    //private final int boostSeconds = 10; //seconds for boost to recharge
    private boolean buttonClicked = false;

    public GameView(MenuController controller) {
        super(controller);
        world = new World(new Vector2(0, 200.0f), true);
        world.setContactListener(new WorldContactListener());
        camera = new OrthographicCamera(WIDTH, HEIGHT);

        boostButton = new Texture("shadedDark49.png");
        boostButtonUnCharged = new Texture("transparentDark47.png");
        //boundsBoost = new Rectangle(camera.position.x-70, camera.position.y-200, boostButton.getWidth(), boostButton.getHeight());
        boundsBoost = new Rectangle(250, 405 - boostButton.getHeight()/2, boostButton.getWidth(), boostButton.getHeight());
        boostFont = new BitmapFont();
        //boostTimer = new Timer();



        Box2D.init();
        //Setup Engine
        ecsEngine = new ECSEngine(world, camera);


        //Create Entities
        ecsEngine.createPlayer(400, 120, world);
        ecsEngine.createPlayer(250, 100, world);
        ecsEngine.createObstacle(200, 200, world);
        ecsEngine.createObstacle(300, 400, world);
        ecsEngine.createObstacle(600, 600, world);
        ecsEngine.createObstacle(200, 900, world);
        ecsEngine.createObstacle(300, 1300, world);
        ecsEngine.createObstacle(600, 1500, world);

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

    /*private void startTimer()
    {
        secondsLeft = boostSeconds;
        boostTimer.scheduleAtFixedRate(new TimerTask()
        {
            @Override
            public void run()
            {
                if(!gameOver)
                    secondsLeft--;
            }
        }, 0, 1000);
    }*/
    private void startTimer(){
        charge += Gdx.graphics.getRawDeltaTime();
        if(charge < 100){
            charge += period;
            //boostFont.draw(sb, "Seconds left:" + secondsLeft, camera.position.x-30, camera.position.y-180);
        }
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
        bodyComponent.renderPosition.lerp(bodyComponent.body.getPosition(), Gdx.graphics.getDeltaTime());
        sb.draw(textureComponent.setupSprite(), bodyComponent.body.getPosition().x - bodyComponent.width * 0.5f, bodyComponent.body.getPosition().y - bodyComponent.height * 0.5f, bodyComponent.width, bodyComponent.height);

        sb.end();
    }


    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            if (boundsBoost.contains(Gdx.input.getX(), Gdx.input.getY())) {
                //startTimer();
                if(charge == 100) {
                    boost();
                    buttonClicked = true;
                    charge = 0;
                }
                System.out.println("Button touched");

            }

        }
    }

    @Override
    public void update(float dt) {
        ecsEngine.update(dt);
        camera.update();
        world.step(dt, 6, 2);
        handleInput();

    }

    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(camera.combined);

        if(buttonClicked){
            startTimer();
        }
        if(charge > 99){
            buttonClicked = false;
            charge = 100;
        }
        sb.begin();
        if(buttonClicked){
            sb.draw(boostButtonUnCharged, camera.position.x-70, camera.position.y-200);
        }
        else{
            sb.draw(boostButton, camera.position.x-70, camera.position.y-200);
        }

        boostFont.draw(sb, (int)charge + "%", camera.position.x-30, camera.position.y-180);
        sb.end();

        for (final Entity entity : animatedEntities) {
            renderEntity(entity, sb);
        }



    }

    @Override
    public void dispose() {
        boostButton.dispose();
        /*for (final Entity entity : animatedEntities){
            entity.dispose(); // må lage en dispose funksjon for disse(?)
        }*/
    }

}