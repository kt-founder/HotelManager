package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

public class ViewRoom extends AppCompatActivity {
    Button reserve, logout, home;
    EditText hotelName,hotelLocation, roomType,nBeds,rFacilities,cid,cod,nNights,price,totalPrice;

    SharedPreferences sharedpreferences;

    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_ROOMNUMBER = "RoomNumber";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_room);

        reserve = findViewById(R.id.viewReserve);
        logout = findViewById(R.id.viewLogout);
        home = findViewById(R.id.viewHome);

        hotelName = findViewById(R.id.viewHotelName);
        hotelLocation = findViewById(R.id.viewHotelLocation);
        roomType = findViewById(R.id.viewRoomType);
        nBeds = findViewById(R.id.viewBeds);
        rFacilities = findViewById(R.id.viewRoomFacilities);
        cid = findViewById(R.id.viewCheckInDate);
        cod = findViewById(R.id.viewCheckOutDate);
        nNights = findViewById(R.id.viewNights);
        price = findViewById(R.id.viewPrice);
        totalPrice = findViewById(R.id.viewTotalPrice);

        // Extract data from intent
        Intent intent = getIntent();
        hotelName.setText(intent.getStringExtra("hotelName"));
        hotelLocation.setText(intent.getStringExtra("hotelLocation"));
        roomType.setText(intent.getStringExtra("roomType"));
        nBeds.setText(intent.getStringExtra("numberOfBeds"));
        rFacilities.setText(intent.getStringExtra("roomFacilities"));
        cid.setText(intent.getStringExtra("checkInDate"));
        cod.setText(intent.getStringExtra("checkOutDate"));
        nNights.setText(intent.getStringExtra("numberOfNights"));
        price.setText(intent.getStringExtra("pricePerNight"));
        totalPrice.setText(intent.getStringExtra("totalPrice"));

        home.setOnClickListener(view -> startActivity(new Intent(ViewRoom.this, userHomeActivity.class)));

        logout.setOnClickListener(view -> startActivity(new Intent(ViewRoom.this, MainActivity.class)));

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewRoom.this,ReverveRoomActivity.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewRoom.this,userHomeActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewRoom.this, MainActivity.class));
            }
        });
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ViewRoom.this,ReverveRoomActivity.class));

            }
        });
    }
}