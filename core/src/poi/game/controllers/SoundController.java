package poi.game.controllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;

public class SoundController {

    private AssetManager assetManager;
    private Sound sound;
    private Sound collision;
    private Boolean isMuted = false;
    long id;

    public SoundController() {
        assetManager = new AssetManager();
        assetManager.load("Main Theme ACNH.mp3", Sound.class);
        assetManager.finishLoading();
        //sound = Gdx.audio.newSound(Gdx.files.internal("Main Theme ACNH.mp3"));
        sound = assetManager.get("Main Theme ACNH.mp3", Sound.class);
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
            sound.resume(id);
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
