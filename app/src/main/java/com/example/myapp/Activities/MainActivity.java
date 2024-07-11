package com.example.myapp.Activities;

import android.content.Intent;
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

import com.example.myapp.Activities.database.DBManager;
import com.example.myapp.Activities.ui.usershome.Register;
import com.example.myapp.Activities.ui.usershome.userHomeActivity;
import com.example.myapp.R;

public class MainActivity extends AppCompatActivity {

    Button bt_signIn, bt_Register;
    EditText username, password;
    TextView reg_success;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//
//        DBManager dbManager=new DBManager(this);
//        dbManager.open();

        // Init
        bt_signIn = findViewById(R.id.bt_signin);
        bt_Register = findViewById(R.id.bt_register);
        username = findViewById(R.id.log_username);
        password = findViewById(R.id.log_password);
        reg_success = findViewById(R.id.reg_success);

        reg_success.setVisibility(View.GONE);
        // Set up
       bt_signIn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent a =new Intent(MainActivity.this, userHomeActivity.class);
               startActivity(a);
           }
       });
       bt_Register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, Register.class);
               startActivity(intent);
           }
       });
    }
    public void visibleField()
    {
        reg_success.setVisibility(View.VISIBLE);
    }
}