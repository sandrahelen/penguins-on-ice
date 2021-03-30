package poi.game;

import android.util.Log;
import static android.content.ContentValues.TAG;
import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    @Override
    public void setOnValueChangedListener() {
        myRef.addValueEventListener(new ValueEventListener() {
            // Read from database
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String key = snapshot.getKey();
                Long value = snapshot.getValue(Long.class);
                Log.d(TAG, "Key is: " + key + ". Value is: " + value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
