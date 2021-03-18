package poi.game.models.entitySystems;

import com.badlogic.ashley.core.*;
import poi.game.models.entityComponents.Position;
import poi.game.models.entityComponents.Velocity;

import javax.swing.text.html.parser.Entity;
import com.badlogic.ashley.core.ComponentMapper<T>;

public class MovementSystem {

    private ImmutableArray<Entity> entities;

    private ComponentMapper<Position> p = ComponentMapper.getFor(Position.class);
    private ComponentMapper<Position> v = ComponentMapper.getFor(Velocity.class);

    public MovementSystem(){


        public void update(float dt) {
            for{int i = 0; i < entities.size(); ++1 {
                Entity entity = entities.get(i);
                Position position = p.get(entity);
                Velocity velocity = v.get(entity);

                position.setPosX(position.getPosX() + velocity.getVelocityX());
            }
        }
    }
}
