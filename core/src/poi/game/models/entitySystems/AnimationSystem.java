package poi.game.models.entitySystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Array;
import java.rmi.activation.ActivationGroup;
import java.util.ArrayList;

//import javax.swing.text.html.parser.Entity;

import poi.game.controllers.BoostController;
import poi.game.controllers.ColorController;
import poi.game.controllers.ColorGameController;
import poi.game.controllers.GameController;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;
import poi.game.models.entityComponents.TextureComponent;


public class AnimationSystem extends IteratingSystem {
    private float stateTime = 0f;
    private BoostController boostController;
    private ColorGameController colorGameController;
    private boolean finish = false;
    private ArrayList<String> texturesP1;
    private ArrayList<String> texturesP2;



    public AnimationSystem(BoostController boostController, ColorGameController colorGameController){
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        this.boostController = boostController;
        this.colorGameController = colorGameController;
        texturesP1 = this.colorGameController.getColorP1();
        texturesP2 = this.colorGameController.getColorP2();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        final TextureComponent textureComponent = ECSEngine.textureMapper.get(entity);
        if(boostController.getBoostComponent1().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                textureComponent.textureAnimation = textureComponent.animate(texturesP1.get(1)/*"players/p1-skli-bak.png"*/, 1,3);
            }
        }
        if(!boostController.getBoostComponent1().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                textureComponent.textureAnimation = textureComponent.animate(texturesP1.get(0)/*"players/p1-bak.png"*/, 1,3);
            }
        }
        if(boostController.getBoostComponent2().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                textureComponent.textureAnimation = textureComponent.animate(texturesP2.get(1)/*"players/p1-skli-bak.png"*/, 1,3);
           }
        }
        if(!boostController.getBoostComponent2().getBoost()){
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                textureComponent.textureAnimation = textureComponent.animate(texturesP2.get(0)/*"players/p1-bak.png"*/, 1,3);
           }
        }
        if (finish){
            if (ECSEngine.playerMapper.get(entity).id == 1) {
                textureComponent.textureAnimation = textureComponent.animate(texturesP1.get(2)/*"players/p1-finish.png"*/, 8, 3);
            }
            if (ECSEngine.playerMapper.get(entity).id == 2) {
                textureComponent.textureAnimation = textureComponent.animate(texturesP2.get(2)/*"players/p1-finish.png"*/, 8, 3);
            }
        }

    }
    public void setFinish(){
        this.finish = true;

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
