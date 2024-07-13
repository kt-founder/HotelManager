package com.example.myapp.Activities.ui.managerhome;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Room;
import com.example.myapp.R;

public class SearchRoomManagerActivity extends AppCompatActivity {

    EditText editText1;
    Button searchRoom;
    TextView roomNumber, roomType, roomPrice, roomStatus;
    View detailRoom;
    DBContext dbContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_room_manager);

        editText1 = findViewById(R.id.editText1);
        searchRoom = findViewById(R.id.search_room);
        roomNumber = findViewById(R.id.room_number);
        roomType = findViewById(R.id.room_type);
        roomPrice = findViewById(R.id.room_price);
        roomStatus = findViewById(R.id.room_status);
        detailRoom = findViewById(R.id.DetailRoom);

        dbContext = new DBContext(this);
        dbContext.open();

        searchRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomNum = editText1.getText().toString().trim();
                if (roomNum.isEmpty()) {
                    Toast.makeText(SearchRoomManagerActivity.this, "Please enter a room number", Toast.LENGTH_SHORT).show();
                } else {
                    Room room = dbContext.getRoom(roomNum);
                    if (room != null) {
                        roomNumber.setText(room.getRoomNumber());
                        roomType.setText(room.getRoomType());
                        roomPrice.setText(room.getPricePerNight());
                        roomStatus.setText(room.getRoomStatus());
                        detailRoom.setVisibility(View.VISIBLE);
                    } else {
                        Toast.makeText(SearchRoomManagerActivity.this, "Room not found", Toast.LENGTH_SHORT).show();
                        detailRoom.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbContext.close();
    }
}
