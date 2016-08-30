package com.example.anuragsharma.surattourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 08/08/16.
 */
public class CategoryParks extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_content);

        ArrayList<Venue> venueList = new ArrayList<>();
        venueList.add(new Venue(getString(R.string.first_park_name),getString(R.string.first_park_address),R.drawable.place));
        venueList.add(new Venue(getString(R.string.second_park_name),getString(R.string.second_park_address),R.drawable.bio));
        venueList.add(new Venue(getString(R.string.third_park_name),getString(R.string.third_park_address),R.drawable.bio));
        venueList.add(new Venue(getString(R.string.fourth_park_name),getString(R.string.fourth_park_address),R.drawable.bio));

        VenueAdapter adapter = new VenueAdapter(this,venueList,R.color.purple);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
