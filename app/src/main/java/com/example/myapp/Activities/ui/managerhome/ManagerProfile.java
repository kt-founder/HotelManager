package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.R;

public class ManagerProfile extends AppCompatActivity {
    Button modify,home,logout;
    EditText pro_user,pro_pwd,pro_first,pro_last,pro_staddr,pro_city,pro_state,pro_zip,pro_email,pro_phone,pro_cname,pro_cnum,pro_cexp,pro_role;
    Spinner pro_ctype;
    TextView pro_name;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manager_profile);
        // Set Id
        detailId();

        home = findViewById(R.id.guestViewHome);
        logout = findViewById(R.id.guestViewLogout);


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerProfile.this,ManagerHomeActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerProfile.this, MainActivity.class));
            }
        });

        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chinh sua
            }
        });
    }
    public void nonEdit()
    {
        pro_user.setFocusable(false);
        pro_pwd.setFocusable(false);
        pro_first.setFocusable(false);
        pro_last.setFocusable(false);
        pro_staddr.setFocusable(false);
        pro_state.setFocusable(false);
        pro_city.setFocusable(false);
        pro_zip.setFocusable(false);
        pro_email.setFocusable(false);
        pro_phone.setFocusable(false);


    }

    public void edit()
    {
        pro_pwd.setFocusableInTouchMode(true);
        pro_first.setFocusableInTouchMode(true);
        pro_last.setFocusableInTouchMode(true);
        pro_staddr.setFocusableInTouchMode(true);
        pro_state.setFocusableInTouchMode(true);
        pro_city.setFocusableInTouchMode(true);
        pro_zip.setFocusableInTouchMode(true);
        pro_email.setFocusableInTouchMode(true);
        pro_phone.setFocusableInTouchMode(true);


        pro_name.setText("Modify Details");
    }

    public void setData(Profile profile)
    {
        pro_user.setText(profile.getUsername());
        pro_pwd.setText(profile.getPassword());
        pro_first.setText(profile.getFirstName());
        pro_last.setText(profile.getLastName());
        pro_staddr.setText(profile.getStreetAddress());
        pro_city.setText(profile.getCity());
        pro_state.setText(profile.getState());
        pro_zip.setText(profile.getZipCode());
        pro_email.setText(profile.getEmail());
        pro_phone.setText(profile.getPhone());
    }

    public void detailId()
    {
        pro_name = findViewById(R.id.pro_name);
        pro_user = findViewById(R.id.pro_user);
        pro_pwd = findViewById(R.id.pro_pwd);
        pro_first = findViewById(R.id.pro_first);
        pro_last = findViewById(R.id.pro_last);
        pro_staddr = findViewById(R.id.pro_staddr);
        pro_city = findViewById(R.id.pro_city);
        pro_state = findViewById(R.id.pro_state);
        pro_zip = findViewById(R.id.pro_zip);
        pro_email = findViewById(R.id.pro_email);
        pro_phone = findViewById(R.id.pro_phone);


        modify = findViewById(R.id.pro_modify);
    }
}