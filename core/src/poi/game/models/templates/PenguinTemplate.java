package poi.game.models.templates;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import poi.game.models.entityComponents.Position;
import poi.game.models.entityComponents.Velocity;
import poi.game.models.entityComponents.Sprite;

abstract class PenguinTemplate{
        protected Vector2 position;
        protected Texture texture;
        protected Sprite sprite;

        protected abstract void update(float dt);
        protected abstract Texture getTextureSheet();

        /*
        public Vector2 getPosition() {
            return position;
        }
        */
        public Sprite getTexture() {
            return sprite;
        }
}
