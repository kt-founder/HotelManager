package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

public class viewProfile extends AppCompatActivity {

    EditText proUser, proPwd, proFirst, proLast, proStAddr, proCity, proState, proZip, proEmail, proPhone, proCname, proCnum, proCexp;
    Spinner proCtype;
    Button proModify, guestViewHome, guestViewLogout;
    TextView errorMessage;
    DBContext dbContext;
    String username;

    public static final String PREFS_NAME = "AppPrefs";
    public static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

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
        proCname = findViewById(R.id.pro_cname);
        proCnum = findViewById(R.id.pro_cnum);
        proCexp = findViewById(R.id.pro_cexp);
        proCtype = findViewById(R.id.pro_ctype);
        proModify = findViewById(R.id.pro_modify);
        guestViewHome = findViewById(R.id.guestViewHome);
        guestViewLogout = findViewById(R.id.guestViewLogout);
        errorMessage = findViewById(R.id.error_message);

        dbContext = new DBContext(this);
        dbContext.open();

        // Get the username from the intent
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = preferences.getString(KEY_USERNAME, "");
        //username = getIntent().getStringExtra("username");

        // Load user data
        loadUserData();

        proModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
                proUser.setFocusable(false);
                proPwd.setFocusable(false);
                proFirst.setFocusable(false);
                proLast.setFocusable(false);
                proStAddr.setFocusable(false);
                proCity.setFocusable(false);
                proState.setFocusable(false);
                proZip.setFocusable(false);
                proEmail.setFocusable(false);
                proPhone.setFocusable(false);
                proCname.setFocusable(false);
                proCnum.setFocusable(false);
                proCexp.setFocusable(false);
            }
        });

        guestViewHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(viewProfile.this, userHomeActivity.class);
                startActivity(intent);
                // Implement navigation to home activity
            }
        });

        guestViewLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement logout functionality
                Intent intent = new Intent(viewProfile.this, MainActivity.class);
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
            proCname.setText(profile.getCreditCardName());
            proCnum.setText(profile.getCreditCardNumber());
            proCexp.setText(profile.getCreditCardExp());
            // Set selected item for Spinner
            // Assuming proCtype is Spinner and creditCardType is a String
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
        String creditCardName = proCname.getText().toString();
        String creditCardNumber = proCnum.getText().toString();
        String creditCardExp = proCexp.getText().toString();
        String creditCardType = proCtype.getSelectedItem().toString();

        // Update user data in the database
        boolean result = dbContext.updateProfile(username, password, firstName, lastName, streetAddress, city, state, zipcode, email, phone, creditCardName, creditCardType, creditCardNumber, creditCardExp);

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
