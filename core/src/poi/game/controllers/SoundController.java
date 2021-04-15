package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundController {

    Sound sound;
    Boolean isMuted = false;
    long id;

    public SoundController() {
        sound = Gdx.audio.newSound(Gdx.files.internal("soundGame.mp3"));

    }

    public void play() {
        if (!isMuted) {
            id = sound.play(1.0f);
        }
    }

    public void stop() {
        if (!isMuted) {
            sound.stop(id);
        }
    }

    public void mute() {
        if (isMuted) {
            isMuted = false;
        }
        else {
            isMuted = true;
        }
    }
}
