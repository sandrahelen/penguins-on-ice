package poi.game.views;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import poi.game.Poi;
import poi.game.models.ECSEngine;

public class Test implements Screen {
    FitViewport viewport;



    public Test(final Poi context){
        viewport = context.getViewport();
        context.getEcsEngine().createPlayer();
        context.getEcsEngine().createObstacle(200,200);
        context.getEcsEngine().createObstacle(300,400);
        context.getEcsEngine().createObstacle(600,600);
        context.getEcsEngine().createObstacle(300,900);
        context.getEcsEngine().createObstacle(500,1100);




    }

    @Override
    public void show() {

    }

    @Override
    public void render(final float delta) {
        viewport.apply(false);


    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
