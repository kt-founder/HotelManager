package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.R;

public class ReverveRoomActivity extends AppCompatActivity {

    Button save,cont, logout, home;
    public static final String PREFS_NAME = "AppPrefs";
    public static final String KEY_USERNAME = "username";
    EditText hotelName,hotelLocation, roomType,nBeds,rFacilities,cid,cod,nNights,price,totalPrice,reserveNumRooms,resNumAdults,reserveNumChildre,reserveBooking;

    DBContext dbContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reverve_room);
        dbContext = new DBContext(this);

        dbContext.open();

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
        String total = intent.getStringExtra("totalPrice");
        String roomNumber = intent.getStringExtra("roomNumber");
        save = findViewById(R.id.reserveSave);
        cont = findViewById(R.id.reserveConfirm);
        logout = findViewById(R.id.viewLogout);
        home = findViewById(R.id.viewHome);

        hotelName = findViewById(R.id.viewHotelName);
        hotelLocation = findViewById(R.id.viewHotelLocation);
        roomType = findViewById(R.id.viewRoomType);

//        nBeds = findViewById(R.id.viewBeds);
//        rFacilities = findViewById(R.id.viewRoomFacilities);

        cid = findViewById(R.id.viewCheckInDate);
        cod = findViewById(R.id.viewCheckOutDate);
        nNights = findViewById(R.id.viewNights);
        price = findViewById(R.id.viewPrice);
        totalPrice = findViewById(R.id.viewTotalPrice);

        reserveNumRooms = findViewById(R.id.reserveNumRooms);
        resNumAdults = findViewById(R.id.reserveNumAdults);
        reserveNumChildre = findViewById(R.id.reserveNumChildren);

        reserveBooking = findViewById(R.id.reserveBooking);

//        Log.d("Test",HotelName);
//        Log.d("Test",HotelLocation);
//        Log.d("Test",roomNumber);
//        Log.d("Test",HotelName);
//        Log.d("Test",HotelName);
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = preferences.getString(KEY_USERNAME, "");

        hotelName.setText(HotelName);
        hotelLocation.setText(HotelLocation);
        roomType.setText(RoomType);
        cid.setText(checkIn);
        cod.setText(checkOut);
        nNights.setText(numNights);
        price.setText(Price);
        totalPrice.setText(total);
        reserveNumRooms.setText(roomNumber);
        reserveBooking.setText(username + roomNumber + checkIn.substring(0,2));

        reserveNumChildre.setHint("enter number Childern");
        resNumAdults.setHint("enter number Adults");





        hotelName.setFocusable(false);
        hotelLocation.setFocusable(false);
        roomType.setFocusable(false);
        roomType.setFocusable(false);
        cid.setFocusable(false);
        cod.setFocusable(false);
        nNights.setFocusable(false);
        reserveNumRooms.setFocusable(false);
//        resNumAdults.setFocusable(false);
//        reserveNumChildre.setFocusable(false);
        reserveBooking.setFocusable(false);
        price.setFocusable(false);
        totalPrice.setFocusable(false);

        cont.setFocusable(false);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double tax = Double.parseDouble(total) * 0.08;

                Log.d("Erorr",username);
                Profile userProfile = new Profile() ;
                if (!username.isEmpty()) {
                    // Use the username as needed
                    userProfile = dbContext.getUser(username);
                } else {
                    Log.d("Erorr","K co user");
                }

                boolean isInserted = dbContext.insertReservation(hotelName.getText().toString(),
                        hotelLocation.getText().toString(), roomType.getText().toString(), reserveNumRooms.getText().toString(),
                        nNights.getText().toString(), resNumAdults.getText().toString(), reserveNumChildre.getText().toString(),
                        cid.getText().toString(), cod.getText().toString(), price.getText().toString(),
                        String.valueOf(tax), totalPrice.getText().toString(), String.valueOf(tax + Double.parseDouble(total)), userProfile.getStreetAddress(),
                        userProfile.getFirstName(), userProfile.getLastName(), "16/07/2024", username,"pending",username + roomNumber + checkIn.substring(0,2));

                if(isInserted) {
                    Toast.makeText(ReverveRoomActivity.this, "Reservation Saved Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(ReverveRoomActivity.this, PendingRoomActivity.class);
                    startActivity(intent1);

                } else {
                    Toast.makeText(ReverveRoomActivity.this, "Failed to Save Reservation", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double tax = Double.parseDouble(total) * 0.08;
                Log.d("Erorr",username);
                Profile userProfile = new Profile() ;
                if (!username.isEmpty()) {
                    // Use the username as needed
                    userProfile = dbContext.getUser(username);
                } else {
                    Log.d("Erorr","K co user");
                }

                boolean isInserted = dbContext.insertReservation(hotelName.getText().toString(),
                        hotelLocation.getText().toString(), roomType.getText().toString(), reserveNumRooms.getText().toString(),
                        nNights.getText().toString(), resNumAdults.getText().toString(), reserveNumChildre.getText().toString(),
                        cid.getText().toString(), cod.getText().toString(), price.getText().toString(),
                        String.valueOf(tax), totalPrice.getText().toString(), String.valueOf(tax + Double.parseDouble(total)), userProfile.getStreetAddress(),
                        userProfile.getFirstName(), userProfile.getLastName(), "16/07/2024", username,"pending",username + roomNumber + checkIn.substring(0,2));

                if(isInserted) {
                    Toast.makeText(ReverveRoomActivity.this, "Go to payment", Toast.LENGTH_SHORT).show();

                    String bookingID = username + roomNumber + checkIn.substring(0,2);
                    Intent intent1 = new Intent(ReverveRoomActivity.this,Payment.class);
                    intent1.putExtra("booking_id",bookingID);
                    startActivity(intent1);

                } else {
                    Toast.makeText(ReverveRoomActivity.this, "Failed to Save Reservation", Toast.LENGTH_SHORT).show();
                }



            }
        });


        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReverveRoomActivity.this,userHomeActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ReverveRoomActivity.this, MainActivity.class));
            }
        });

    }
}