package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundController {

    private Sound sound;
    private Sound collision;
    private Boolean isMuted = false;
    long id;

    public SoundController() {
        sound = Gdx.audio.newSound(Gdx.files.internal("Main Theme ACNH.mp3"));
        collision = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));
    }

    public void playCollisionSound(boolean isPlaying) {
        if (isPlaying) {
            collision.play();
        }
        else {
            collision.stop();
        }
    }

    public void play() {
        if (!isMuted) {
            id = sound.loop(0.2f);
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
            sound.loop();
        }
        else {
            isMuted = true;
            //sound.setLooping(id, false);
            //sound.stop(id);
        }
    }
}
