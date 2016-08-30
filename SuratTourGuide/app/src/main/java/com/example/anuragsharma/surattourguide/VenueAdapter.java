package com.example.anuragsharma.surattourguide;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 08/08/16.
 */
public class VenueAdapter extends ArrayAdapter<Venue> {

    private int mColorResourceId;

    public VenueAdapter(Context context, ArrayList<Venue> venues, int colorResourceId){
        super(context, 0, venues);
        mColorResourceId = colorResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Venue v = getItem(position);
        TextView location = (TextView) listItemView.findViewById(R.id.location);
        location.setText(v.getLocation());

        TextView address = (TextView) listItemView.findViewById(R.id.address);
        address.setText(v.getAddress());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.icon);
        if(v.hasImage())
        {
            imageView.setImageResource(v.getResourceId());
            imageView.setVisibility(View.VISIBLE);
        }
        else{
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.textview_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
