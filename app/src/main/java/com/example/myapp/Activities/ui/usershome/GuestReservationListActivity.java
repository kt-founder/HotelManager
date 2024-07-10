package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

public class GuestReservationListActivity extends AppCompatActivity {
    Button ButtonLogOut, ButtonHome;
    ListView LISTVIEW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guest_reservation_list);

        LISTVIEW =  findViewById(R.id.listView1);
        ButtonLogOut= findViewById(R.id.button_logout);
        ButtonHome = findViewById(R.id.button_home);

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestReservationListActivity.this,userHomeActivity.class);
                startActivity(intent);
            }
        });
        ButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestReservationListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        myAdapter.add("Reservation 1 ");
        myAdapter.add("Reservation 2 ");
        myAdapter.add("Reservation 3 ");
        myAdapter.add("Reservation 4 ");
        myAdapter.add("Reservation 5 ");
        LISTVIEW.setAdapter(myAdapter);
        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GuestReservationListActivity.this, DetailReservationActivity.class);
                startActivity(intent);
            }
        });
    }
}