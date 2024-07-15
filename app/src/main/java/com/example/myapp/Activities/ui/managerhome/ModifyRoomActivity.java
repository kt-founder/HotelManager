package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.R;

public class ModifyRoomActivity extends AppCompatActivity {
    Button cancel_modify,availLogout,navigate_home;
    TextView rn ;
    EditText roomPrice;
    Spinner rtSpinner, roomStatSp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_modifi_room);

        DBContext dbContext = new DBContext(this);
        dbContext.open();
        Intent intent = getIntent();
        final String roomNumber = intent.getStringExtra("roomNumber");
        final String room_Price = intent.getStringExtra("roomPrice");
        String room_Type = intent.getStringExtra("roomType");
        Log.i("room number intent " , roomNumber);
        rn = (TextView)findViewById((R.id.room_number_text)) ;
        rn.setText(roomNumber);
        cancel_modify = (Button)findViewById(R.id.cancel_edit);
        cancel_modify.setMovementMethod(LinkMovementMethod.getInstance());
        cancel_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newRoomType = rtSpinner.getSelectedItem().toString();
                String newRoomStatus = roomStatSp.getSelectedItem().toString();
                String newRoomPrice = roomPrice.getText().toString();

                if (newRoomPrice.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid room price", Toast.LENGTH_LONG).show();
                    return;
                }

                try {
                    double price = Double.parseDouble(newRoomPrice);
                    if (dbContext.updateRoomDetails(roomNumber, price, newRoomStatus, newRoomType)) {
                        Toast.makeText(getApplicationContext(), "Modified Room Details Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(ModifyRoomActivity.this, AvailableRoomActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to update room details", Toast.LENGTH_LONG).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Invalid price format", Toast.LENGTH_LONG).show();
                }
            }
        });


        availLogout = findViewById(R.id.availLogout);

        availLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ModifyRoomActivity.this, MainActivity.class));
            }
        });

        navigate_home = (Button)findViewById(R.id.button8);
        navigate_home.setMovementMethod(LinkMovementMethod.getInstance());
        navigate_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyRoomActivity.this, ManagerHomeActivity.class);
                startActivity(intent);
            }
        });

        final Spinner rtSpinner = (Spinner) findViewById(R.id.roomTyps);
        //final String roomType = mySpinner.getSelectedItem().toString();
        rtSpinner.setSelection(((ArrayAdapter<String>)rtSpinner.getAdapter()).getPosition(room_Type));

        // final Spinner rtSpinnerFinal = rtSpinner;

        roomPrice = (EditText)findViewById(R.id.edit_room_price);
        roomPrice.setText(room_Price);
        final String rp = roomPrice.getText().toString();


        // Edit
        Button btnEdit = (Button)findViewById(R.id.save_edit);

        Spinner roomStatSp = (Spinner) findViewById(R.id.roomStat);
        final String roomStat = roomStatSp.getSelectedItem().toString();


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                // Displaying posotioned Toast message
                //db.updateRoomDetails();
                Log.i("inside save " ," inside save");
                final String rt = rtSpinner.getSelectedItem().toString();
                Log.i("inside save rt " ,rt);
                final String roomPr = roomPrice.getText().toString();
                Log.i("inside save roomPr " ,roomPr);
                //db.updateRoomDetails(roomNumber,roomPr,roomStat,rt);
                Toast t = Toast.makeText(getApplicationContext(),
                        "Modified Room Details Successfully",
                        Toast.LENGTH_LONG);

                t.show();

                startActivity(new Intent(ModifyRoomActivity.this,AvailableRoomActivity.class));
            }
        });

    }
}