package poi.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


import poi.game.controllers.MenuController;
import poi.game.views.MenuView;
import poi.game.views.View;

public class Poi extends ApplicationAdapter {

	private SpriteBatch batch;
	//Texture img;
	private View view;
	private Texture playButton;
	private MenuController controller;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		controller = new MenuController();

		//view = new MenuView();
		//view.create();
		//img = new Texture("badlogic.jpg");
		//MenuController menuController = new MenuController();
		//menuController.navigateToView("MENU");
		//playButton = new Texture("button.png");
		Gdx.app.log("Poi Create", "Poi created");

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(225/255f, 251/255f, 249/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		Gdx.app.log("Poi render", "batch begin");
		controller.navigateToView("MENU", batch);
		Gdx.app.log("Poi render", "after navigate");
		//batch.draw(img, 0, 0);
		//view.render(batch);	// MenuView

		//batch.draw(playButton, 50, 50);
		batch.end();
		//view.render(dt);
	}
	
	@Override
	public void dispose () {
		/*batch.dispose();
		img.dispose();*/
		//view.dispose();
	}

	/*public void setView(View view) {
		this.view = view;
	}*/
}
