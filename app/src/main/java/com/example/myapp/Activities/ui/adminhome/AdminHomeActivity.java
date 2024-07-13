package com.example.myapp.Activities.ui.adminhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

public class AdminHomeActivity extends AppCompatActivity {
    Button admin_view, admin_search, admin_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_home);

        admin_view = findViewById(R.id.admin_view);
        admin_search = findViewById(R.id.admin_search);
        admin_logout = findViewById(R.id.admin_logout);

        // Retrieve the username from the intent
        final String username = getIntent().getStringExtra("username");

        admin_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminHomeActivity.this, AdminProfileActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        admin_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, AdminSearch.class));
            }
        });

        admin_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this, MainActivity.class));
            }
        });
    }
}
