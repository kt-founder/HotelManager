package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
        String HotelName = intent.getStringExtra("hotelName");
        String HotelLocation = intent.getStringExtra("hotelLocation");
        String RoomType = intent.getStringExtra("roomType");
        String NumBeds = intent.getStringExtra("numberOfBeds");
        String RoomFacilites = intent.getStringExtra("roomFacilities");
        String checkIn = intent.getStringExtra("checkInDate");
        String checkOut = intent.getStringExtra("checkOutDate");
        String numNights = intent.getStringExtra("numberOfNights");
        String Price = intent.getStringExtra("pricePerNight");
        String Total = String.valueOf(Double.parseDouble(Price) * Integer.parseInt(numNights));
        String roomNum = intent.getStringExtra("roomNumber");
        Log.e("Test",HotelName);
        Log.e("Test",HotelLocation);
        Log.e("Test",roomNum);
        hotelName.setText(HotelName);
        hotelLocation.setText(HotelLocation);
        roomType.setText(RoomType);
        nBeds.setText(NumBeds);
        rFacilities.setText(RoomFacilites);
        cid.setText(checkIn);
        cod.setText(checkOut);
        nNights.setText(numNights);
        price.setText(Price);
        totalPrice.setText(Total);

        home.setOnClickListener(view -> startActivity(new Intent(ViewRoom.this, userHomeActivity.class)));

        logout.setOnClickListener(view -> startActivity(new Intent(ViewRoom.this, MainActivity.class)));

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ViewRoom.this,ReverveRoomActivity.class);

                intent1.putExtra("hotelName",HotelName);

                intent1.putExtra("hotelLocation", HotelLocation );
                intent1.putExtra("roomType", RoomType);
                intent1.putExtra("pricePerNight", Price);
                intent1.putExtra("numberOfBeds", NumBeds);
                intent1.putExtra("roomFacilities", RoomFacilites );
                intent1.putExtra("checkInDate", checkIn);
                intent1.putExtra("checkOutDate", checkOut);
                intent1.putExtra("numberOfNights", numNights);
                intent1.putExtra("totalPrice", Total);
                intent1.putExtra("roomNumber",roomNum);
                startActivity(intent1);
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

    }
}