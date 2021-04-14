package poi.game.models.entityComponents;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import poi.game.Poi;

public class ButtonComponent {

    private Texture buttonPlay;
    private Texture buttonHighscore;
    private Texture buttonSettings;
    private Texture buttonMenu;
    private Texture buttonSound;
    private Texture buttonColor;
    private Rectangle boundsPlay;
    private Rectangle boundsHighscore;
    private Rectangle boundsSettings;
    private Rectangle boundsMenu;
    private Rectangle boundsSound;
    private Rectangle boundsColor;

    public ButtonComponent() {
        buttonPlay = new Texture("general/buttonPlay.png");
        buttonHighscore = new Texture("general/buttonHighscore.png");
        buttonSettings = new Texture("general/buttonSettings.png");
        buttonMenu = new Texture("general/buttonMenu.png");
        buttonSound = new Texture("general/buttonSound.png");
        buttonColor = new Texture("general/buttonColor.png");

        boundsPlay = new Rectangle(Poi.WIDTH/2-buttonPlay.getWidth()/2, Poi.HEIGHT*3/6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsHighscore = new Rectangle(Poi.WIDTH/2-buttonPlay.getWidth()/2,Poi.HEIGHT*2/6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsSettings = new Rectangle(Poi.WIDTH/2-buttonPlay.getWidth()/2,Poi.HEIGHT/6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsMenu = new Rectangle(Poi.WIDTH/2-buttonPlay.getWidth()/2,Poi.HEIGHT/6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsSound = new Rectangle(Poi.WIDTH/2-buttonPlay.getWidth()/2, Poi.HEIGHT*3/6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsColor = new Rectangle(Poi.WIDTH/2-buttonPlay.getWidth()/2,Poi.HEIGHT*2/6, buttonPlay.getWidth(), buttonPlay.getHeight());
    }

    public int getButtonWidth() { return buttonPlay.getWidth(); }
    public int getButtonHeight() { return buttonPlay.getHeight(); }

    public Texture getButtonPlay() { return buttonPlay; }
    public Texture getButtonHighscore() { return buttonHighscore; }
    public Texture getButtonSettings() { return buttonSettings; }
    public Texture getButtonMenu() { return buttonMenu; }
    public Texture getButtonSound() { return buttonSound; }
    public Texture getButtonColor() { return buttonColor; }

    public Rectangle getBoundsPlay() { return boundsPlay; }
    public Rectangle getBoundsHighscore() { return boundsHighscore; }
    public Rectangle getBoundsSettings() { return boundsSettings; }
    public Rectangle getBoundsMenu() { return boundsMenu; }
    public Rectangle getBoundsSound() { return boundsSound; }
    public Rectangle getBoundsColor() { return boundsColor; }

}
