package poi.game;

import android.util.Log;
import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import poi.game.models.ClientServer.Datahandler;
import poi.game.models.ClientServer.Leaderboard;

public class AndroidLeaderboard implements Leaderboard {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public AndroidLeaderboard() {
        database = FirebaseDatabase.getInstance();
        myRef  = database.getReference("message");
    }

    public void submitScore(String user, int score) {
        myRef = database.getReference("scores");
        myRef.child(user).setValue(score);  // Adding username and score to database
    }

    @Override
    public void FirstFireBaseTest() {   // Testing that Firebase database is working
        if(myRef != null){
            myRef.setValue("Hello, Poi!");
        }
        else{
            System.out.println("Databasereference was not set -> therefore could not write to DB");
        }
    }

    @Override
    public void setOnValueChangedListener(final Datahandler datahandler) {
        myRef = database.getReference("scores");
        myRef.orderByValue().limitToFirst(5).addChildEventListener(new ChildEventListener() {
            // Read from database in order by score, displaying only the first five
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Updates database when new data added
                Log.d(TAG, "User: " + snapshot.getKey() + ". Score: " + snapshot.getValue(Integer.class));
                datahandler.setScores(snapshot.getKey(), snapshot.getValue(Integer.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                // Updates database with changed data
                Log.d(TAG, "User " + snapshot.getKey() + " updated score to " + snapshot.getValue(Integer.class));
                datahandler.clearScores();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                Log.d(TAG, "User " + snapshot.getKey() + " removed");
                datahandler.clearScores();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG, "User " + snapshot.getKey() + " changed order position");
                datahandler.clearScores();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG,"The read failed: " + error.toException());
            }
        });
    }
}
