package com.example.myapp.Activities.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.Activities.ui.adminhome.AdminViewGuestActivity;
import com.example.myapp.R;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Profile> arrayList;


    public MyAdapter(Context context, ArrayList<Profile> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.custom_layout, parent, false);
        }

        EditText tvFirstName = convertView.findViewById(R.id.tvFirstName);
        EditText tvLastName = convertView.findViewById(R.id.tvLastName);
        EditText tvRole = convertView.findViewById(R.id.tvRole);
        EditText tvUsername = convertView.findViewById(R.id.tvUsername);
        Button btnView = convertView.findViewById(R.id.btnView);

        Profile profile = arrayList.get(position);
        tvFirstName.setText(profile.getFirstName());
        tvLastName.setText(profile.getLastName());
        tvRole.setText(profile.getRole());
        tvUsername.setText(profile.getUsername());

        btnView.setOnClickListener(v -> {

            Intent intent = new Intent(context, AdminViewGuestActivity.class);
            intent.putExtra("username",profile.getUsername());

            context.startActivity(intent);
        });

        return convertView;
    }
}
