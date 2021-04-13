package poi.game.models;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import poi.game.controllers.BoostController;
import poi.game.models.entityComponents.BoostComponent;
import poi.game.controllers.JoystickController;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.ObstacleComponent;
import poi.game.models.entityComponents.PlayerComponent;
import poi.game.models.entityComponents.TextureComponent;
import poi.game.models.entitySystems.CameraSystem;
import poi.game.models.entitySystems.MovementSystem;
import poi.game.models.entitySystems.TimerSystem;

public class ECSEngine extends PooledEngine {
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    private final JoystickController joystickController1;
    private final JoystickController joystickController2;
    private final BoostController boostController;

    //Mappers for components
    public static final ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<PlayerComponent> playerMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);


    public ECSEngine(final World world, final OrthographicCamera orthographicCamera) {
        super();

        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();

        //Iterating systems
        joystickController1 = new JoystickController(orthographicCamera, 1);
        joystickController2 = new JoystickController(orthographicCamera, 2);
        boostController = new BoostController();
        addSystem(new MovementSystem(joystickController1, joystickController2, boostController));
        addSystem(new CameraSystem(orthographicCamera));
        addSystem(new TimerSystem());

    }

    public JoystickController getGameController() {
        if (joystickController1.getId() == 1) {
            return joystickController1;
        }
        else {
            return joystickController2;
        }
    }

    public BoostController getBoostContoller() {
        return boostController;
    }



    public void createPlayer(int posX, int posY, World world, int id){
        Entity player = this.createEntity();

        //Body component
        final BodyComponent body = this.createComponent(BodyComponent.class);
        body.width = 19;
        body.height = 32;
        bodyDef.gravityScale = 1;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(posX,posY);

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body.body = world.createBody(bodyDef);



        //fixtures
        final PolygonShape polygonShape = new PolygonShape();
        fixtureDef.filter.categoryBits = 1;
        fixtureDef.filter.maskBits = 2 | 1;
        polygonShape.setAsBox(body.width * 0.5f, body.height * 0.5f);
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1f;
        body.body.createFixture(fixtureDef);
        polygonShape.dispose();
        body.renderPosition = body.body.getPosition();
        body.body.setUserData(player);
        player.add(body);



        //Add components to entity
        final AnimationComponent animationComponent = this.createComponent(AnimationComponent.class);
        player.add(animationComponent);

        final PlayerComponent playerComponent = this.createComponent(PlayerComponent.class);
        playerComponent.id = id;
        player.add(playerComponent);

        final TextureComponent textureComponent = this.createComponent(TextureComponent.class);
        //Animation walk
        textureComponent.textureAnimation = textureComponent.animate("players/p1-bak.png", 1,3);
        //Animation for boost
        //textureComponent.textureAnimation = textureComponent.animate("players/p1-skli-bak.png", 1,3);
        //Animation for finishline
        //textureComponent.textureAnimation = textureComponent.animate("players/p1-finish.png", 6,3);
        player.add(textureComponent);


        // add entity to engine
        this.addEntity(player);

    }


    public void createObstacle(int posX, int posY, World world) {
        Entity obstacle = this.createEntity();
        final BodyComponent body = this.createComponent(BodyComponent.class);
        body.width = 36;
        body.height = 29;

        bodyDef.gravityScale = 0;
        bodyDef.position.set(posX, posY);
        bodyDef.fixedRotation = true;
        bodyDef.type = BodyDef.BodyType.StaticBody;
        body.body = world.createBody(bodyDef);
        body.body.setUserData(obstacle);



        fixtureDef.filter.categoryBits = 2;
        fixtureDef.filter.maskBits = 1;
        final PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(body.width * 0.5f, body.height * 0.5f);
        fixtureDef.shape = polygonShape;
        fixtureDef.density = 1.0f;
        body.body.createFixture(fixtureDef);
        polygonShape.dispose();
        body.renderPosition = body.body.getPosition();
        obstacle.add(body);

        final AnimationComponent animationComponent = this.createComponent(AnimationComponent.class);
        obstacle.add(animationComponent);

        final ObstacleComponent obstacleComponent= this.createComponent(ObstacleComponent.class);
        obstacle.add(obstacleComponent);

        final TextureComponent textureComponent = this.createComponent(TextureComponent.class);
        textureComponent.textureAnimation = textureComponent.animate("obstacles/ice-2.png", 12, 1);
        obstacle.add(textureComponent);

        this.addEntity(obstacle);
    }

}
