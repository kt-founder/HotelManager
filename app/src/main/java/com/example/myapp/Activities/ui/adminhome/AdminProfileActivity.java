package com.example.myapp.Activities.ui.adminhome;

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
import com.example.myapp.R;

public class AdminProfileActivity extends AppCompatActivity {
    Button home,logout;
    EditText pro_user,pro_pwd,pro_first,pro_last,pro_staddr,pro_city,pro_state,pro_zip,pro_email,pro_phone,pro_cname,pro_cnum,pro_cexp,pro_role;
    Spinner pro_ctype;
    TextView pro_name;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_profile);

        detailId();
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProfileActivity.this,AdminHomeActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminProfileActivity.this, MainActivity.class));
            }
        });


    }

    private void detailId() {
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
    }
}