package com.example.anuragsharma.surattourguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonClick(View view){
        Intent i;
        switch (view.getId()){
            case R.id.button_places:
                i = new Intent(this,CategoryPlaces.class);
                startActivity(i);
                break;
            case R.id.button_temples:
                i = new Intent(this,CategoryTemple.class);
                startActivity(i);
                break;
            case R.id.button_restaurants:
                i = new Intent(this,CategoryRestaurants.class);
                startActivity(i);
                break;
            case R.id.button_parks:
                i = new Intent(this,CategoryParks.class);
                startActivity(i);
                break;
        }
    }
}
