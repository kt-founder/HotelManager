package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    SQLiteDatabase sqLiteDatabaseObj;
    EditText EditTextDate;
    Button ButtonLogOut, ButtonHome, ButtonListView;
    RadioGroup RadioGroupRoomType;
    public RadioButton RadioButtonStandard, RadioButtonDeluxe, RadioButtonSuite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mview_list_reservation);

        ButtonLogOut = (Button)findViewById(R.id.button_logout);
        ButtonHome = (Button) findViewById(R.id.button_home);
        ButtonListView = (Button) findViewById(R.id.buttonListView);
        EditTextDate = (EditText)findViewById(R.id.editTextDate);
        //
        RadioGroupRoomType = (RadioGroup) findViewById(R.id.radioGroup);
        
        RadioButtonStandard = (RadioButton) findViewById(R.id.radioButtonStandard);
        RadioButtonDeluxe = (RadioButton) findViewById(R.id.radioButtonDeluxe);
        RadioButtonSuite = (RadioButton) findViewById(R.id.radioButtonSuite);

        ButtonListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date = EditTextDate.getText().toString();
                String roomType = "";
                if( RadioButtonStandard.isChecked() && !RadioButtonDeluxe.isChecked() && !RadioButtonSuite.isChecked()){
                    roomType = "Standard";
                }else if(RadioButtonStandard.isChecked() && !RadioButtonDeluxe.isChecked() && !RadioButtonSuite.isChecked()){
                    roomType = "Deluxe";
                }else if(RadioButtonStandard.isChecked() && !RadioButtonDeluxe.isChecked() && !RadioButtonSuite.isChecked()){
                    roomType = "Suite";
                }else{
                    Toast.makeText(MViewListReservationActivity.this,"Please check Once!", Toast.LENGTH_LONG).show();
                }


                if(roomType.isEmpty() || date.isEmpty()) {      // add default date and default room type

                    Toast.makeText(MViewListReservationActivity.this,"Please fill the reservation date!", Toast.LENGTH_LONG).show();
                    return;
                }
                // Move Intent
                Intent intent = new Intent(MViewListReservationActivity.this, ListReservationManager.class);
                intent.putExtra("date", date);
                intent.putExtra("roomType", roomType);
                startActivity(intent);
            }
        });
        ButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MViewListReservationActivity.this, MainActivity.class));
            }
        });

        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MViewListReservationActivity.this, ManagerHomeActivity.class));
            }
        });

    }
}