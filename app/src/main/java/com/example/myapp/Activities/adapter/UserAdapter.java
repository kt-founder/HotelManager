package com.example.myapp.Activities.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapp.Activities.entities.Profile;
import com.example.myapp.R;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<Profile> {
    public UserAdapter(Context context, ArrayList<Profile> profiles) {
        super(context, 0, profiles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Profile profile = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_expandable_list_item_1, parent, false);
        }
        TextView tvName = convertView.findViewById(android.R.id.text1);
        TextView tvDetail = convertView.findViewById(android.R.id.text2);
        // Populate the data into the template view using the profile object
        tvName.setText(profile.getLastName());
        tvDetail.setText(profile.getFirstName());  // Example usage
        return convertView;
    }
}

