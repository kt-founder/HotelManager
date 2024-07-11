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

public class ReverveRoomActivity extends AppCompatActivity {

    Button save,cont, logout, home;
    EditText hotelName,hotelLocation, roomType,nBeds,rFacilities,cid,cod,nNights,price,totalPrice,reserveNumRooms,resNumAdults,reserveNumChildre,reserveBooking;

    SharedPreferences sharedpreferences;

    public static final String SHARED_PREF_NAME = "mypref";
    public static final String KEY_BOOKING = "booking_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reverve_room);

//        sharedpreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
//        final String rt = sharedpreferences.getString(searchRoomAdapter.KEY_rt,"");
//        final String hn = sharedpreferences.getString(searchRoomAdapter.KEY_hn,"");
//        final String check = sharedpreferences.getString(searchRoomScreen.KEY_CHECKINDATE,"");
//        final String check_out = sharedpreferences.getString(searchRoomScreen.KEY_CHECKOUTDATE,"");
//        final String numNights = sharedpreferences.getString(searchRoomScreen.KEY_NUMBEROFNIGHTS,"");
//        final String numRooms = sharedpreferences.getString(searchRoomScreen.KEY_NUMBEROFROOMS,"");
//        final String adults = sharedpreferences.getString(searchRoomScreen.KEY_ADULT,"");
//        final String child = sharedpreferences.getString(searchRoomScreen.KEY_CHILDREN,"");
//        final String fn = sharedpreferences.getString(MainActivity.KEY_FIRSTNAME,"");
//        final String ln = sharedpreferences.getString(MainActivity.KEY_LASTNAME,"");
//        final String un = sharedpreferences.getString(MainActivity.KEY_USERNAME,"");


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

        hotelName.setFocusable(false);
        hotelLocation.setFocusable(false);
        roomType.setFocusable(false);
        roomType.setFocusable(false);
        cid.setFocusable(false);
        cod.setFocusable(false);
        nNights.setFocusable(false);
        reserveNumRooms.setFocusable(false);
        resNumAdults.setFocusable(false);
        reserveNumChildre.setFocusable(false);
        reserveBooking.setFocusable(false);
        price.setFocusable(false);
        totalPrice.setFocusable(false);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ReverveRoomActivity.this,PendingRoomActivity.class));

            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ReverveRoomActivity.this,Payment.class));

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