package com.example.anuragsharma.surattourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by anuragsharma on 08/08/16.
 */
public class CategoryRestaurants extends AppCompatActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_content);

        ArrayList<Venue> venueList = new ArrayList<>();
        venueList.add(new Venue(getString(R.string.first_restaurant_name),getString(R.string.first_restaurant_address),R.drawable.restaurants));
        venueList.add(new Venue(getString(R.string.second_restaurant_name),getString(R.string.second_restaurant_address),R.drawable.restaurants));
        venueList.add(new Venue(getString(R.string.third_restaurant_name),getString(R.string.third_restaurant_address),R.drawable.restaurants));
        venueList.add(new Venue(getString(R.string.fourth_restaurant_name),getString(R.string.fourth_restaurant_address),R.drawable.restaurants));
        venueList.add(new Venue(getString(R.string.fifth_restaurant_name),getString(R.string.fifth_restaurant_address),R.drawable.restaurants));

        VenueAdapter adapter = new VenueAdapter(this,venueList,R.color.blue);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}
