package poi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import poi.game.views.GameView;

public class Poi extends ApplicationAdapter {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private SpriteBatch batch;
	private GameView gv;
	private BitmapFont text;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gv = new GameView();
		//gv.create();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(220/255f, 243/255f, 255/255f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.begin();
		//text.draw(batch, "Play", Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		//gv.update(Gdx.graphics.getDeltaTime());
		gv.render(batch);
		//batch.begin();
		//batch.draw(img, 0, 0);
		//batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		gv.dispose();
	}
}
