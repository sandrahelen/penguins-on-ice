package poi.game.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import poi.game.Poi;
import poi.game.controllers.ColorController;
import poi.game.controllers.MapController;
import poi.game.models.factories.ViewFactory;

public class MapChangerView extends View implements ViewFactory {

    private MapController controller;
    private BitmapFont text;
    private Texture buttonMap1;
    private Texture buttonMap2;
    private Texture buttonBack;
    private Texture selected;

    public MapChangerView(GameView gameView){
        super();
        controller = new MapController(gameView);
        text = new BitmapFont();
        text.setColor(Color.BLACK);
        buttonMap1 = controller.getButtonMap1();
        buttonMap2 = controller.getButtonMap2();
        buttonBack = controller.getButtonBack();
        selected = controller.getSelected();
    }

    @Override
    public void update(float dt) {
        controller.handleInput();
    }

    public void dispose() {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);

        sb.begin();
        text.draw(sb, "Map1", Poi.WIDTH/2, Poi.HEIGHT-Poi.HEIGHT/3);
        text.draw(sb, "Map2", Poi.WIDTH/2, Poi.HEIGHT/3);
        if(Poi.getMapLocation() == "Map/Map1.tmx"){
            sb.draw(selected, Poi.WIDTH/4 - selected.getWidth()/10, Poi.HEIGHT-Poi.HEIGHT/3 - selected.getHeight()/2);
        }
        if(Poi.getMapLocation() == "Map/Map2.tmx"){
            sb.draw(selected, Poi.WIDTH/4 - selected.getWidth()/10, Poi.HEIGHT/3 - selected.getHeight()/2);
        }


        sb.draw(buttonMap1, Poi.WIDTH/4, Poi.HEIGHT-Poi.HEIGHT/2);
        sb.draw(buttonMap2, Poi.WIDTH/4, Poi.HEIGHT/6);

        sb.end();
        }
    }
