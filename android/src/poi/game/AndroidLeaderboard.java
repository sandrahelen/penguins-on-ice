package poi.game;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidLeaderboard implements Leaderboard {

    private final LeaderboardServiceApi service;
    FirebaseDatabase database;
    DatabaseReference myRef;

    public AndroidLeaderboard() {
        // Assuming we can instantiate it like this
        service = new LeaderboardServiceApi();
        database = FirebaseDatabase.getInstance();
        myRef  = database.getReference("message");
    }

    public void submitScore(String user, int score) {
        service.submitScore(user, score);
    }
}
