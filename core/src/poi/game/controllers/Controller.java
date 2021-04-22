package poi.game.controllers;

import poi.game.Poi;
import poi.game.SoundManager;
import poi.game.models.entityComponents.ButtonComponent;

public abstract class Controller {

    protected ChangeViewController changeViewController;
    protected SoundManager soundController;

    public Controller() {
        this.changeViewController = Poi.getChangeViewController();
        this.soundController = Poi.getSoundController();
    }

}
