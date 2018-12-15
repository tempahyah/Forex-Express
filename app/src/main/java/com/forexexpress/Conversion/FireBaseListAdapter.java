package com.forexexpress.Conversion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.forexexpress.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FireBaseListAdapter extends AppCompatActivity {
        private ListView mListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base_list_adapter);

        mListview =(ListView) findViewById(R.id.fire_list);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Bureaus").child("Shumuk Forex Bureau").child("Buying");


        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                this,
                String.class,
                R.layout.firebase_layout,
                databaseReference

        ) {
            @Override
            protected void populateView(View v, String model, int position) {

                TextView bureau = (TextView) v.findViewById(R.id.bureau_tv);
                TextView currency = (TextView) v.findViewById(R.id.currency_tv);
                TextView buying_tv = (TextView) v.findViewById(R.id.buying_tv);
                TextView selling_tv = (TextView) v.findViewById(R.id.selling_tv);

                String currency_name = (this.getRef(position).getKey());
                currency.setText(currency_name);








            }
        };

        mListview.setAdapter(firebaseListAdapter);

        }
    }

