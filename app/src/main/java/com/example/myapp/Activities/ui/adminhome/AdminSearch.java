package com.example.myapp.Activities.ui.adminhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.adapter.MyAdapter;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.R;

import java.util.ArrayList;

public class AdminSearch extends AppCompatActivity {

    Button search,logout;
    EditText lastName;
    ListView lv_customerList;
    ArrayList<Profile> arrayList;
    MyAdapter myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_search);

        lastName = findViewById(R.id.admin_lastname);
        search = findViewById(R.id.admin_search);
        lv_customerList = findViewById(R.id.admin_list);

        logout = findViewById(R.id.searchAdminLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminSearch.this, MainActivity.class));
            }
        });
        // Search here

    }
}