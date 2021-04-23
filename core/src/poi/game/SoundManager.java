package poi.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

// Manager for sound inn app
public class SoundManager {

    private Music sound;
    private Sound collision;
    private Sound boost;
    private Boolean isMuted = false;
    private long id;

    private static final SoundManager soundManager = new SoundManager();

    private SoundManager() {
        sound = Gdx.audio.newMusic(Gdx.files.internal("audio/Main Theme ACNH.mp3"));
        collision = Gdx.audio.newSound(Gdx.files.internal("audio/collision.wav"));
        boost = Gdx.audio.newSound(Gdx.files.internal("audio/boost.wav"));
    }

    public static SoundManager getInstance(){
        return soundManager;
    }

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
