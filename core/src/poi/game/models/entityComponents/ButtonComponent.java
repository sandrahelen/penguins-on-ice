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
    private Texture buttonMap;
    private Texture buttonSubmit;
    private Texture buttonBack;
    private Texture buttonNext;
    private Texture buttonHelp;
    private Texture buttonQuit;
    private Rectangle boundsPlay;
    private Rectangle boundsHighscore;
    private Rectangle boundsSettings;
    private Rectangle boundsMenu;
    private Rectangle boundsSound;
    private Rectangle boundsColor;
    private Rectangle boundsMap;
    private Rectangle boundsSubmit;
    private Rectangle boundsBack;
    private Rectangle boundsHelp;
    private Rectangle boundsQuit;

    public ButtonComponent() {
        buttonPlay = new Texture("general/buttonPlay.png");
        buttonHighscore = new Texture("general/buttonHighscore.png");
        buttonSettings = new Texture("general/buttonSettings.png");
        buttonMenu = new Texture("general/buttonMenu.png");
        buttonSound = new Texture("general/buttonSound.png");
        buttonColor = new Texture("general/buttonColor.png");
        buttonMap = new Texture("general/buttonMap.png");
        buttonSubmit = new Texture("general/buttonSubmit.png");
        buttonBack = new Texture("general/buttonBack.png");
        buttonNext = new Texture("general/buttonNext.png");
        buttonHelp = new Texture("general/buttonHelp.png");
        buttonQuit = new Texture("general/buttonQuit.png");

        boundsPlay = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT * 3 / 6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsHighscore = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT * 2 / 6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsSettings = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT / 6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsMenu = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT*1/16, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsSound = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT * 10 / 16, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsColor = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT * 7 / 16, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsMap = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT*4/16, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsSubmit = new Rectangle(Poi.WIDTH / 2 - buttonSubmit.getWidth() / 2, Poi.HEIGHT / 2 - buttonSubmit.getHeight() * 3 / 2, buttonSubmit.getWidth(), buttonPlay.getHeight());
        boundsBack = new Rectangle(Poi.WIDTH / 2 - buttonPlay.getWidth() / 2, Poi.HEIGHT / 6, buttonPlay.getWidth(), buttonPlay.getHeight());
        boundsHelp = new Rectangle(Poi.WIDTH - buttonHelp.getWidth() - 20, Poi.HEIGHT - buttonHelp.getHeight() - 20, buttonHelp.getWidth(), buttonHelp.getHeight());
       }

    public int getButtonWidth() { return buttonPlay.getWidth(); }
    public int getButtonHeight() { return buttonPlay.getHeight(); }

    public Texture getButtonPlay() { return buttonPlay; }
    public Texture getButtonHighscore() { return buttonHighscore; }
    public Texture getButtonSettings() { return buttonSettings; }
    public Texture getButtonMenu() { return buttonMenu; }
    public Texture getButtonSound() { return buttonSound; }
    public Texture getButtonColor() { return buttonColor; }
    public Texture getButtonMap() { return buttonMap; }
    public Texture getButtonSubmit() { return buttonSubmit; }
    public Texture getButtonBack() { return buttonBack; }
    public Texture getButtonNext() { return buttonNext; }
    public Texture getButtonHelp() { return buttonHelp; }
    public Texture getButtonQuit() { return buttonQuit; }

    public Rectangle getBoundsPlay() { return boundsPlay; }
    public Rectangle getBoundsHighscore() { return boundsHighscore; }
    public Rectangle getBoundsSettings() { return boundsSettings; }
    public Rectangle getBoundsMenu() { return boundsMenu; }
    public Rectangle getBoundsSound() { return boundsSound; }
    public Rectangle getBoundsColor() { return boundsColor; }
    public Rectangle getBoundsMap() { return boundsMap; }
    public Rectangle getBoundsSubmit() { return boundsSubmit; }
    public Rectangle getBoundsBack() { return boundsBack; }
    public Rectangle getBoundsHelp() { return boundsHelp; }

}
