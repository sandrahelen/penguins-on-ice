package poi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.controllers.MenuController;
import poi.game.views.MenuView;

public class Poi extends ApplicationAdapter {
	private SpriteBatch batch;
	//Texture img;
	private MenuView view;
	private Texture playButton;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		view = new MenuView();
		view.create();
		//img = new Texture("badlogic.jpg");
		//MenuController menuController = new MenuController();
		//menuController.navigateToView("MENU");
		playButton = new Texture("button.png");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(225/255f, 251/255f, 249/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		view.render(batch);

		//batch.draw(playButton, 50, 50);
		batch.end();
		//view.render(dt);
	}
	
	@Override
	public void dispose () {
		/*batch.dispose();
		img.dispose();*/
		view.dispose();
	}
}
