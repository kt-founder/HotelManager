package com.example.myapp.Activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.Activities.entities.Room;
import com.example.myapp.Activities.ui.usershome.ViewRoom;
import com.example.myapp.R;

import java.util.ArrayList;

public class SearchRoomAdapter extends ArrayAdapter<Room> {
    private Context context;
    private ArrayList<Room> rooms;

    public SearchRoomAdapter(Context context, ArrayList<Room> rooms) {
        super(context, 0, rooms);
        this.context = context;
        this.rooms = rooms;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.room_list_adapter, parent, false);
        }

        TextView hotelName = convertView.findViewById(R.id.hotel_name);
        TextView hotelLocation = convertView.findViewById(R.id.hotel_location);
        TextView roomType = convertView.findViewById(R.id.room_type);
        TextView pricePerNight = convertView.findViewById(R.id.price_per_night);
        Button viewButton = convertView.findViewById(R.id.view_room);

        final Room room = getItem(position);

        if (room != null) {
            hotelName.setText(room.getHotelName());
            hotelLocation.setText(room.getHotelLocation());
            roomType.setText(room.getRoomType());
            pricePerNight.setText(room.getPricePerNight());
        }

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewRoom.class);
                intent.putExtra("hotelName", room.getHotelName());
                intent.putExtra("hotelLocation", room.getHotelLocation());
                intent.putExtra("roomType", room.getRoomType());
                intent.putExtra("pricePerNight", room.getPricePerNight());
                intent.putExtra("numberOfBeds", room.getNumberOfBeds());
                intent.putExtra("roomFacilities", room.getRoomFacilities());
                intent.putExtra("checkInDate", room.getCheckInDate());
                intent.putExtra("checkOutDate", room.getCheckOutDate());
                intent.putExtra("numberOfNights", room.getNumberOfNights());
                intent.putExtra("totalPrice", room.getTotalPrice());
                intent.putExtra("roomNumber",room.getRoomNumber());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
