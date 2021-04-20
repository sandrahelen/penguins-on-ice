package poi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import poi.game.Poi;

public class SoundManager {

    private Music sound;
    private Sound collision;
    private Sound boost;
    private Boolean isMuted = false;
    private long id;

    public SoundManager() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("Main Theme ACNH.mp3"));
        //id = sound.loop(1.0f);
        collision = Gdx.audio.newSound(Gdx.files.internal("collision.wav"));
        boost = Gdx.audio.newSound(Gdx.files.internal("boost.wav"));
    }

    //public void setMute(boolean)

    public void playCollisionSound() {
        if (!isMuted) {
            collision.play();
        }
    }

    public void playBoostSound() {
        if (!isMuted) {
            boost.play();
        }
    }

    public void play() {
        if (!isMuted) {
            if (id != 0) {
                sound.pause();
            }
            else {
                sound.play();
            }
        }
    }

    public void stop() {
        if (!isMuted) {
            sound.play();
        }
    }

    public void mute() {
        if (isMuted) {
            isMuted = false;
            sound.play();
        }
        else {
            isMuted = true;
            sound.pause();
        }
    }
}
