package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import poi.game.Poi;
import poi.game.models.entityComponents.BoostComponent;
import poi.game.views.SettingsView;


public class BoostController {

    public BoostComponent boostComponent1;
    public BoostComponent boostComponent2;

    public BoostController(){
        boostComponent1 = new BoostComponent(120, 52);
        boostComponent2 = new BoostComponent(480, 52);
    }
    public void handleInput() {
        Vector3 touchTransformed = Poi.getCamera().unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
        if (Gdx.input.justTouched()) {
            if (boostComponent1.getBoundsBoost().contains(touchTransformed.x, touchTransformed.y)) {
                if (boostComponent1.getCharge() == 100) {
                    boostComponent1.setButtonClicked(true);
                    boostComponent1.setCharge(0);
                    boostComponent1.setBoost(true);
                }
                System.out.println("Button1 touched");
            }
            if (boostComponent2.getBoundsBoost().contains(touchTransformed.x, touchTransformed.y)) {
                if (boostComponent2.getCharge() == 100) {
                    boostComponent2.setButtonClicked(true);
                    boostComponent2.setCharge(0);
                    boostComponent2.setBoost(true);
                }
                System.out.println("Button2 touched");
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

    public void startTimer(){
        if(boostComponent1.getButtonClicked()){
            boostComponent1.startTimer();
        }
        if(boostComponent2.getButtonClicked()){
            boostComponent2.startTimer();
        }
    }
}
