package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

public class MViewListReservationActivity extends AppCompatActivity {
    EditText EditTextDate;
    Button ButtonLogOut, ButtonHome, ButtonListView;
    RadioGroup RadioGroupRoomType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mview_list_reservation);

        ButtonLogOut = findViewById(R.id.button_logout);
        ButtonHome = findViewById(R.id.button_home);
        ButtonListView = findViewById(R.id.buttonListView);
        EditTextDate = findViewById(R.id.editTextDate);
        RadioGroupRoomType = findViewById(R.id.radioGroup);

        ButtonListView.setOnClickListener(view -> {
            String date = EditTextDate.getText().toString();
            int selectedId = RadioGroupRoomType.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectedId);
            String roomType = radioButton != null ? radioButton.getText().toString() : "";

            if(roomType.isEmpty() || date.isEmpty()) {
                Toast.makeText(MViewListReservationActivity.this, "Please fill all fields!", Toast.LENGTH_LONG).show();
                return;
            }

            // Move Intent
            Intent intent = new Intent(MViewListReservationActivity.this, ListReservationManager.class);
            intent.putExtra("date", date);
            intent.putExtra("roomType", roomType);
            startActivity(intent);
        });

        ButtonLogOut.setOnClickListener(view -> startActivity(new Intent(MViewListReservationActivity.this, MainActivity.class)));

        ButtonHome.setOnClickListener(view -> startActivity(new Intent(MViewListReservationActivity.this, ManagerHomeActivity.class)));
    }
}
