package poi.game.models;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.maps.tiled.TiledMap;

import poi.game.controllers.BoostController;
import poi.game.controllers.ColorGameController;
import poi.game.controllers.JoystickController;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.ObstacleComponent;
import poi.game.models.entityComponents.PlayerComponent;
import poi.game.models.entityComponents.TextureComponent;
import poi.game.models.entitySystems.AnimationSystem;
import poi.game.models.entitySystems.CameraBoundsCollisionSystem;
import poi.game.models.entitySystems.CameraSystem;
import poi.game.models.entitySystems.GoalSystem;
import poi.game.models.entitySystems.MovementSystem;
import poi.game.models.entitySystems.TimerSystem;

// ECS Engine for Entity-Component-System pattern together  with folders entityComponent and entitySystems
public class ECSEngine extends PooledEngine {
    private final JoystickController joystickController;
    private final BoostController boostController;
    private final ColorGameController colorGameController;
    private final TiledMap tiledMap;

    //Mappers for components
    public static final ComponentMapper<BodyComponent> bodyMapper = ComponentMapper.getFor(BodyComponent.class);
    public static final ComponentMapper<PlayerComponent> playerMapper = ComponentMapper.getFor(PlayerComponent.class);
    public static final ComponentMapper<TextureComponent> textureMapper = ComponentMapper.getFor(TextureComponent.class);


    public ECSEngine(final OrthographicCamera orthographicCamera, TiledMap tiledMap) {
        super();

        this.tiledMap = tiledMap;

        //Iterating systems
        joystickController = new JoystickController();
        boostController = new BoostController();
        colorGameController = new ColorGameController();
        addSystem(new MovementSystem(orthographicCamera, joystickController, boostController));
        addSystem(new CameraSystem(orthographicCamera));
        addSystem(new TimerSystem());
        addSystem(new CameraBoundsCollisionSystem(orthographicCamera));
        addSystem(new GoalSystem(tiledMap));
        addSystem(new AnimationSystem(boostController, colorGameController));

    }

    public JoystickController getJoystickController() {
        return joystickController;
    }

    public BoostController getBoostContoller() {
        return boostController;
    }

    public ColorGameController getColorGameController() {
        return colorGameController;
    }



}
