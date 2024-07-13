package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Activities.adapter.RoomAdapter;
import com.example.myapp.R;
import com.example.myapp.Activities.entities.Room;

import java.util.ArrayList;
import java.util.List;

public class ManageRoom extends AppCompatActivity {

    private RecyclerView roomRecyclerView;
    private RoomAdapter roomAdapter;
    private List<Room> roomList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);

        roomRecyclerView = findViewById(R.id.room_recycler_view);
        Button addRoomButton = findViewById(R.id.add_room_button);

        roomList = new ArrayList<>();
        roomAdapter = new RoomAdapter(roomList, this);

        roomRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        roomRecyclerView.setAdapter(roomAdapter);

        addRoomButton.setOnClickListener(v -> {
            // Handle add room button click
            Intent intent = new Intent(ManageRoom.this, AddRoomActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                String roomNumber = data.getStringExtra("roomNumber");
                String roomType = data.getStringExtra("roomType");
                String pricePerNight = data.getStringExtra("pricePerNight");
                Room room = new Room();
                room.setRoomNumber(roomNumber);
                room.setRoomType(roomType);
                room.setPricePerNight(pricePerNight);
                roomList.add(room);
                roomAdapter.notifyItemInserted(roomList.size() - 1);
            }
        }
    }
}

