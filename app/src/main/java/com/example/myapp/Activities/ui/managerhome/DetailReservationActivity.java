package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

public class DetailReservationActivity extends AppCompatActivity {
    Button ButtonLogOut, ButtonHome;
    TextView idE, firstNameE, lastNameE, roomTypeE, checkInDateE, checkOutDateE, numberOfRoomE, numberOfAdultsE, numberOfChildrenE, totalPriceE;
    DBContext db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail_reservation2);

        db = new DBContext(this);
        db.open();
        bindViews();

        Intent intent = getIntent();
        if (intent != null) {
            setReservationDetails(intent);
        }

        setupButtons();
    }

    private void bindViews() {
        ButtonLogOut = findViewById(R.id.button_logout);
        ButtonHome = findViewById(R.id.button_home);
        idE = findViewById(R.id.textViewID);
        firstNameE = findViewById(R.id.textViewFirstNAME);
        lastNameE = findViewById(R.id.textViewLastNAME);
        roomTypeE = findViewById(R.id.textViewRoomType);
        checkInDateE = findViewById(R.id.textViewCheckInData);
        checkOutDateE = findViewById(R.id.textViewCheckOutData);
        numberOfRoomE = findViewById(R.id.textViewNumberOfRoom);
        numberOfAdultsE = findViewById(R.id.textViewAdults);
        numberOfChildrenE = findViewById(R.id.textViewChildren);
        totalPriceE = findViewById(R.id.textViewPrice);
    }

    private void setReservationDetails(Intent intent) {
        String bookingId = intent.getStringExtra("booking_id");
        if (bookingId == null) {
            // Handle case where booking ID is not passed or is null
            Toast.makeText(this, "Booking ID is missing!", Toast.LENGTH_LONG).show();
            return;
        }

        Reservation reservation = db.getReservationByBookingId(bookingId);
        if (reservation == null) {
            // Handle case where no reservation is found for the given booking ID
            Toast.makeText(this, "No reservation found for the given ID", Toast.LENGTH_LONG).show();
            return;
        }

        // Setting the text views with the data from the reservation object
        idE.setText(reservation.getBooking_id());
        firstNameE.setText(reservation.getFirst_name());
        lastNameE.setText(reservation.getLast_name());
        roomTypeE.setText(reservation.getRoom_type());
        checkInDateE.setText(reservation.getCheck_in_date());
        checkOutDateE.setText(reservation.getCheck_out_date());
        numberOfRoomE.setText(reservation.getNumber_of_rooms());
        numberOfAdultsE.setText(reservation.getNumber_of_adults());
        numberOfChildrenE.setText(reservation.getNumber_of_children());
        totalPriceE.setText(String.valueOf(reservation.getTotal_price()));
    }


    private void setupButtons() {
        ButtonLogOut.setOnClickListener(v -> startActivity(new Intent(DetailReservationActivity.this, MainActivity.class)));
        ButtonHome.setOnClickListener(v -> startActivity(new Intent(DetailReservationActivity.this, ManagerHomeActivity.class)));
    }
}

