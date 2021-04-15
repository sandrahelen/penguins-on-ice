package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundController {

    private Sound sound;
    private Sound collision;
    Boolean isMuted = false;
    long id;

    public SoundController() {
        sound = Gdx.audio.newSound(Gdx.files.internal("soundGame.mp3"));
        collision = Gdx.audio.newSound(Gdx.files.internal("collision.mp3"));
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
            sound.play();
        }
        else {
            isMuted = true;
            sound.stop();
        }
    }
}
