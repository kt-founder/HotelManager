package com.example.myapp.Activities.ui.usershome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.adapter.SearchRoomAdapter;
import com.example.myapp.Activities.entities.Hotel;
import com.example.myapp.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchRoomActivity extends AppCompatActivity {
    Button home,logout,search;
    Button homer,logoutr;
    EditText nadults,nchild,cidate,codate;
    Spinner Hloc, Hroom, HNroom;

    ListView room_listView;
    SearchRoomAdapter searchRoomAdapter;
    ArrayList<Hotel> arrayList;
    SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_room);

        home = findViewById(R.id.userHome);
        logout = findViewById(R.id.logout);
        search = findViewById(R.id.search_r);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchRoomActivity.this,userHomeActivity.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchRoomActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Hotel Room Type is " + Hroom.getSelectedItem().toString());

                String checkinDate = cidate.getText().toString();
                String checkOutDate = codate.getText().toString();

                String[] arr1 = checkinDate.split("/");
                String[] arr = checkOutDate.split("/");

                String tomYear = arr[2];
                String tomDay = arr[1];
                String tomMonth = arr[0];

                String toYear = arr1[2];
                String toDay = arr1[1];
                String toMonth = arr1[0];
                int numOfNights = 0;

                if(Integer.parseInt(tomYear) >= Integer.parseInt(toYear))
                {
                    if(Integer.parseInt(toMonth) == Integer.parseInt(tomMonth) )
                    {
                        numOfNights = (Integer.parseInt(tomDay)) - (Integer.parseInt(toDay));
                    }
                }

                System.out.println(numOfNights);

//                sharedpreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
////
//                SharedPreferences.Editor session = sharedpreferences.edit();
//                session.putString(KEY_ADULT, nadults.getText().toString());
//                session.putString(KEY_CHILDREN,nchild.getText().toString());
//                session.putString(KEY_CHECKINDATE,cidate.getText().toString());
//                session.putString(KEY_CHECKOUTDATE,codate.getText().toString());
//                session.putString(KEY_NUMBEROFNIGHTS, String.valueOf(numOfNights));
//                session.putString(KEY_NUMBEROFROOMS,HNroom.getSelectedItem().toString());
//                session.apply();


//                String result = "";
//                String arr[] = cidate.getText().toString().split("/");
//                String year = arr[2];
//                String day = arr[1];
//                String month = arr[0];
//
//                Date dnow = new Date()

//                if(numOfNights < 1)
//                {
//                    Toast.makeText(searchRoomScreen.this, "Check out cannot be same as check in", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//
//                    setContentView(R.layout.room_list);
////
////
////
//                    room_listView = findViewById(R.id.room_listView);
//
//                    DBManager dbManager = new DBManager(searchRoomScreen.this);
//                    arrayList = dbManager.getRoomDetails(Hroom.getSelectedItem().toString(),numOfNights);
                    arrayList.add(new Hotel("âa","aa","aa","aa","âa"));
                    searchRoomAdapter = new SearchRoomAdapter(SearchRoomActivity.this,arrayList);

                    room_listView.setAdapter(searchRoomAdapter);
                    searchRoomAdapter.notifyDataSetChanged();
//
//                }




            }
        });

    }
}