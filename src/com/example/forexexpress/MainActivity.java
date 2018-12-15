package com.example.forexexpress;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button convertbutton = (Button) findViewById(R.id.ConvertButton);
        convertbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Convert.class);
                startActivityForResult(myIntent, 0);
            }
        });    
        Button locatebutton = (Button) findViewById(R.id.LocateBureauButton);
        locatebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), LocateBureau.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button bureauRankingsbutton = (Button) findViewById(R.id.BureauRankingsButton);
        bureauRankingsbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), BureauRankings.class);
                startActivityForResult(myIntent, 0);
            }
        }); 
        Button viewStatbutton = (Button) findViewById(R.id.ViewStatisticsButton);
        viewStatbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ViewStatistics.class);
                startActivityForResult(myIntent, 0);
            }
        });     
            }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void textView1(View v)
    {
        Intent i=new Intent();
        i.setClass(this,LogIn.class);
        startActivity(i);
    }

    public void textView2(View v)
    {
        Intent in=new Intent();
        in.setClass(this,Help.class);
        startActivity(in);
    }
    
}
