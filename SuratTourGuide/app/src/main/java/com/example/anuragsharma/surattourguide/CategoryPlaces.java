package com.example.anuragsharma.surattourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 08/08/16.
 */
public class CategoryPlaces extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_content);

        ArrayList<Venue> venueList = new ArrayList<>();
        venueList.add(new Venue(getString(R.string.first_place_name),getString(R.string.first_place_address),R.drawable.beach));
        venueList.add(new Venue(getString(R.string.second_place_name),getString(R.string.second_place_address),R.drawable.img));
        venueList.add(new Venue(getString(R.string.third_place_name),getString(R.string.third_place_address),R.drawable.beach));
        venueList.add(new Venue(getString(R.string.fourth_place_name),getString(R.string.fourth_place_address),R.drawable.beach));
        venueList.add(new Venue(getString(R.string.fifth_place_name),getString(R.string.fifth_place_address),R.drawable.place));
        venueList.add(new Venue(getString(R.string.sixth_place_name),getString(R.string.sixth_place_address),R.drawable.castle));
        venueList.add(new Venue(getString(R.string.seventh_place_name),getString(R.string.seventh_place_address),R.drawable.beach));
        venueList.add(new Venue(getString(R.string.eighth_place_name),getString(R.string.eighth_place_address),R.drawable.img));

        VenueAdapter adapter = new VenueAdapter(this,venueList,R.color.red);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
