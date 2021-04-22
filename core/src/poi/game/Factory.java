package poi.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.ObstacleComponent;
import poi.game.models.entityComponents.PlayerComponent;
import poi.game.models.entityComponents.TextureComponent;

public class Factory {

    private ECSEngine ecsEngine;
    private final BodyDef bodyDef;
    private final FixtureDef fixtureDef;
    private final TiledMap tiledMap;
    private final World world;

    public Factory(ECSEngine ecsEngine, TiledMap tiledMap, World world){
        this.ecsEngine = ecsEngine;
        bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        this.tiledMap = tiledMap;
        this.world = world;
    }

    public void createPlayer(int posX, int posY, int id) {
        Entity player = ecsEngine.createEntity();

        //Body component
        final BodyComponent body = ecsEngine.createComponent(BodyComponent.class);
        body.width = 19;
        body.height = 32;
        bodyDef.gravityScale = 1;
        bodyDef.fixedRotation = true;
        bodyDef.position.set(posX, posY);

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
        final AnimationComponent animationComponent = ecsEngine.createComponent(AnimationComponent.class);
        player.add(animationComponent);

        final PlayerComponent playerComponent = ecsEngine.createComponent(PlayerComponent.class);
        playerComponent.id = id;
        player.add(playerComponent);

        TextureComponent textureComponent = ecsEngine.createComponent(TextureComponent.class);
        //Animation walk
        textureComponent.textureAnimation = textureComponent.animate("players/svart-bak.png", 1, 3);
        player.add(textureComponent);

        // add entity to engine
        ecsEngine.addEntity(player);
    }

    public void createObstacle(int posX, int posY) {
        Entity obstacle = ecsEngine.createEntity();
        final BodyComponent body = ecsEngine.createComponent(BodyComponent.class);
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

        final AnimationComponent animationComponent = ecsEngine.createComponent(AnimationComponent.class);
        obstacle.add(animationComponent);

        final ObstacleComponent obstacleComponent = ecsEngine.createComponent(ObstacleComponent.class);
        obstacle.add(obstacleComponent);

        final TextureComponent textureComponent = ecsEngine.createComponent(TextureComponent.class);
        textureComponent.textureAnimation = textureComponent.animate("obstacles/ice-2.png", 12, 1);
        obstacle.add(textureComponent);

        ecsEngine.addEntity(obstacle);
    }

    public void spawnGameObjects() {
        final MapLayer objectsLayer = tiledMap.getLayers().get("Obstacles");
        if (objectsLayer == null) {
            Gdx.app.log("Empty", "No obstacles in layer");
        } else {
            for (MapObject object : objectsLayer.getObjects()) {
                final Rectangle iceBlock = ((RectangleMapObject) object).getRectangle();
                createObstacle((int) iceBlock.x, (int) iceBlock.y);
            }
        }

    }
}
