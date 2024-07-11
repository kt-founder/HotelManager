package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

public class ManagerHomeActivity extends AppCompatActivity {
    Button manager_profile, manager_viewList, manager_availableRooms, manager_searchRoom, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manager_home);

        manager_profile = findViewById(R.id.manager_profile);
        manager_viewList = findViewById(R.id.manager_listReservation);
        manager_availableRooms = findViewById(R.id.manager_available);
        manager_searchRoom = findViewById(R.id.manager_search);
        logout = findViewById(R.id.manager_logout);


        manager_viewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerHomeActivity.this,MViewListReservationActivity.class));
            }
        });

        manager_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerHomeActivity.this,ManagerProfile.class));
            }
        });
        manager_availableRooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHomeActivity.this, AvailableRoomActivity.class);
                startActivity(intent);
            }
        });

        manager_searchRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManagerHomeActivity.this, SearchRoomManagerActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerHomeActivity.this, MainActivity.class));
            }
        });

    }
}