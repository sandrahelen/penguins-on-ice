package poi.game.models.clientServer;

public interface Leaderboard {
    public void submitScore(String user, int score);
    public void FirstFireBaseTest();
    public void setOnValueChangedListener(Datahandler datahandler);
}
