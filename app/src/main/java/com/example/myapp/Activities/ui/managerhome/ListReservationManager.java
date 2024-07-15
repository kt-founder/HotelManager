package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.List;

public class ListReservationManager extends AppCompatActivity {
    private DBContext dbContext;
    private ListView listView;
    private Button buttonLogOut, buttonHome;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> reservationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reservation_manager);

        dbContext = new DBContext(this);
        dbContext.open();

        listView = findViewById(R.id.listView1);
        buttonLogOut = findViewById(R.id.button_logout);
        buttonHome = findViewById(R.id.button_home);

        reservationDetails = new ArrayList<>();

        // Fetch data
        List<Reservation> reservations = dbContext.getAllReservations(); // Implement this method in DBContext
        for (Reservation reservation : reservations) {
            reservationDetails.add(reservation.toString());  // Make sure your Reservation class has a proper toString() method.
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reservationDetails);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Reservation selectedReservation = reservations.get(position);
            Intent intent = new Intent(ListReservationManager.this, DetailReservationActivity.class);
            intent.putExtra("booking_id", selectedReservation.getBooking_id());
            startActivity(intent);
        });

        buttonLogOut.setOnClickListener(v -> startActivity(new Intent(ListReservationManager.this, MainActivity.class)));

        buttonHome.setOnClickListener(v -> startActivity(new Intent(ListReservationManager.this, ManagerHomeActivity.class)));
    }
}
