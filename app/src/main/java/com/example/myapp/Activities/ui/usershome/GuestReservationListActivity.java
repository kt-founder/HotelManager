package com.example.myapp.Activities.ui.usershome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.adapter.ReservationAdapter;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

import java.util.List;

public class GuestReservationListActivity extends AppCompatActivity {
    Button ButtonLogOut, ButtonHome;
    ListView LISTVIEW;
    public static final String PREFS_NAME = "AppPrefs";
    public static final String KEY_USERNAME = "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guest_reservation_list);

        String Date = getIntent().getStringExtra("Date");
        LISTVIEW = findViewById(R.id.listView1);
        ButtonLogOut = findViewById(R.id.button_logout);
        ButtonHome = findViewById(R.id.button_home);

        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = preferences.getString(KEY_USERNAME, "");
        DBContext db = new DBContext(this);
        db.open();

        List<Reservation> reservations = db.getReservationsByDate(Date, username);
        if(reservations.isEmpty()){
            Toast.makeText(GuestReservationListActivity.this,"Nothing to show",Toast.LENGTH_SHORT).show();
        }
        ReservationAdapter adapter = new ReservationAdapter(this,reservations);
        LISTVIEW.setAdapter(adapter);

        LISTVIEW.setOnItemClickListener((parent, view, position, id) -> {
            Reservation reservation = adapter.getItem(position);
            if (reservation != null) {
                showReservationDetails(reservation);
            }
        });

        ButtonHome.setOnClickListener(v -> startActivity(new Intent(this, userHomeActivity.class)));
        ButtonLogOut.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
    }


    private void showReservationDetails(Reservation reservation) {
        Intent intent = new Intent(GuestReservationListActivity.this, DetailReservationActivity.class);
        intent.putExtra("booking_id", reservation.getBooking_id());
        startActivity(intent);
    }
}
