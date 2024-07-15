package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.ui.usershome.viewProfile;
import com.example.myapp.R;



public class userHomeActivity extends AppCompatActivity {
    Button bt_view_profile, bt_search_room, bt_view_pending, bt_view_reservations, bt_logout;
    String username;
    public static final String PREFS_NAME = "AppPrefs";
    public static final String KEY_USERNAME = "username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_home);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        username = preferences.getString(KEY_USERNAME, "");

        bt_view_profile = findViewById(R.id.user_viewprofile);
        bt_search_room = findViewById(R.id.user_searchroom);
        bt_view_pending = findViewById(R.id.user_pending);
        bt_view_reservations = findViewById(R.id.user_reservationSummary);
        bt_logout = findViewById(R.id.manager_logout);

        bt_view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userHomeActivity.this, viewProfile.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        bt_search_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userHomeActivity.this, SearchRoomActivity.class);
                startActivity(intent);
            }
        });

        bt_view_pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userHomeActivity.this, PendingRoomActivity.class);
                startActivity(intent);
            }
        });

        bt_view_reservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userHomeActivity.this, GuestReservationActivity.class);
                startActivity(intent);
            }
        });

        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userHomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
