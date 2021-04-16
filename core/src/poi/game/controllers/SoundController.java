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
        id = sound.loop(1.0f);
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
<<<<<<< HEAD
            id = sound.loop(0.2f);
=======
            sound.resume(id);
>>>>>>> 008a0dcfa6a5ef6a3f7ebf13f3345328eda8778a
        }
    }

    public void stop() {
        if (!isMuted) {
            sound.pause(id);
        }
    }

    public void mute() {
        if (isMuted) {
            isMuted = false;
            sound.resume(id);
        }
        else {
            isMuted = true;
            sound.pause(id);
        }
    }
}
