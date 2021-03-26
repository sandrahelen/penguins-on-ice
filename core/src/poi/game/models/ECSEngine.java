package poi.game.models;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

import poi.game.Poi;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.ObstacleComponent;
import poi.game.models.entityComponents.PlayerComponent;
import poi.game.models.entityComponents.TextureComponent;
import poi.game.models.entitySystems.CameraSystem;
import poi.game.models.entitySystems.MovementSystem;
import poi.game.models.entitySystems.RenderSystem;

public class ECSEngine extends PooledEngine {
    private final World world;
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    private final Array<RenderSystem> renderSystems;

    public static final ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<PlayerComponent> playerMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);


    public ECSEngine(final Poi context) {
        super();



        world = context.getWorld();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        this.renderSystems = new Array<>();
        this.addSystem(new MovementSystem(context));
        this.addSystem(new CameraSystem(context));

    }

    public void createPlayer(){
        Entity entity = this.createEntity();


        final BodyComponent body = this.createComponent(BodyComponent.class);
        bodyDef.position.set(50,50);
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body.body = world.createBody(bodyDef);
        body.body.setUserData("PLAYER");
        body.width = 30;
        body.height = 50;


        fixtureDef.filter.categoryBits = 10;
        fixtureDef.filter.maskBits = 10;
        final PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(1*0.5f,1*0.5f);
        fixtureDef.shape = polygonShape;
        body.body.createFixture(fixtureDef);
        polygonShape.dispose();
        body.renderPosition = body.body.getPosition();
        entity.add(body);

        final AnimationComponent animationComponent = this.createComponent(AnimationComponent.class);
        entity.add(animationComponent);

        final PlayerComponent playerComponent = this.createComponent(PlayerComponent.class);
        entity.add(playerComponent);

        final TextureComponent textureComponent = this.createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("p1-bak.png");
        entity.add(textureComponent);

        this.addEntity(entity);

    }

    public void createObstacle(int posX, int posY) {
        Entity entity = this.createEntity();


        final BodyComponent body = this.createComponent(BodyComponent.class);
        bodyDef.position.set(posX, posY);
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body.body = world.createBody(bodyDef);
        body.body.setUserData("Obstacle");
        body.width = 100;
        body.height = 30;


        fixtureDef.filter.categoryBits = 10;
        fixtureDef.filter.maskBits = 10;
        final PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(1 * 0.5f, 1 * 0.5f);
        fixtureDef.shape = polygonShape;
        body.body.createFixture(fixtureDef);
        polygonShape.dispose();
        body.renderPosition = body.body.getPosition();
        entity.add(body);

        final AnimationComponent animationComponent = this.createComponent(AnimationComponent.class);
        entity.add(animationComponent);

        final ObstacleComponent obstacleComponent= this.createComponent(ObstacleComponent.class);
        entity.add(obstacleComponent);

        final TextureComponent textureComponent = this.createComponent(TextureComponent.class);
        textureComponent.texture = new Texture("badlogic.jpg");
        entity.add(textureComponent);

        this.addEntity(entity);
    }

}
