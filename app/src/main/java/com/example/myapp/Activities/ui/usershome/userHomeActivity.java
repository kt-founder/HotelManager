package com.example.myapp.Activities.ui.usershome;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.R;

public class userHomeActivity extends AppCompatActivity {
    Button bt_view_profile, bt_search_room, bt_view_pending, bt_view_reservations, bt_logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_home);

        bt_view_profile = findViewById(R.id.user_viewprofile);
        bt_search_room = findViewById(R.id.user_searchroom);
        bt_view_pending = findViewById(R.id.user_pending);
        bt_view_reservations = findViewById(R.id.user_reservationSummary);
        bt_logout = findViewById(R.id.manager_logout);

    }
}