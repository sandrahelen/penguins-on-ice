package poi.game.models.entityComponents;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Sprite {
    private Array<Sprite> sprites;

    public Sprite(Texture...textures) {
        sprites = new Array<>();
        this.addTextures(textures);
    }

    private void addTextures(Texture...textures) {
        for (Texture texture : textures) {
            sprites.add(new Sprite(texture));
        }
    }

    public Array<Sprite> getSprites() {
        return this.sprites;
    }

}
