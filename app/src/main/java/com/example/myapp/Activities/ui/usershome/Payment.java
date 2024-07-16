package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.R;

public class Payment extends AppCompatActivity {
    Button home,logout,pay;
    EditText cvv,name,staddr,city,state,zip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);

        DBContext dbContext = new DBContext(this);
        dbContext.open();
        Intent intent = getIntent();
        String bookingId = intent.getStringExtra("booking_id");

        home = findViewById(R.id.paymentHome);
        logout = findViewById(R.id.paymentLogout);
        pay = findViewById(R.id.paymentPay);

        cvv = findViewById(R.id.paymentCVV);
        name = findViewById(R.id.paymentName);
        staddr = findViewById(R.id.paymentAddr);
        city = findViewById(R.id.paymentCity);
        state = findViewById(R.id.paymentState);
        zip = findViewById(R.id.paymentZip);

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Payment.this,ConfirmationActivity.class);

                boolean isUpdated = dbContext.updateReservationStatus(bookingId,"paid");

                if (isUpdated) {
                    Toast.makeText(getApplicationContext(), "Reservation status updated to paid!", Toast.LENGTH_SHORT).show();
                    intent1.putExtra("booking_id",bookingId);
                    startActivity(intent1);
                } else {
                    Toast.makeText(getApplicationContext(), "Failed to update reservation status.", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}