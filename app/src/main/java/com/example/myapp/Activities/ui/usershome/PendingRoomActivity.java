package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.adapter.PendingAdapter;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

import java.util.ArrayList;

public class PendingRoomActivity extends AppCompatActivity {
    Button home, logout;
    ListView pendingList;
    PendingAdapter pendingAdapter;
    DBContext dbContext;
    String username; // Assume this is retrieved from SharedPreferences or passed via intent
    public static final String PREFS_NAME = "AppPrefs";
    public static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_room);

        dbContext = new DBContext(this);
        dbContext.open();

        username = getUsername(); // Method to retrieve the username

        ArrayList<Reservation> pendingReservations = dbContext.getPendingReservations(username);
        if(pendingReservations.isEmpty()){
            Toast.makeText(PendingRoomActivity.this,"Nothing to show",Toast.LENGTH_SHORT).show();
        }

        pendingList = findViewById(R.id.pendingList);
        pendingAdapter = new PendingAdapter(this, pendingReservations);
        pendingList.setAdapter(pendingAdapter);

        home = findViewById(R.id.pendingHome);
        logout = findViewById(R.id.pendingLogout);

        home.setOnClickListener(v -> startActivity(new Intent(this, userHomeActivity.class)));
        logout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }

    private String getUsername() {
        // Retrieve username from SharedPreferences or intent
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = preferences.getString(KEY_USERNAME, "");
        return username; // Replace with actual retrieval logic
    }
}
