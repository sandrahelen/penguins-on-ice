package poi.game.models.entitySystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import poi.game.controllers.BoostController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;
import poi.game.models.entityComponents.TextureComponent;


public class AnimationSystem extends IteratingSystem {
    private float stateTime = 0f;
    private BoostController boostController;

    public AnimationSystem(BoostController boostController){
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.boostController = boostController;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        final TextureComponent textureComponent = ECSEngine.textureMapper.get(entity);
        if(boostController.getBoostComponent1().getBoost()){
            System.out.println("Nå skal animasjon kjøre");
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                textureComponent.textureAnimation = textureComponent.animate("players/p1-skli-bak.png", 1,3);

                //bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }
        if(!boostController.getBoostComponent1().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                textureComponent.textureAnimation = textureComponent.animate("players/p1-bak.png", 1,3);
                //bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }
        if(boostController.getBoostComponent2().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                textureComponent.textureAnimation = textureComponent.animate("players/p1-skli-bak.png", 1,3);
                //bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }
        if(!boostController.getBoostComponent2().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                textureComponent.textureAnimation = textureComponent.animate("players/p1-bak.png", 1,3);
                //bodyComponent.body.setTransform(bodyComponent.body.getPosition().x, bodyComponent.body.getPosition().y+0.7f,0);
            }
        }
    }
    public void setStateTime(float time) {
        stateTime += time;
    }

    public float getStateTime() {
        return stateTime;
    }


    //Setup for sprite and frames
    /*public TextureRegion setupSprite() {
        this.setStateTime(Gdx.graphics.getDeltaTime());
        return this.getAnimation().getKeyFrame(this.getStateTime(), true);
    }*/


}
