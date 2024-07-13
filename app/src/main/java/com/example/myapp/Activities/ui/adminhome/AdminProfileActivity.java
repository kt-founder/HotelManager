package com.example.myapp.Activities.ui.adminhome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.R;

public class AdminProfileActivity extends AppCompatActivity {

    EditText proUser, proPwd, proFirst, proLast, proStAddr, proCity, proState, proZip, proEmail, proPhone;
    Spinner proCtype;
    Button proModify, adminHome, adminLogout;
    TextView errorMessage;
    DBContext dbContext;
    String username;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        // Initialize UI components
        proUser = findViewById(R.id.pro_user);
        proPwd = findViewById(R.id.pro_pwd);
        proFirst = findViewById(R.id.pro_first);
        proLast = findViewById(R.id.pro_last);
        proStAddr = findViewById(R.id.pro_staddr);
        proCity = findViewById(R.id.pro_city);
        proState = findViewById(R.id.pro_state);
        proZip = findViewById(R.id.pro_zip);
        proEmail = findViewById(R.id.pro_email);
        proPhone = findViewById(R.id.pro_phone);
        proCtype = findViewById(R.id.pro_ctype); // Ensure Spinner is initialized
        errorMessage = findViewById(R.id.error_message);
        proModify = findViewById(R.id.adminModify);
        adminHome = findViewById(R.id.adminHome);
        adminLogout = findViewById(R.id.adminLogout);

        // Set up spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.credit_card_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proCtype.setAdapter(adapter); // This line sets the adapter to the Spinner

        dbContext = new DBContext(this);
        dbContext.open();

        // Get the username from the intent
        username = getIntent().getStringExtra("username");

        // Load user data
        loadUserData();

        proModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        adminHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfileActivity.this, AdminHomeActivity.class);
                startActivity(intent);
            }
        });

        adminLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadUserData() {
        Profile profile = dbContext.getUser(username);
        if (profile != null) {
            proUser.setText(profile.getUsername());
            proPwd.setText(profile.getPassword());
            proFirst.setText(profile.getFirstName());
            proLast.setText(profile.getLastName());
            proStAddr.setText(profile.getStreetAddress());
            proCity.setText(profile.getCity());
            proState.setText(profile.getState());
            proZip.setText(profile.getZipCode());
            proEmail.setText(profile.getEmail());
            proPhone.setText(profile.getPhone());

            // Set selected item for Spinner
            String creditCardType = profile.getCreditCardType();
            if (creditCardType != null) {
                for (int i = 0; i < proCtype.getAdapter().getCount(); i++) {
                    if (creditCardType.equals(proCtype.getAdapter().getItem(i).toString())) {
                        proCtype.setSelection(i);
                        break;
                    }
                }
            }
        } else {
            errorMessage.setText("User not found");
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    private void updateProfile() {
        // Get values from the input fields
        String password = proPwd.getText().toString();
        String firstName = proFirst.getText().toString();
        String lastName = proLast.getText().toString();
        String streetAddress = proStAddr.getText().toString();
        String city = proCity.getText().toString();
        String state = proState.getText().toString();
        String zipcode = proZip.getText().toString();
        String email = proEmail.getText().toString();
        String phone = proPhone.getText().toString();
        String creditCardType = proCtype.getSelectedItem().toString();

        // Update user data in the database
        boolean result = dbContext.updateProfileAdmin(username, password, firstName, lastName, streetAddress, city, state, zipcode, email, phone, creditCardType);

        // Show the result as an error message
        if (result) {
            errorMessage.setText("Profile updated successfully");
            errorMessage.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            errorMessage.setVisibility(View.VISIBLE);
        } else {
            errorMessage.setText("Profile update failed");
            errorMessage.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            errorMessage.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbContext.close();
    }
}
