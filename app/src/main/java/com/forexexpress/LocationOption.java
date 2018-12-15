package com.forexexpress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LocationOption extends AppCompatActivity {

        Button contact, directions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_option);

        contact = (Button) findViewById(R.id.address);
        directions = (Button) findViewById(R.id.directions);

        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ContactInfo.class);
                startActivityForResult(myIntent, 0);
            }
        });

        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), MapsActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }

}
