package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.adapter.SearchRoomAdapter;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Hotel;
import com.example.myapp.Activities.entities.Room;
import com.example.myapp.R;

import java.util.ArrayList;

public class SearchRoomActivity extends AppCompatActivity {
    Button home, logout, search;
    EditText nadults, nchild, cidate, codate;
    Spinner Hloc, Hroom, HNroom;
    ListView room_listView;
    SearchRoomAdapter searchRoomAdapter;
    ArrayList<Room> arrayList;
    DBContext dbContext;
    SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_room);

        home = findViewById(R.id.userHome);
        logout = findViewById(R.id.logout);
        search = findViewById(R.id.search_r);

        nadults = findViewById(R.id.nadults);
        nchild = findViewById(R.id.nchild);
        cidate = findViewById(R.id.cidate);
        codate = findViewById(R.id.codate);
        Hloc = findViewById(R.id.Hloc);
        Hroom = findViewById(R.id.Hroom);
        HNroom = findViewById(R.id.HNroom);
        room_listView = findViewById(R.id.room_listView);

        dbContext = new DBContext(this);
        dbContext.open();

        // Set up the Spinners
        ArrayAdapter<CharSequence> adapterHotel = ArrayAdapter.createFromResource(this,
                R.array.hotel_location_array, android.R.layout.simple_spinner_item);
        adapterHotel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Hloc.setAdapter(adapterHotel);

        ArrayAdapter<CharSequence> adapterRoom = ArrayAdapter.createFromResource(this,
                R.array.room_type_array, android.R.layout.simple_spinner_item);
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Hroom.setAdapter(adapterRoom);

        ArrayAdapter<CharSequence> adapterNumRoom = ArrayAdapter.createFromResource(this,
                R.array.noOfRooms, android.R.layout.simple_spinner_item);
        adapterNumRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        HNroom.setAdapter(adapterNumRoom);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchRoomActivity.this, userHomeActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchRoomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchRooms();
                // Your search functionality here
            }
        });
    }
    private void searchRooms() {
        String location = Hloc.getSelectedItem().toString();
        String roomType = Hroom.getSelectedItem().toString();

        arrayList = dbContext.getRoomDetails(location, roomType);

        if (arrayList.isEmpty()) {
            Toast.makeText(SearchRoomActivity.this, "No rooms found", Toast.LENGTH_SHORT).show();
        } else {
            searchRoomAdapter = new SearchRoomAdapter(SearchRoomActivity.this, arrayList);
            room_listView.setAdapter(searchRoomAdapter);
            searchRoomAdapter.notifyDataSetChanged();
        }
    }
    private int calculateNumOfNights(String[] checkinArr, String[] checkoutArr) {
        int checkinDay = Integer.parseInt(checkinArr[0]);
        int checkinMonth = Integer.parseInt(checkinArr[1]);
        int checkinYear = Integer.parseInt(checkinArr[2]);

        int checkoutDay = Integer.parseInt(checkoutArr[0]);
        int checkoutMonth = Integer.parseInt(checkoutArr[1]);
        int checkoutYear = Integer.parseInt(checkoutArr[2]);

        // Calculate number of nights
        int numOfNights = (checkoutYear - checkinYear) * 365 + (checkoutMonth - checkinMonth) * 30 + (checkoutDay - checkinDay);
        return numOfNights;
    }
}
