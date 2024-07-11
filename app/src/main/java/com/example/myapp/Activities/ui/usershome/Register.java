package com.example.myapp.Activities.ui.usershome;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.R;
import com.example.myapp.Activities.database.DBManager;
import com.example.myapp.Activities.entities.Registration;

public class Register extends AppCompatActivity {

    private EditText regUser, regPwd, regFirst, regLast, regStaddr, regCity, regState, regZip, regEmail, regPhone, regCname, regCnum, regCexp;
    private Spinner regCtype;
    private Button registerButton;
    private DBManager dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        regUser = findViewById(R.id.reg_user);
        regPwd = findViewById(R.id.reg_pwd);
        regFirst = findViewById(R.id.reg_first);
        regLast = findViewById(R.id.reg_last);
        regStaddr = findViewById(R.id.reg_staddr);
        regCity = findViewById(R.id.reg_city);
        regState = findViewById(R.id.reg_state);
        regZip = findViewById(R.id.reg_zip);
        regEmail = findViewById(R.id.reg_email);
        regPhone = findViewById(R.id.reg_phone);
        regCname = findViewById(R.id.reg_cname);
        regCtype = findViewById(R.id.reg_ctype);
        regCnum = findViewById(R.id.reg_cnum);
        regCexp = findViewById(R.id.reg_cexp);
        registerButton = findViewById(R.id.register_button);

        dbHelper = new DBManager(this);

        registerButton.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        // Get values from the input fields
        String username = regUser.getText().toString();
        String password = regPwd.getText().toString();
        String firstName = regFirst.getText().toString();
        String lastName = regLast.getText().toString();
        String streetAddress = regStaddr.getText().toString();
        String city = regCity.getText().toString();
        String state = regState.getText().toString();
        String zipcode = regZip.getText().toString();
        String email = regEmail.getText().toString();
        String phone = regPhone.getText().toString();
        String creditCardName = regCname.getText().toString();
        String creditCardType = regCtype.getSelectedItem().toString();
        String creditCardNumber = regCnum.getText().toString();
        String creditCardExp = regCexp.getText().toString();

        // Create a Registration object
        Registration registration = new Registration(username, password, firstName, lastName, streetAddress, city, state, zipcode, email, phone, creditCardName, creditCardType, creditCardNumber, creditCardExp);

        // Add record to the database
        String result = dbHelper.addRecord(registration);

        // Show the result as a toast
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
