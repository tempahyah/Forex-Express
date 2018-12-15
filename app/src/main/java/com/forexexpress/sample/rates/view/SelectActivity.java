package com.forexexpress.sample.rates.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.forexexpress.BureauRankings;
import com.google.firebase.database.FirebaseDatabase;
import com.forexexpress.R;
import com.forexexpress.sample.rates.model.loader.CONSTANTS;

import java.util.ArrayList;
import java.util.List;

public class SelectActivity extends AppCompatActivity implements View.OnClickListener {

    Spinner spn_currencies, spn_exchange_type;
    String SpinnerValue, SpinnerValue2;
    List<String> currencies, exchange_types;
    ArrayAdapter<String> currencies_adapter, exchange_types_adapter;
    Button bt_proceed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalFirebaseSetup();
        setContentView(R.layout.activity_select);
        setUpSpinners();
        setUpButton();
    }

    private void setUpSpinners() {
        spn_currencies = (Spinner) findViewById(R.id.spn_currencies);
        spn_exchange_type = (Spinner) findViewById(R.id.spn_exchange_type);

        currencies = new ArrayList<>();
        currencies.add(CONSTANTS.DOLLAR);
        currencies.add(CONSTANTS.POUND);
        currencies.add(CONSTANTS.EURO);
        currencies.add(CONSTANTS.KSHS);
        currencies.add(CONSTANTS.TSHS);
        currencies.add(CONSTANTS.RF);


        currencies_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        spn_currencies.setAdapter(currencies_adapter);

        exchange_types = new ArrayList<>();
        exchange_types.add(CONSTANTS.BUYING);
        exchange_types.add(CONSTANTS.SELLING);

        exchange_types_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exchange_types);
        spn_exchange_type.setAdapter(exchange_types_adapter);
    }

    private void setUpButton(){
        bt_proceed = (Button) findViewById(R.id.bt_proceed);
        bt_proceed.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(getBundleToSend());
            startActivity(intent);

    }

    private Bundle getBundleToSend(){
        Bundle bundle = new Bundle();
        bundle.putString(CONSTANTS.CURRENCY,
                spn_currencies.getItemAtPosition(
                        spn_currencies.getSelectedItemPosition()
                ).toString());
        bundle.putString(CONSTANTS.EXCHANGE_TYPE,
                spn_exchange_type.getItemAtPosition(
                        spn_exchange_type.getSelectedItemPosition()
                ).toString());
        return bundle;
    }


    private void globalFirebaseSetup() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
