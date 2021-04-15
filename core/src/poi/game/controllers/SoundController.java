package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundController {

    Sound sound;
    Boolean isMuted = false;
    long id;

    public SoundController() {
        //sound = Gdx.audio.newSound(Gdx.files.internal("soundGame.mp3"));
        //id = sound.play(1.0f);

    }

    public void play() {
        if (!isMuted) {
            //sound.setLooping(id, true);
        }
    }

    public void stop() {
        if (!isMuted) {
            //sound.setLooping(id, false);
            //sound.stop(id);
        }
    }

    public void mute() {
        if (isMuted) {
            isMuted = false;
            //sound.setLooping(id, true);
            //sound.play();
        }
        else {
            isMuted = true;
            //sound.setLooping(id, false);
            //sound.stop(id);
        }
    }
}
