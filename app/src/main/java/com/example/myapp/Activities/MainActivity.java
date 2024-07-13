package com.example.myapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.ui.adminhome.AdminHomeActivity;
import com.example.myapp.Activities.ui.managerhome.ManagerHomeActivity;
import com.example.myapp.Activities.ui.usershome.userHomeActivity;
import com.example.myapp.R;

public class MainActivity extends AppCompatActivity {

    Button bt_signIn, bt_Register;
    EditText username, password;
    TextView reg_success, errorMessage;
    DBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        bt_signIn = findViewById(R.id.bt_signin);
        bt_Register = findViewById(R.id.bt_register);
        username = findViewById(R.id.log_username);
        password = findViewById(R.id.log_password);
        reg_success = findViewById(R.id.reg_success);
        errorMessage = findViewById(R.id.error_message);

        dbContext = new DBContext(this);
        dbContext.open();

        reg_success.setVisibility(View.GONE);
        errorMessage.setVisibility(View.GONE);

        // Set up
        bt_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameText = username.getText().toString();
                String passwordText = password.getText().toString();

                if (usernameText.isEmpty() || passwordText.isEmpty()) {
                    errorMessage.setText("Please enter both username and password");
                    errorMessage.setVisibility(View.VISIBLE);
                } else if (dbContext.checkLogin(usernameText, passwordText)) {
                    String role = dbContext.getUserRole(usernameText, passwordText);
                    Intent intent;
                    if (role != null) {
                        switch (role) {
                            case "Admin":
                                intent = new Intent(MainActivity.this, AdminHomeActivity.class);
                                break;
                            case "Manager":
                                intent = new Intent(MainActivity.this, ManagerHomeActivity.class);
                                break;
                            case "Guest":
                            default:
                                intent = new Intent(MainActivity.this, userHomeActivity.class);
                                break;
                        }

                        intent.putExtra("username", usernameText);
                        startActivity(intent);
                    }else{
                        errorMessage.setText("Invalid username or password");
                        errorMessage.setVisibility(View.VISIBLE);
                    }
                } else {
                    errorMessage.setText("Invalid username or password");
                    errorMessage.setVisibility(View.VISIBLE);
                }
            }
        });

        bt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.myapp.Activities.ui.usershome.Register.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbContext.close();
    }

    public void visibleField() {
        reg_success.setVisibility(View.VISIBLE);
    }
}
