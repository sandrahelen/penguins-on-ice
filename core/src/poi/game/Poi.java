package poi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import poi.game.models.entities.Player;
import poi.game.views.GameView;

public class Poi extends ApplicationAdapter {
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;

	private SpriteBatch batch;
	private GameView gv;
	//private Array<TextureRegion> currentFrames = new Array<TextureRegion>();
	private BitmapFont text;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		gv = new GameView();
		//text = new BitmapFont();
		//gv.create();


		//img = new Texture("p1-skli-bak.png");

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
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
		//img.dispose();
	}
}
