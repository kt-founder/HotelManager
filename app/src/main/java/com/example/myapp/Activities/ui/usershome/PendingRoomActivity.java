package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.adapter.PendingAdapter;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

import java.util.ArrayList;

public class PendingRoomActivity extends AppCompatActivity {
    Button home,logout;
    ListView pendingList;
    PendingAdapter pendingAdapter;
    ArrayList<Reservation> arrayList1;

    SharedPreferences sharedpreferences;
    public static final String SHARED_PREF_NAME = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pending_room);

        home = findViewById(R.id.pendingHome);
        logout = findViewById(R.id.pendingLogout);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(PendingRoomActivity.this, userHomeActivity.class);
                startActivity(a);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a =new Intent(PendingRoomActivity.this, MainActivity.class);
                startActivity(a);
            }
        });

    }
}