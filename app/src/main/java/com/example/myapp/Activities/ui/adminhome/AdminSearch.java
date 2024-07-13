package com.example.myapp.Activities.ui.adminhome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.adapter.MyAdapter;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.database.SQLiteHelper;
import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.R;

import java.util.ArrayList;

public class AdminSearch extends AppCompatActivity {

    Button search, logout;
    EditText lastName;
    ListView lv_customerList;
    ArrayList<Profile> arrayList;
    MyAdapter myAdapter;
    private SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_search);

        dbHelper = new SQLiteHelper(this);
        lastName = findViewById(R.id.admin_lastname);
        search = findViewById(R.id.admin_search);
        logout = findViewById(R.id.searchAdminLogout);
        lv_customerList = findViewById(R.id.admin_list);
        arrayList = new ArrayList<>();
        myAdapter = new MyAdapter(this, arrayList);
        lv_customerList.setAdapter(myAdapter);

        logout.setOnClickListener(v -> startActivity(new Intent(AdminSearch.this, MainActivity.class)));

        search.setOnClickListener(v -> {
            String lastNameText = lastName.getText().toString().trim();
            if (!lastNameText.isEmpty()) {
                arrayList.clear();
                arrayList.addAll(searchByLastName(lastNameText));
                if (arrayList.isEmpty()) {
                    // Show toast message if no results found
                    Toast.makeText(AdminSearch.this, "No profiles found with the last name: " + lastNameText, Toast.LENGTH_LONG).show();
                }
                myAdapter.notifyDataSetChanged();
            }else{
                Toast.makeText(AdminSearch.this, "Please enter a last name to search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("Range")
    private ArrayList<Profile> searchByLastName(String lastName) {
        ArrayList<Profile> profiles = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM system_user WHERE lastName LIKE ?", new String[]{"%" + lastName + "%"});
        if (cursor.moveToFirst()) {
            do {
                Profile profile = new Profile();
                profile.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                profile.setFirstName(cursor.getString(cursor.getColumnIndex("firstName")));
                profile.setLastName(cursor.getString(cursor.getColumnIndex("lastName")));
                profile.setRole(cursor.getString(cursor.getColumnIndex("Role")));
                profiles.add(profile);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return profiles;
    }
}
