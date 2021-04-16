package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class SoundController {

    private Sound sound;
    private Sound collision;
    private Boolean isMuted = false;
    long id;

    public SoundController() {
        sound = Gdx.audio.newSound(Gdx.files.internal("game.mp3"));
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

    public void startGameSound() {
        id = sound.play(1.0f);
    }
    /*
    public void play() {
        if (!isMuted) {
            sound.loop();
            Gdx.app.log("Play", "ok");
        }
    }
    */
    /*public void stop() {
        if (!isMuted) {
            sound.stop();
            Gdx.app.log("Stop", "ok");
        }
    }*/

    public void mute() {
        if (isMuted) {
            isMuted = false;
            //sound.loop();
            sound.setLooping(id, true);
            Gdx.app.log("Mute", "resume");
        }
        else {
            isMuted = true;
            //sound.stop();
            sound.setLooping(id, false);
            Gdx.app.log("Mute", "pause");
        }
    }
}

