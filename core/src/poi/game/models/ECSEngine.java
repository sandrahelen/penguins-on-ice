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
import poi.game.models.factories.ComponentFactory;

public class ECSEngine extends PooledEngine implements ComponentFactory {
    private final World world;
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;


    public static final ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<PlayerComponent> playerMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);


    public ECSEngine(final Poi context) {
        super();



        world = context.getWorld();
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();


        this.addSystem(new MovementSystem(context));
        this.addSystem(new CameraSystem(context));

    }

    @Override
    public void createPlayer(int posX, int posY){
        Entity entity = this.createEntity();


        final BodyComponent body = this.createComponent(BodyComponent.class);
        bodyDef.position.set(posX,posY);
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body.body = world.createBody(bodyDef);
        body.body.setUserData("PLAYER");
        body.width = 48;
        body.height = 48;


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
        textureComponent.textureAnimation = textureComponent.animate("p1-bak.png", 1,3);
        entity.add(textureComponent);

        this.addEntity(entity);

    }

    @Override
    public void createObstacle(int posX, int posY) {
        Entity entity = this.createEntity();


        final BodyComponent body = this.createComponent(BodyComponent.class);
        bodyDef.position.set(posX, posY);
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body.body = world.createBody(bodyDef);
        body.body.setUserData("OBSTACLE");
        body.width = 20;
        body.height = 27;


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
        textureComponent.textureAnimation = textureComponent.animate("ice-sprites.png", 4, 3);
        entity.add(textureComponent);

        this.addEntity(entity);
    }

}
