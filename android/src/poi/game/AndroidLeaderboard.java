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
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AndroidLeaderboard implements Leaderboard {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    public AndroidLeaderboard() {
        // Assuming we can instantiate it like this
        database = FirebaseDatabase.getInstance();
        myRef  = database.getReference("message");
    }

    public void submitScore(String user, int score) {
        Map<String, Integer> data = new HashMap<>();
        data.put(user, score);
        myRef = database.getReference("scores");
        myRef.child(user).setValue(score);  // Adding username and score to database
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

    @Override
    public void setOnValueChangedListener() {
        myRef = database.getReference("scores");
        myRef.orderByValue().limitToFirst(5).addChildEventListener(new ChildEventListener() {
            // Read from database in order by score, displaying only the first five
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG, "User: " + snapshot.getKey() + ". Score: " + snapshot.getValue());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d(TAG, "User " + snapshot.getKey() + " updated score to " + snapshot.getValue());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        /*
        myRef.addValueEventListener(new ValueEventListener() {
            // Read from database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String key = snapshot.getKey();
                Map<String, Integer> value = (HashMap<String, Integer>) snapshot.getValue();
                for (Map.Entry<String, Integer> data : value.entrySet()) {
                    Log.d(TAG, "User: " + data.getKey() + ". Score: " + data.getValue());
                }
                //Log.d(TAG, "Key is: " + value.keySet() + ". Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/
    }
}
