package com.example.myapp.Activities.ui.usershome;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.R;

public class Register extends AppCompatActivity {

    private EditText regUser, regPwd, regFirst, regLast, regStaddr, regCity, regState, regZip, regEmail, regPhone, regCname, regCnum, regCexp;
    private Spinner regCtype;
    private Button registerButton;
    private DBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        dbContext = new DBContext(this);

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

        if (validateInputs(username, password, firstName, lastName, streetAddress, city, state, zipcode, email, phone, creditCardName, creditCardNumber, creditCardExp)) {
            dbContext.open();

            // Add record to the database
            long result = dbContext.insertUser(username, password, firstName, lastName, streetAddress, city, state, zipcode, email, phone, creditCardName, creditCardType, creditCardNumber, creditCardExp);

            dbContext.close();

            // Show the result as a toast
            if (result != -1) {
                Toast.makeText(this, "Registration Successful", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    private boolean validateInputs(String username, String password, String firstName, String lastName, String streetAddress, String city, String state, String zipcode, String email, String phone, String creditCardName, String creditCardNumber, String creditCardExp) {
        if (username.isEmpty()) {
            regUser.setError("Username is required");
            return false;
        }
        if (password.isEmpty()) {
            regPwd.setError("Password is required");
            return false;
        }
        if (firstName.isEmpty()) {
            regFirst.setError("First name is required");
            return false;
        }
        if (lastName.isEmpty()) {
            regLast.setError("Last name is required");
            return false;
        }
        if (streetAddress.isEmpty()) {
            regStaddr.setError("Street address is required");
            return false;
        }
        if (city.isEmpty()) {
            regCity.setError("City is required");
            return false;
        }
        if (state.isEmpty()) {
            regState.setError("State is required");
            return false;
        }
        if (zipcode.isEmpty()) {
            regZip.setError("Zip code is required");
            return false;
        }
        if (email.isEmpty()) {
            regEmail.setError("Email is required");
            return false;
        }
        if (phone.isEmpty()) {
            regPhone.setError("Phone is required");
            return false;
        }
        if (creditCardName.isEmpty()) {
            regCname.setError("Name on the credit card is required");
            return false;
        }
        if (creditCardNumber.isEmpty()) {
            regCnum.setError("Credit card number is required");
            return false;
        }
        if (creditCardExp.isEmpty()) {
            regCexp.setError("Credit card expiry date is required");
            return false;
        }
        return true;
    }
}
