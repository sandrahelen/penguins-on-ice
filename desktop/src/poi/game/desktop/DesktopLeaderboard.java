package poi.game.desktop;
import com.badlogic.gdx.Gdx;
import poi.game.Leaderboard;

public class DesktopLeaderboard implements Leaderboard {

    public void submitScore(String user, int score) {
        Gdx.app.log("DesktopLeaderboard", "would have submitted score for user " + user + ": " + score);
    }
    @Override
    public void FirstFireBaseTest() {

    }

    @Override
    public void setOnValueChangedListener() {
        Gdx.app.log("DesktopLauncher", "would have read from database");
    }
}
