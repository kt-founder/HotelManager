package com.example.myapp.Activities.ui.managerhome;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapp.Activities.MainActivity;
import com.example.myapp.Activities.database.DBContext;
import com.example.myapp.Activities.entities.Room;
import com.example.myapp.Activities.ui.adminhome.AdminHomeActivity;
import com.example.myapp.Activities.ui.usershome.ViewRoom;
import com.example.myapp.R;

import java.util.List;

public class AvailableRoomActivity extends AppCompatActivity {
    private DBContext dbContext;
    private TableLayout tableLayout;
    private Spinner spinner;
    String type;
    private Button homeButton, logoutButton, searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_room);

        dbContext = new DBContext(this);
        dbContext.open();
        spinner = findViewById(R.id.room_typ);
        tableLayout = findViewById(R.id.room_table);
        homeButton = findViewById(R.id.button8);
        logoutButton = findViewById(R.id.availLogout);
        searchButton = findViewById(R.id.avl_room_search_btn);  // Make sure you have this button in your layout

        type = spinner.getSelectedItem().toString().trim();
        // Setup buttons
        homeButton.setOnClickListener(v -> startActivity(new Intent(this, ManagerHomeActivity.class)));
        logoutButton.setOnClickListener(v -> startActivity(new Intent(this, MainActivity.class)));
        searchButton.setOnClickListener(v -> {
            type = spinner.getSelectedItem().toString().trim();
            tableLayout.removeAllViews();  // Clear the table before repopulating
            populateTable();
        });

        // Initially populate the table with available rooms
        populateTable();
    }

    private void setupButtons() {

    }

    private void populateTable() {
        List<Room> availableRooms = dbContext.getAvailableRooms(type); // Implement this method in DBContext
        if (availableRooms != null && !availableRooms.isEmpty()) {
            for (Room room : availableRooms) {
                TableRow row = new TableRow(this);
                row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvRoom = new TextView(this);
                tvRoom.setText(room.getRoomNumber());
                tvRoom.setGravity(View.TEXT_ALIGNMENT_CENTER);

                TextView tvType = new TextView(this);
                tvType.setText(room.getRoomType());
                tvType.setGravity(View.TEXT_ALIGNMENT_CENTER);

                Button btnModify = new Button(this);
                btnModify.setText("Modify");
                btnModify.setOnClickListener(v -> {

                    // Handle Modify Click
                    // Intent or dialog to modify room details
                    Intent intent = new Intent(AvailableRoomActivity.this, ModifyRoomActivity.class);
                    intent.putExtra("roomNumber",room.getRoomNumber());
                    startActivity(intent);

                });

                row.addView(tvRoom);
                row.addView(tvType);
                row.addView(btnModify);

                tableLayout.addView(row);
            }
        } else {
            // Handle case when no available rooms are found
            TableRow row = new TableRow(this);
            TextView tvEmpty = new TextView(this);
            tvEmpty.setText("No available rooms found.");
            row.addView(tvEmpty);
            tableLayout.addView(row);
        }
    }
}
