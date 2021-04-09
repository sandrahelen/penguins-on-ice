package poi.game.views;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import poi.game.WorldContactListener;
import poi.game.controllers.GameController;
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
    public final GameController gameController1;
    public final GameController gameController2;


    public GameView(MenuController controller) {
        super(controller);
        world = new World(new Vector2(0, 200.0f), true);
        world.setContactListener(new WorldContactListener());
        camera = new OrthographicCamera(WIDTH, HEIGHT);
        Box2D.init();

        //Setup Engine
        ecsEngine = new ECSEngine(world, camera);
        gameController1 = ecsEngine.getGameController();
        gameController2 = ecsEngine.getGameController();


        //Create Entities
        ecsEngine.createPlayer(400, 120, world, 1);
        ecsEngine.createPlayer(250, 100, world, 2);
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


    private void renderEntity(Entity entity, SpriteBatch sb) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        final TextureComponent textureComponent = ECSEngine.textureMapper.get(entity);
        if (profiler.isEnabled()) {
            box2DDebugRenderer.render(world, camera.combined);
        }
        sb.begin();
        bodyComponent.renderPosition.lerp(bodyComponent.body.getPosition(), Gdx.graphics.getDeltaTime());
        sb.draw(textureComponent.setupSprite(), bodyComponent.body.getPosition().x - bodyComponent.width * 0.5f, bodyComponent.body.getPosition().y - bodyComponent.height * 0.5f, bodyComponent.width, bodyComponent.height);
        //Draw controller player 1
        sb.draw(gameController1.base, camera.position.x - 300, camera.position.y - 200, 100, 100);
        sb.draw(gameController1.background, camera.position.x - 300, camera.position.y - 200, 95, 95);
        sb.draw(gameController1.joystick, camera.position.x - gameController1.getPosition().x, camera.position.y - gameController1.getPosition().y, 50, 50);
        //Draw controller player 2
        sb.draw(gameController2.base, camera.position.x, camera.position.y - 200, 100, 100);
        sb.draw(gameController2.background, camera.position.x, camera.position.y - 200, 95, 95);
        sb.draw(gameController2.joystick, camera.position.x - gameController2.getPosition().x, camera.position.y - gameController2.getPosition().y, 50, 50);
        sb.end();
    }


    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        ecsEngine.update(dt);
        camera.update();
        world.step(dt, 6, 2);

    }

    @Override
    public void render(SpriteBatch sb) {

        Gdx.gl.glClearColor(0, 0, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        sb.setProjectionMatrix(camera.combined);

        for (final Entity entity : animatedEntities) {
            renderEntity(entity, sb);
        }

    }

    @Override
    public void dispose() {

    }

}