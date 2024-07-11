package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

import java.util.HashMap;

public class SearchRoomManagerActivity extends AppCompatActivity {

    Button navigate_home, search_room, log, availLogout;
    TextView room_number , room_status , room_price , room_type ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_room_manager);

        navigate_home = (Button)findViewById(R.id.button8);
        navigate_home.setMovementMethod(LinkMovementMethod.getInstance());
        navigate_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SearchRoomManagerActivity.this, ManagerHomeActivity.class);
                startActivity(intent);
            }
        });

        log = findViewById(R.id.availLogout);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchRoomManagerActivity.this, MainActivity.class));
            }
        });

        search_room = (Button)findViewById(R.id.search_room);
        search_room.setMovementMethod(LinkMovementMethod.getInstance());
        search_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent roomintent = new Intent(Searchroom.this, Searchroom.class);
                Log.i("inside oncick ","inside oncick");
                room_number = (TextView)findViewById( R.id.room_number );
                room_status = (TextView)findViewById( R.id.room_status );
                room_price = (TextView)findViewById( R.id.room_price );
                room_type = (TextView)findViewById( R.id.room_type );
                /*room_number.setText("1234");
                room_status.setText("occ");
                room_price.setText("1000");
                room_type.setText("st");*/

                EditText editText=findViewById(R. id. editText1);
                String roomNumber =editText. getText(). toString();
                //HashMap<String,String> roomMap =  db.getHotelData(roomNumber);
                HashMap<Integer, HashMap<String, String>> hotelData = new HashMap<>();
                room_number = (TextView)findViewById( R.id.room_number );
                room_status = (TextView)findViewById( R.id.room_status );
                room_price = (TextView)findViewById( R.id.room_price );
                room_type = (TextView)findViewById( R.id.room_type );

                // Sample Data
                HashMap<String, String> room101 = new HashMap<>();
                room101.put("roomNumber", "101");
                room101.put("checkInDate", "2024-07-01");
                room101.put("checkOutDate", "2024-07-05");
                room101.put("guestName", "John Doe");
                hotelData.put(101, room101);

                // Room details for room number 102
                HashMap<String, String> room102 = new HashMap<>();
                room102.put("roomNumber", "102");
                room102.put("checkInDate", "2024-07-10");
                room102.put("checkOutDate", "2024-07-15");
                room102.put("guestName", "Alice Johnson");
                hotelData.put(102, room102);

//                room_number.setText(roomMap.get("RoomNumber"));
//                room_status.setText(roomMap.get("roomStatus"));
//                room_price.setText(roomMap.get("pricePerNight"));
//                room_type.setText(roomMap.get("roomType"));
                Log.i("RoomNumber ",room_number.getText().toString());
                Log.i("roomStatus ",room_status.getText().toString());
                Log.i("pricePerNight ",room_price.getText().toString());
                Log.i("roomType ",room_type.getText().toString());
                //startActivity(roomintent);
            }
        });
    }
}