package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Map;

import poi.game.Poi;
import poi.game.SoundManager;
import poi.game.models.BoostComponent;

// Controller for boost in game
public class BoostController extends Controller {

    private BoostComponent boostComponent1;
    private BoostComponent boostComponent2;

    private Vector3 touchPos;
    private Map<Integer, Float> touches = new HashMap<>();

    private SoundManager soundController;

    public BoostController(){
        boostComponent1 = new BoostComponent(Poi.WIDTH/2-280, Poi.HEIGHT/2-50);
        boostComponent2 = new BoostComponent(Poi.WIDTH/2+240, Poi.HEIGHT/2-50);
        touchPos = new Vector3();

        // Adding possible fingers to Hashmap
        for (int i=0; i<5; i++) {
            touches.put(i, touchPos.x);
            touches.put(i, touchPos.y);
        }
        soundController = Poi.getSoundController();
    }

    public void handleInput(float dt) {
        startTimer(dt);
        for (int i=0; i<5; i++) {
            if (Gdx.input.isTouched(i)) {
                Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(i), Gdx.input.getY(i), 0));
                touches.put(i, touchTransformed.x);
                if (boostComponent1.getBoundsBoost().contains(touchTransformed.x, touchTransformed.y)) {
                    if (boostComponent1.getCharge() == 100) {
                        boostComponent1.setButtonClicked(true);
                        boostComponent1.setCharge(0);
                        boostComponent1.setBoost(true);
                        soundController.playBoostSound();
                    }
                }
                if (boostComponent2.getBoundsBoost().contains(touchTransformed.x, touchTransformed.y)) {
                    if (boostComponent2.getCharge() == 100) {
                        boostComponent2.setButtonClicked(true);
                        boostComponent2.setCharge(0);
                        boostComponent2.setBoost(true);
                        soundController.playBoostSound();
                    }
                }
            }
        }
    }

    public BoostComponent getBoostComponent1() {
        return boostComponent1;
    }

    public BoostComponent getBoostComponent2() {
        return boostComponent2;
    }

    public void setValues(){
        if (boostComponent1.getCharge() > 20){
            boostComponent1.setBoost(false);
        }
        if (boostComponent2.getCharge() > 20){
            boostComponent2.setBoost(false);
        }
        if(boostComponent1.getCharge() > 99){
            boostComponent1.setButtonClicked(false);
            boostComponent1.setCharge(100);
        }
        if(boostComponent2.getCharge() > 99){
            boostComponent2.setButtonClicked(false);
            boostComponent2.setCharge(100);
        }
    }

    public void startTimer(float dt){
        if(boostComponent1.getButtonClicked()){
            boostComponent1.startTimer(dt);
        }
        if(boostComponent2.getButtonClicked()){
            boostComponent2.startTimer(dt);
        }
    }
}
