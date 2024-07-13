package com.example.myapp.Activities.ui.managerhome;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.entities.Room;
import com.example.myapp.R;

import java.io.Serializable;

public class EditRoomActivity extends AppCompatActivity {

    EditText editHotelLocation, editNumberOfBeds, editRoomFacilities, editPricePerNight, editRoomNumber, editRoomStatus;
    Spinner spinnerHotelName, spinnerRoomType;
    Button btnSaveRoom;
    private int position;
    private Room room;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        spinnerHotelName = findViewById(R.id.spinner_hotel_name);
        editHotelLocation = findViewById(R.id.edit_hotel_location);
        spinnerRoomType = findViewById(R.id.spinner_room_type);
        editNumberOfBeds = findViewById(R.id.edit_number_of_beds);
        editRoomFacilities = findViewById(R.id.edit_room_facilities);
        editPricePerNight = findViewById(R.id.edit_price_per_night);
        editRoomNumber = findViewById(R.id.edit_room_number);
        editRoomStatus = findViewById(R.id.edit_room_status);
        btnSaveRoom = findViewById(R.id.btn_save_room);

        // Nhận dữ liệu từ intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("room")) {
            room = (Room) intent.getSerializableExtra("room");
            position = intent.getIntExtra("position", -1);
            if (room != null) {
                // Đặt các giá trị của phòng lên các view
                spinnerHotelName.setSelection(getSpinnerIndex(spinnerHotelName, room.getHotelName()));
                editHotelLocation.setText(room.getHotelLocation());
                spinnerRoomType.setSelection(getSpinnerIndex(spinnerRoomType, room.getRoomType()));
                editNumberOfBeds.setText(room.getNumberOfBeds());
                editRoomFacilities.setText(room.getRoomFacilities());
                editPricePerNight.setText(room.getPricePerNight());
                editRoomNumber.setText(room.getRoomNumber());
                editRoomStatus.setText(room.getRoomStatus());
            }
        }

        btnSaveRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hotelName = spinnerHotelName.getSelectedItem().toString();
                String hotelLocation = editHotelLocation.getText().toString();
                String roomType = spinnerRoomType.getSelectedItem().toString();
                String numberOfBeds = editNumberOfBeds.getText().toString();
                String roomFacilities = editRoomFacilities.getText().toString();
                String pricePerNight = editPricePerNight.getText().toString();
                String roomNumber = editRoomNumber.getText().toString();
                String roomStatus = editRoomStatus.getText().toString();

                if (hotelLocation.isEmpty() || numberOfBeds.isEmpty() || roomFacilities.isEmpty() || pricePerNight.isEmpty() ||
                        roomNumber.isEmpty() || roomStatus.isEmpty()) {
                    Toast.makeText(EditRoomActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Cập nhật đối tượng phòng
                    room.setHotelName(hotelName);
                    room.setHotelLocation(hotelLocation);
                    room.setRoomType(roomType);
                    room.setNumberOfBeds(numberOfBeds);
                    room.setRoomFacilities(roomFacilities);
                    room.setPricePerNight(pricePerNight);
                    room.setRoomNumber(roomNumber);
                    room.setRoomStatus(roomStatus);

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("room", (Serializable) room);
                    resultIntent.putExtra("position", position);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }

    private int getSpinnerIndex(Spinner spinner, String value) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(value)) {
                return i;
            }
        }
        return 0;
    }
}
