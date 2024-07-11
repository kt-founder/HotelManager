package com.example.myapp.Activities.ui.adminhome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

public class AdminViewGuestActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;

    public static final String SHARED_PREF_NAME = "mypref";

    Button admin_modifyGM,admin_deleteGM,home,logout;
    EditText admin_userGM,admin_roleGM,admin_lastGM,admin_firstGM,admin_pwdGM,admin_staddrGM,admin_cityGM,admin_stateGM,admin_zipGM,admin_emailGM,admin_phone;
    TextView admin_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_view_guest);

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
        admin_phone = findViewById(R.id.admin_phoneGM);
        admin_title = findViewById(R.id.admin_viewTextGM);

        admin_modifyGM = findViewById(R.id.admin_modifyGM);
        admin_deleteGM = findViewById(R.id.admin_deleteGM);

        home = findViewById(R.id.adminViewHome);
        logout = findViewById(R.id.adminViewLogout);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminViewGuestActivity.this,AdminHomeActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminViewGuestActivity.this, MainActivity.class));
            }
        });
    }
}