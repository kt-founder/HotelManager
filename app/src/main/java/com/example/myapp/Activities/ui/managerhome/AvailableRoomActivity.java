package com.example.myapp.Activities.ui.managerhome;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBManager;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AvailableRoomActivity extends AppCompatActivity {
    DatePickerDialog picker;
    EditText eText,sText;
    DBManager db;
    Button modify_room,navigate_home,view_available_rooms,availLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_available_room);

        availLogout = findViewById(R.id.availLogout);

        availLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AvailableRoomActivity.this, MainActivity.class));
            }
        });

        navigate_home = (Button)findViewById(R.id.button8);
        navigate_home.setMovementMethod(LinkMovementMethod.getInstance());
        navigate_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvailableRoomActivity.this, ManagerHomeActivity.class);
                startActivity(intent);
            }
        });
        eText=(EditText) findViewById(R.id.date_text);
        eText.setInputType(InputType.TYPE_NULL);

        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(AvailableRoomActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText( (monthOfYear + 1) +  "/" + dayOfMonth + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });

        sText=(EditText) findViewById(R.id.time_text);
        sText.setInputType(InputType.TYPE_NULL);
        sText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(AvailableRoomActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        sText.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);//NO 24 hour time

                mTimePicker.show();

            }
        });


        view_available_rooms = findViewById(R.id.avl_room_search_btn);
        view_available_rooms.setMovementMethod(LinkMovementMethod.getInstance());

        view_available_rooms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent roomintent = new Intent(Searchroom.this, Searchroom.class);
                Log.i("inside oncick ","inside oncick");
                eText = (EditText)findViewById( R.id.date_text );
                sText = (EditText)findViewById( R.id.time_text );
                List<HashMap<String,String>> roomMapList = new ArrayList<>();

                Spinner mySpinner = (Spinner) findViewById(R.id.room_typ);
                String roomType = mySpinner.getSelectedItem().toString();

                String checkInDate =eText. getText(). toString();
                String checkInTime = sText. getText(). toString();
//                if(roomType != null && roomType.equalsIgnoreCase("All")) {
//                    roomMapList = db.getAvailableRooms(checkInDate + " " + checkInTime);
//                    Log.i("room type spinner " , roomType);
//                }
//                else
//                {
//                    roomMapList = db.getAvailableRoomsByType(checkInDate + " " + checkInTime , roomType);
//                    Log.i("room type spinner " , roomType);
//                }
                HashMap<String, String> roomDetails1 = new HashMap<>();
                roomDetails1.put("checkInDate", "2024-01-01");
                roomDetails1.put("checkOutDate", "2024-01-05");
                roomMapList.add(roomDetails1);

                // Sample Data Entry 2
                HashMap<String, String> roomDetails2 = new HashMap<>();
                roomDetails2.put("checkInDate", "2024-02-01");
                roomDetails2.put("checkOutDate", "2024-02-05");
                roomMapList.add(roomDetails2);

                // Sample Data Entry 3
                HashMap<String, String> roomDetails3 = new HashMap<>();
                roomDetails3.put("checkInDate", "2024-03-01");
                roomDetails3.put("checkOutDate", "2024-03-05");
                roomMapList.add(roomDetails3);
                
                TableLayout roomTableLayout = (TableLayout) findViewById(R.id.room_table);
                for (final HashMap<String,String> map : roomMapList)
                {
                    TableRow row= new TableRow(getBaseContext());
                    TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                    row.setLayoutParams(lp);
                    TextView roomNumber = new TextView(getBaseContext());
                    roomNumber.setText(map.get("RoomNumber"));
                    final  String rnString = roomNumber.getText().toString();
                    TextView room_Type = new TextView(getBaseContext());
                    room_Type.setText(map.get("roomType"));
                    Button modifyButton = new Button(getBaseContext());
                    modifyButton.setText("modify");
                    row.addView(roomNumber);
                    row.addView(room_Type);
                    row.addView(modifyButton);
                    roomTableLayout.addView(row);

                    // set some properties of rowTextView or something

                    modifyButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            Intent intent = new
                                    Intent(AvailableRoomActivity.this, ModifyRoomActivity.class);
                            intent.putExtra("roomNumber",rnString);
                            intent.putExtra("roomPrice",map.get("room_price"));
                            intent.putExtra("roomType",map.get("room_type"));
                            startActivity(intent);
                        }
                    });

                }





                //startActivity(roomintent);
            }
        });
    }
}