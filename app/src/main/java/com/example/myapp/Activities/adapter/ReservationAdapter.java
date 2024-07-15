package com.example.myapp.Activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.R;

import java.util.List;

public class ReservationAdapter extends ArrayAdapter<Reservation> {

    public ReservationAdapter(Context context, List<Reservation> reservations) {
        super(context, 0, reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reservation_item, parent, false);
        }

        // Get the data item for this position
        Reservation reservation = getItem(position);

        // Lookup view for data population
        TextView tvHotelName = (TextView) convertView.findViewById(R.id.hotel_name);
        TextView tvRoomNumber = (TextView) convertView.findViewById(R.id.room_number);
        TextView tvStatus = (TextView) convertView.findViewById(R.id.status);

        // Populate the data into the template view using the data object
        tvHotelName.setText(reservation.getHotel_name());
        tvRoomNumber.setText(reservation.getNumber_of_rooms());
        tvStatus.setText(reservation.getStatus());

        // Return the completed view to render on screen
        return convertView;
    }
}
