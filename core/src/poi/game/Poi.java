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

	public static final int WIDTH = 360;
	public static final int HEIGHT = 640;

	private SpriteBatch batch;
	//Texture img;
	private View view;
	private Texture playButton;
	private MenuController controller;

	public void create () {
		batch = new SpriteBatch();
		controller = new MenuController();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		controller.push(new MenuView(controller));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(225/255f, 251/255f, 249/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		controller.update(Gdx.graphics.getDeltaTime());
		controller.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	/*
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//controller = new MenuController();

		//view = new MenuView();
		//view.create();
		//img = new Texture("badlogic.jpg");
		//MenuController menuController = new MenuController();
		//menuController.navigateToView("MENU");
		//playButton = new Texture("button.png");
		//Gdx.app.log("Poi Create", "Poi created");

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(225/255f, 251/255f, 249/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//Gdx.app.log("Poi render", "batch begin");
		//controller.navigateToView("MENU", batch); // Går alltid til menu..
		//Gdx.app.log("Poi", "Destination: " + controller.getDestination());

		//controller.navigateToView(batch);
		//Gdx.app.log("Poi render", "after navigate");
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
	//}

	/*public void setView(View view) {
		this.view = view;
	}*/
}
