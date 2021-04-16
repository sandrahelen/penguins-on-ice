package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundController {

    private Sound sound;
    private Sound collision;
    private Sound boost;
    private Boolean isMuted = false;
    long id;

    public SoundController() {
        sound = Gdx.audio.newSound(Gdx.files.internal("soundGame.mp3"));
        id = sound.loop(1.0f);
        collision = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));
        boost = Gdx.audio.newSound(Gdx.files.internal("boost.wav"));
    }

    public void playCollisionSound(boolean isPlaying) {
        if (isPlaying) {
            collision.play();
        }
        else {
            collision.stop();
        }
    }

    public void playBoostSound(boolean isPlaying) {
        if (isPlaying) {
            boost.play();
        }
        else {
            boost.stop();
        }
    }

    public void play() {
        if (!isMuted) {
            if (id != 0) {
                sound.resume(id);
            }
            else {
                id = sound.loop(0.2f);
            }
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
