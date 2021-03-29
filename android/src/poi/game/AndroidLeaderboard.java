package poi.game;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidLeaderboard implements Leaderboard {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public AndroidLeaderboard() {
        // Assuming we can instantiate it like this
        database = FirebaseDatabase.getInstance();
        myRef  = database.getReference("message");
    }

    public void submitScore(String user, int score) {
        myRef = database.getReference(user);
        myRef.setValue(score);
    }

    @Override
    public void FirstFireBaseTest() {
        if(myRef != null){
            myRef.setValue("Hello, World!");
        }
        else{
            System.out.println("Databasereference was not set -> therefore could not write to DB");
        }
    }
}
