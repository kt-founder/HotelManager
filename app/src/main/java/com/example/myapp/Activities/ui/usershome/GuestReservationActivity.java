package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.R;

public class GuestReservationActivity extends AppCompatActivity {
    Button ButtonLogOut, ButtonHome, ButtonListView;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_guest_reservation);
        DBContext db = new DBContext(this);
        db.open();

        ButtonLogOut = (Button)findViewById(R.id.button_logout);
        ButtonHome = (Button) findViewById(R.id.button_home);
        ButtonListView = findViewById(R.id.buttonListView);
        editText = findViewById(R.id.editTextDate);
        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestReservationActivity.this, userHomeActivity.class);
                startActivity(intent);
            }
        });

        ButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestReservationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ButtonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuestReservationActivity.this, GuestReservationListActivity.class);
                if(editText.getText().toString().trim().isEmpty()){
                    editText.setError("Please fill Date");
                }else{
                    String date = editText.getText().toString();
                    intent.putExtra("Date",date);
                    startActivity(intent);
                }
            }
        });
    }
}