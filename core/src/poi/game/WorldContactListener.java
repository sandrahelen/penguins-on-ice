package poi.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

public class WorldContactListener implements ContactListener {


    @Override
    public void beginContact(final Contact contact) {
        Entity player;
        Entity obstacle;
        final Body BodyA = contact.getFixtureA().getBody();
        final Body BodyB = contact.getFixtureB().getBody();
        final int catFixA =  contact.getFixtureA().getFilterData().categoryBits;
        final int catFixB =  contact.getFixtureB().getFilterData().categoryBits;

        if((int) (catFixA & 1) == 1){
            player = (Entity) BodyA.getUserData();
        }

        else if((int) (catFixB & 1) == 1){
            player =  (Entity) BodyB.getUserData();
        }
        else{
            return;
        }

        if((int) (catFixA & 2) == 2){
            obstacle =  (Entity) BodyA.getUserData();
        }
        else if((int) (catFixB & 2) == 2){
            obstacle =  (Entity) BodyB.getUserData();
        }
        else{
            return;
        }
        if(obstacle != null && player !=null){
            //Poi.getSoundController().playCollisionSound(true);
            Poi.getSoundController().playCollisionSound();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}

