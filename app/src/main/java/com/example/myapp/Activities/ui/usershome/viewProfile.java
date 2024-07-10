package com.example.myapp.Activities.ui.usershome;

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

import com.example.myapp.R;

public class viewProfile extends AppCompatActivity {
    private Button modify,home,logout;
    private EditText pro_user,pro_pwd,pro_first,pro_last,pro_staddr,pro_city,pro_state,pro_zip,pro_email,pro_phone,pro_cname,pro_cnum,pro_cexp,pro_role;
    private Spinner pro_ctype;
    private TextView pro_name;
    private SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_profile);

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
        pro_cname = findViewById(R.id.pro_cname);
        pro_ctype = findViewById(R.id.pro_ctype);
        pro_cnum = findViewById(R.id.pro_cnum);
        pro_cexp = findViewById(R.id.pro_cexp);
        modify = findViewById(R.id.pro_modify);
        home = findViewById(R.id.guestViewHome);
        logout = findViewById(R.id.guestViewLogout);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(viewProfile.this,userHomeActivity.class);
            }
        });
    }
}