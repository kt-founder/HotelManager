package com.example.myapp.Activities.ui.adminhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.R;

public class AdminViewGuestActivity extends AppCompatActivity {

    DBContext db;
    Button admin_modifyGM, admin_deleteGM, home, logout;
    EditText admin_userGM, admin_roleGM, admin_lastGM, admin_firstGM, admin_pwdGM, admin_staddrGM, admin_cityGM, admin_stateGM, admin_zipGM, admin_emailGM, admin_phoneGM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_view_guest);

        db = new DBContext(this);
        db.open();

        // Initialize UI components
        setupUI();

        // Retrieve username from intent
        String username = getIntent().getStringExtra("username");

        // Fetch profile data
        populateProfile(username);

        admin_modifyGM.setOnClickListener(v -> {
            if (updateUserProfile(username)) {
                Toast.makeText(this, "Profile updated successfully.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to update profile.", Toast.LENGTH_SHORT).show();
            }
        });

        admin_deleteGM.setOnClickListener(v -> {
            if (db.deleteUser(username)) {
                Toast.makeText(this, "User deleted successfully.", Toast.LENGTH_SHORT).show();
                finish(); // Exit activity after delete
            } else {
                Toast.makeText(this, "Failed to delete user.", Toast.LENGTH_SHORT).show();
            }
        });

        home.setOnClickListener(v -> startActivity(new Intent(this, AdminHomeActivity.class)));
        logout.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }

    private void setupUI() {
        admin_modifyGM = findViewById(R.id.admin_modifyGM);
        admin_deleteGM = findViewById(R.id.admin_deleteGM);
        home = findViewById(R.id.adminViewHome);
        logout = findViewById(R.id.adminViewLogout);

        admin_userGM = findViewById(R.id.admin_userGM);
        admin_roleGM = findViewById(R.id.admin_roleGM);
        admin_firstGM = findViewById(R.id.admin_firstGM);
        admin_lastGM = findViewById(R.id.admin_lastGM);
        admin_pwdGM = findViewById(R.id.admin_pwdGM);
        admin_staddrGM = findViewById(R.id.admin_staddrGM);
        admin_cityGM = findViewById(R.id.admin_cityGM);
        admin_stateGM = findViewById(R.id.admin_stateGM);
        admin_zipGM = findViewById(R.id.admin_zipGM);
        admin_emailGM = findViewById(R.id.admin_emailGM);
        admin_phoneGM = findViewById(R.id.admin_phoneGM);
    }

    private void populateProfile(String username) {
        Profile profile = db.getUser(username);
        if (profile != null) {
            admin_userGM.setText(profile.getUsername());
            admin_roleGM.setText(profile.getRole());
            admin_firstGM.setText(profile.getFirstName());
            admin_lastGM.setText(profile.getLastName());
            admin_pwdGM.setText(profile.getPassword());
            admin_staddrGM.setText(profile.getStreetAddress());
            admin_cityGM.setText(profile.getCity());
            admin_stateGM.setText(profile.getState());
            admin_zipGM.setText(profile.getZipCode());
            admin_emailGM.setText(profile.getEmail());
            admin_phoneGM.setText(profile.getPhone());
        }
    }

    private boolean updateUserProfile(String username) {
        String password = admin_pwdGM.getText().toString();
        String firstName = admin_firstGM.getText().toString();
        String lastName = admin_lastGM.getText().toString();
        String streetAddress = admin_staddrGM.getText().toString();
        String city = admin_cityGM.getText().toString();
        String state = admin_stateGM.getText().toString();
        String zipcode = admin_zipGM.getText().toString();
        String email = admin_emailGM.getText().toString();
        String phone = admin_phoneGM.getText().toString();

        return db.updateProfile(username, password, firstName, lastName, streetAddress, city, state, zipcode, email, phone);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
