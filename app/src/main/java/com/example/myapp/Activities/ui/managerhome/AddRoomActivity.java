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

import com.example.myapp.R;

public class AddRoomActivity extends AppCompatActivity {

    EditText editHotelLocation, editNumberOfBeds, editRoomFacilities, editPricePerNight, editRoomNumber, editRoomStatus;
    Spinner spinnerHotelName, spinnerRoomType;
    Button btnAddRoom;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);

        spinnerHotelName = findViewById(R.id.spinner_hotel_name);
        editHotelLocation = findViewById(R.id.edit_hotel_location);
        spinnerRoomType = findViewById(R.id.spinner_room_type);
        editNumberOfBeds = findViewById(R.id.edit_number_of_beds);
        editRoomFacilities = findViewById(R.id.edit_room_facilities);
        editPricePerNight = findViewById(R.id.edit_price_per_night);
        editRoomNumber = findViewById(R.id.edit_room_number);
        editRoomStatus = findViewById(R.id.edit_room_status);
        btnAddRoom = findViewById(R.id.btn_add_room);

        btnAddRoom.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(AddRoomActivity.this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("hotelName", hotelName);
                    resultIntent.putExtra("hotelLocation", hotelLocation);
                    resultIntent.putExtra("roomType", roomType);
                    resultIntent.putExtra("numberOfBeds", numberOfBeds);
                    resultIntent.putExtra("roomFacilities", roomFacilities);
                    resultIntent.putExtra("pricePerNight", pricePerNight);
                    resultIntent.putExtra("roomNumber", roomNumber);
                    resultIntent.putExtra("roomStatus", roomStatus);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });
    }
}
