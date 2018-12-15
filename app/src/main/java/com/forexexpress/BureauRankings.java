package com.forexexpress;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class BureauRankings extends AppCompatActivity {

    private Spinner spinner1, spinner2;
    private Button nNext;

    String SpinnerValue, SpinnerValue2;

    public static String selectedItemInSpinner=null;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bureau_rankings);
        spinner1 = (Spinner) findViewById(R.id._spinner1);
        spinner2 = (Spinner) findViewById(R.id._spinner2);
        nNext = (Button) findViewById(R.id._bureauRankings);

        spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue = (String)spinner1.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue2 = (String)spinner2.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        nNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SpinnerValue.equals("Buying") && SpinnerValue2.equals("USD")){
                    selectedItemInSpinner="USD_buying";
                    intent = new Intent(BureauRankings.this, BuyingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Buying") && SpinnerValue2.equals("Euro")){
                    selectedItemInSpinner="Euro_buying";
                    intent = new Intent(BureauRankings.this, BuyingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Buying") && SpinnerValue2.equals("GB Pound")){
                    selectedItemInSpinner="GB Pound_buying";
                    intent = new Intent(BureauRankings.this, BuyingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Buying") && SpinnerValue2.equals("KSHS")){
                    selectedItemInSpinner="KSHS_buying";
                    intent = new Intent(BureauRankings.this, BuyingActivity.class);
                    startActivity(intent);

                }

                else if(SpinnerValue.equals("Buying") && SpinnerValue2.equals("TSHS")){
                    selectedItemInSpinner="TSHS_buying";
                    intent = new Intent(BureauRankings.this, BuyingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Buying") && SpinnerValue2.equals("RF")){
                    selectedItemInSpinner="RF_buying";
                    intent = new Intent(BureauRankings.this, BuyingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Selling") && SpinnerValue2.equals("USD")){
                    selectedItemInSpinner="USD_selling";
                    intent = new Intent(BureauRankings.this, SellingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Selling") && SpinnerValue2.equals("Euro")){
                    selectedItemInSpinner="Euro_selling";
                    intent = new Intent(BureauRankings.this, SellingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Selling") && SpinnerValue2.equals("GB Pound")){
                    selectedItemInSpinner="GB Pound_selling";
                    intent = new Intent(BureauRankings.this, SellingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Selling") && SpinnerValue2.equals("KSHS")){
                    selectedItemInSpinner="KSHS_selling";
                    intent = new Intent(BureauRankings.this, SellingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Selling") && SpinnerValue2.equals("UGX")){
                    selectedItemInSpinner="UGX_selling";
                    intent = new Intent(BureauRankings.this, SellingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Selling") && SpinnerValue2.equals("TSHS")){
                    selectedItemInSpinner="TSHS_selling";
                    intent = new Intent(BureauRankings.this, SellingActivity.class);
                    startActivity(intent);

                }
                else if(SpinnerValue.equals("Selling") && SpinnerValue2.equals("RF")){
                    selectedItemInSpinner="RF_selling";
                    intent = new Intent(BureauRankings.this, SellingActivity.class);
                    startActivity(intent);


                }
                else{
                    Toast.makeText(BureauRankings.this, "Please Select all options before proceeding", Toast.LENGTH_SHORT).show();


                }



            }
        });




    }
}

