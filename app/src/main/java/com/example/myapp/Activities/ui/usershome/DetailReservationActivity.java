package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

public class DetailReservationActivity extends AppCompatActivity {

    Button home, logout, close;
    TextView idE, firstNameE, lastNameE, roomTypeE, checkInDateE, checkOutDateE, numberOfRoomE, numberOfAdultsE, numberOfChildrenE, totalPriceE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_reservation);

        Intent intent = getIntent();
        String bookingId = intent.getStringExtra("booking_id");

        DBContext db = new DBContext(this);
        db.open();

        Reservation reservation = db.getReservationByBookingId(bookingId);

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

        // Set data to TextViews
        idE.setText(reservation.getBooking_id());
        firstNameE.setText(reservation.getFirst_name());
        lastNameE.setText(reservation.getLast_name());
        roomTypeE.setText(reservation.getRoom_type());
        checkInDateE.setText(reservation.getCheck_in_date());
        checkOutDateE.setText(reservation.getCheck_out_date());
        numberOfRoomE.setText(reservation.getNumber_of_rooms());
        numberOfAdultsE.setText(reservation.getNumber_of_adults());
        numberOfChildrenE.setText(reservation.getNumber_of_children());
        totalPriceE.setText(reservation.getTotal_price());  // Format for currency display

        logout = findViewById(R.id.button_logout);
        home = findViewById(R.id.button_home);
        close = findViewById(R.id.bt_close);

        home.setOnClickListener(v -> startActivity(new Intent(DetailReservationActivity.this, userHomeActivity.class)));
        logout.setOnClickListener(v -> startActivity(new Intent(DetailReservationActivity.this, MainActivity.class)));
        close.setOnClickListener(v -> startActivity(new Intent(DetailReservationActivity.this, GuestReservationListActivity.class)));
    }
}
