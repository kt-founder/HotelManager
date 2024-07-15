package com.example.myapp.Activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapp.Activities.entities.Reservation;
import com.example.myapp.Activities.ui.usershome.Payment;

import com.example.myapp.R;

import java.util.ArrayList;

public class PendingAdapter extends ArrayAdapter<Reservation> {
    private Context context;
    private ArrayList<Reservation> reservations;

    public PendingAdapter(Context context, ArrayList<Reservation> reservations) {
        super(context, 0, reservations);
        this.context = context;
        this.reservations = reservations;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.pending_reservation_item, parent, false);
        }

        TextView tvRoomNumber = convertView.findViewById(R.id.tvRoomNumber);
        TextView tvHotelName = convertView.findViewById(R.id.tvHotelName);
        TextView tvRoomType = convertView.findViewById(R.id.tvRoomType);
        TextView tvDates = convertView.findViewById(R.id.tvDates);
        Button btnPay = convertView.findViewById(R.id.btnPay);

        Reservation reservation = getItem(position);

        if (reservation != null) {
            tvRoomNumber.setText(reservation.getRoomNumber());
            tvHotelName.setText(reservation.getHotel_name());
            tvRoomType.setText(reservation.getRoom_type());
            String dates = "Check-in: " + reservation.getCheck_in_date() + " | Check-out: " + reservation.getCheck_out_date();
            tvDates.setText(dates);

            btnPay.setOnClickListener(v -> {
                Intent intent = new Intent(context, Payment.class);
                intent.putExtra("booking_id", reservation.getBooking_id());  // Assuming Reservation has a getBookingId method
                context.startActivity(intent);
            });
        }

        return convertView;
    }
}
