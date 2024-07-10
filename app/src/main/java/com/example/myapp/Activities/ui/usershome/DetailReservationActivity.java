package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

public class DetailReservationActivity extends AppCompatActivity {

    Button home, logout, close;
    TextView idE,firstNameE,lastNameE,roomTypeE,checkInDateE,checkOutDateE,numberOfRoomE,numberOfAdultsE,numberOfChildrenE,totalPriceE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_reservation);

        idE = findViewById(R.id.textViewBookingID);
        firstNameE = findViewById(R.id.textViewFirstName);
        lastNameE = findViewById(R.id.textViewLastName);
        roomTypeE = findViewById(R.id.textViewRoomType);
        checkInDateE = findViewById(R.id.textViewCheckInDate);
        checkOutDateE = findViewById(R.id.textViewCheckOutDate);
        numberOfRoomE = findViewById(R.id.textViewNumberOfRooms);
        numberOfAdultsE = findViewById(R.id.textViewNumberOfAdults);
        numberOfChildrenE = findViewById(R.id.textViewNumberOfChildren);
        totalPriceE = findViewById(R.id.textViewTotalPrice);

        logout= findViewById(R.id.button_logout);
        home = findViewById(R.id.button_home);
        close = findViewById(R.id.bt_close);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailReservationActivity.this, userHomeActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailReservationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailReservationActivity.this, GuestReservationListActivity.class);
                startActivity(intent);
            }
        });
    }
}