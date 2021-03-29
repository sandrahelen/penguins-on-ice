package poi.game.models.entitySystems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;


import poi.game.Poi;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.PlayerComponent;


public class MovementSystem extends IteratingSystem{
    private Vector2 xFactor;
    private Vector3 touchPos;


    public MovementSystem(final Poi context){
        super(Family.all(PlayerComponent.class, BodyComponent.class).get());
        xFactor = new Vector2(10,0);
        touchPos = new Vector3();
    }

    @Override
    public void processEntity(final Entity entity, final float deltaTime){
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        bodyComponent.body.setTransform(bodyComponent.body.getPosition().x,bodyComponent.body.getPosition().y+1, 0);
        if(Gdx.input.isTouched()){
            touchPos.set(Gdx.input.getX(),Gdx.input.getY(),0);
            if(touchPos.x > bodyComponent.body.getPosition().x+50)
                bodyComponent.body.setTransform(bodyComponent.body.getPosition().x + 10,bodyComponent.body.getPosition().y, 0);
            else
                bodyComponent.body.setTransform(bodyComponent.body.getPosition().x - 10,bodyComponent.body.getPosition().y, 0);
            //Gdx.app.log("MyTag", String.valueOf(bodyComponent.body.getPosition().x));
        }
    }


}
