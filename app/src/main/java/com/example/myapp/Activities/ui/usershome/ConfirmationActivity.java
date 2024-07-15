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
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

public class ConfirmationActivity extends AppCompatActivity {

    Button logout, home;
    EditText hotelName,hotelLocation, tax,fn,ln,billedAddr,billedPrice,cid,cod,nNights,reserveNumRooms,resNumAdults,reserveNumChildre,reserveBooking;
    SharedPreferences sharedpreferences;

    public static final String SHARED_PREF_NAME = "mypref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirmation);
        Intent intent = getIntent();
        String bookingId = intent.getStringExtra("booking_id");
        DBContext db = new DBContext(this);
        db.open();
        Reservation reservation = db.getReservationByBookingId(bookingId);

        logout = findViewById(R.id.confirmHome);
        home = findViewById(R.id.confirmLogout);


        tax = findViewById(R.id.confirmTax);
        fn = findViewById(R.id.confirmFirst);
        ln = findViewById(R.id.confirmLast);
        billedAddr = findViewById(R.id.confirmBilledAddress);
        billedPrice = findViewById(R.id.confirmBilledPrice);
        hotelName = findViewById(R.id.viewHotelName);
        hotelLocation = findViewById(R.id.viewHotelLocation);
        cid = findViewById(R.id.viewCheckInDate);
        cod = findViewById(R.id.viewCheckOutDate);
        nNights = findViewById(R.id.viewNights);

        reserveNumRooms = findViewById(R.id.reserveNumRooms);
        resNumAdults = findViewById(R.id.reserveNumAdults);
        reserveNumChildre = findViewById(R.id.reserveNumChildren);
        reserveBooking = findViewById(R.id.reserveBooking);

        hotelName.setText(reservation.getHotel_name());
        hotelLocation.setText(reservation.getHotel_location());
        cid.setText(reservation.getCheck_in_date());
        // Complete it

        hotelName.setText(reservation.getHotel_name());
        hotelLocation.setText(reservation.getHotel_location());
        cid.setText(reservation.getCheck_in_date());
        cod.setText(reservation.getCheck_out_date());
        nNights.setText(reservation.getNumber_of_nights());
        reserveNumRooms.setText(reservation.getRoomNumber());
        resNumAdults.setText(reservation.getNumber_of_adults());
        reserveNumChildre.setText(reservation.getNumber_of_children());
        reserveBooking.setText(reservation.getBooking_id());
        tax.setText(reservation.getTax());
        billedPrice.setText(reservation.getBilled_price());
        fn.setText(reservation.getFirst_name());
        ln.setText(reservation.getLast_name());
        billedAddr.setText(reservation.getBilling_address());

        db.updateRoomStatus(reservation.getRoomNumber(),"Served");

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmationActivity.this,userHomeActivity.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfirmationActivity.this, MainActivity.class));
            }
        });
    }
}