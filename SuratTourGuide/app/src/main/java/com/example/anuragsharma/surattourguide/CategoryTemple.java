package com.example.anuragsharma.surattourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 08/08/16.
 */
public class CategoryTemple extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_content);

        ArrayList<Venue> venueList = new ArrayList<>();
        venueList.add(new Venue(getString(R.string.first_temple_name),getString(R.string.first_temple_address),R.drawable.temple));
        venueList.add(new Venue(getString(R.string.second_temple_name),getString(R.string.second_temple_address),R.drawable.temple));
        venueList.add(new Venue(getString(R.string.third_temple_name),getString(R.string.third_temple_address),R.drawable.temple));
        venueList.add(new Venue(getString(R.string.fourth_temple_name),getString(R.string.fourth_temple_address),R.drawable.temple));
        venueList.add(new Venue(getString(R.string.fifth_temple_name),getString(R.string.fifth_temple_address),R.drawable.temple));

        VenueAdapter adapter = new VenueAdapter(this,venueList,R.color.green);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
