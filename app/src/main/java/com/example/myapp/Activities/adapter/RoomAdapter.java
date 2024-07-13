package com.example.myapp.Activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapp.Activities.ui.managerhome.EditRoomActivity;
import com.example.myapp.Activities.ui.managerhome.ManageRoom;
import com.example.myapp.R;
import com.example.myapp.Activities.entities.Room;

import java.io.Serializable;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> roomList;
    private Context context;

    public RoomAdapter(List<Room> roomList, Context context) {
        this.roomList = roomList;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_item, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.roomNumber.setText(room.getRoomNumber());
        holder.roomType.setText(room.getRoomType());
        holder.roomPrice.setText(room.getPricePerNight());

        holder.editButton.setOnClickListener(v -> {
            // Handle edit button click
            Toast.makeText(context, "Edit " + room.getRoomNumber(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, EditRoomActivity.class);
            intent.putExtra("room", (Serializable) room);
            intent.putExtra("position", position);
            ((ManageRoom) context).startActivityForResult(intent,position);
        });

        holder.deleteButton.setOnClickListener(v -> {
            // Handle delete button click
            roomList.remove(position);
            notifyItemRemoved(position);
            Toast.makeText(context, "Deleted " + room.getRoomNumber(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView roomNumber, roomType, roomPrice;
        Button editButton, deleteButton;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomNumber = itemView.findViewById(R.id.room_number);
            roomType = itemView.findViewById(R.id.room_type);
            roomPrice = itemView.findViewById(R.id.room_price);
            editButton = itemView.findViewById(R.id.edit_button);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}


