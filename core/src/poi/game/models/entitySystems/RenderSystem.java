package poi.game.models.entitySystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

import poi.game.Poi;
import poi.game.models.ECSEngine;
import poi.game.models.entityComponents.AnimationComponent;
import poi.game.models.entityComponents.BodyComponent;
import poi.game.models.entityComponents.TextureComponent;

public class RenderSystem implements Disposable {

    public static final String TAG = RenderSystem.class.getSimpleName();

    private final ImmutableArray<Entity> animatedEntities;

    private final SpriteBatch spriteBatch;
    private final World world;


    public RenderSystem(final Poi context){
        spriteBatch = context.getSpriteBatch();
        world = context.getWorld();


        animatedEntities = context.getEcsEngine().getEntitiesFor(Family.all(AnimationComponent.class, BodyComponent.class).get());

    }

    public void render(final float alpha){
        Gdx.gl.glClearColor(0,0,1,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        for(final Entity entity : animatedEntities){
            renderEntity(entity, alpha);
        }
        spriteBatch.end();
    }

    private void renderEntity(Entity entity, float alpha) {
        final BodyComponent bodyComponent = ECSEngine.bodyMapper.get(entity);
        final TextureComponent textureComponent = ECSEngine.textureMapper.get(entity);

        if(textureComponent.texture != null){
            bodyComponent.renderPosition.lerp(bodyComponent.body.getPosition(), alpha);
            spriteBatch.draw(textureComponent.texture, bodyComponent.body.getPosition().x,bodyComponent.body.getPosition().y, 200,100 );

        }



    }


    @Override
    public void dispose(){

    }

}
