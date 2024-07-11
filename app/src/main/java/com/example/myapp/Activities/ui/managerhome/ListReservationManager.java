package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.R;

import java.util.ArrayList;

public class ListReservationManager extends AppCompatActivity {
  //  DBManager DBManager;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    Button ButtonLogOut, ButtonHome;


    Intent myIntent;

    ArrayList<String> ID_Array;
    ArrayList<String> Reservation_Date_Array;
    ArrayList<String> Room_Type_Array;
    ArrayList<String> Number_of_Room_Array;
    ArrayList<String> CheckIn_Array;
    ArrayList<String> CheckOut_Array;
    ArrayList<String> First_Name_Array;
    ArrayList<String> Last_Name_Array;
    ArrayList<String> Number_Of_Adults_Array;
    ArrayList<String> Number_Of_Children_Array;
    ArrayList<String> Total_Price_Array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_reservation_manager);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ButtonLogOut= (Button)findViewById(R.id.button_logout);
        ButtonHome = (Button) findViewById(R.id.button_home);


        ID_Array = new ArrayList<String>();
        Reservation_Date_Array = new ArrayList<String>();
        Room_Type_Array = new ArrayList<String>();
        Number_of_Room_Array = new ArrayList<String>();
        CheckIn_Array = new ArrayList<String>();
        CheckOut_Array = new ArrayList<String>();
        First_Name_Array = new ArrayList<String>();
        Last_Name_Array = new ArrayList<String>();
        Number_Of_Adults_Array = new ArrayList<String>();
        Number_Of_Children_Array = new ArrayList<String>();
        Total_Price_Array = new ArrayList<String>();


       // DBManager = new DBManager(this);

        myIntent = new Intent(this, DetailReservationActivity.class);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);

        LISTVIEW.setAdapter(listAdapter);

        ButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListReservationManager.this, MainActivity.class));
            }
        });



        ButtonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListReservationManager.this, ManagerHomeActivity.class));
            }
        });

    }
}