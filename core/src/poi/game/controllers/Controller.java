package poi.game.controllers;

import poi.game.Poi;
import poi.game.SoundManager;

public abstract class Controller {

    protected ChangeViewController changeViewController;
    protected SoundManager soundController;

    public Controller() {
        this.changeViewController = Poi.getChangeViewController();
        this.soundController = Poi.getSoundManager();
    }

}
