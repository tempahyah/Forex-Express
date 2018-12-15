package com.forexexpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.forexexpress.Conversion.ConvertConstants;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Convert extends AppCompatActivity implements View.OnClickListener {
    public Spinner spinner1, spinner2, spinner3;
    public String SpinnerValue1, SpinnerValue2, SpinnerValue3;
    public Button button;
    public TextView manipulatedvalue, nDisplayFrom,nDisplayTo ;
    EditText amount_entered, amount_converted;
    private double nValue1, nValue2;
    DatabaseReference nRef1, nRef2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);
        amount_entered= (EditText) findViewById(R.id.amount_to_convert);
        amount_converted = (EditText) findViewById(R.id.amount_Converted);
        manipulatedvalue = (TextView) findViewById(R.id.manipulatedValue);
        nDisplayFrom = (TextView) findViewById(R.id.displayFrom);
        nDisplayTo = (TextView) findViewById(R.id.displayTo);
        addListenerOnSpinnerItemSelection();
        addListenerOnButton();


    }
    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner3 = (Spinner) findViewById(R.id.spinner3);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue1 = (String)spinner1.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue2 = (String)spinner2.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SpinnerValue3 = (String)spinner3.getSelectedItem();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
    public void addListenerOnButton() {

        button = (Button) findViewById(R.id.ConvertButtoN);
        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(amount_entered.length()==0){
            Toast.makeText(Convert.this, "Please Enter the Amount you want to Change", Toast.LENGTH_SHORT).show();
            return;
        }
        if(SpinnerValue2.equals(SpinnerValue3)){
            Toast.makeText(Convert.this, "The Currencies must be different", Toast.LENGTH_SHORT).show();
            return;
        }

        //Metropolitan Forex Bureau Calculations

        //USD
        if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/usd");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }


        // Euro
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //GB_Pound
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.UGX)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.KSHS)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.RF)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //UGX
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue2 = ((1/value) * value2);
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue2 = ((1/value) * value2);
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue2 = ((1/value) * value2);
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue2 = ((1/value) * value2);
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue2 = ((1/value) * value2);
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue2 = ((1/value) * value2);
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        //KSHS
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.TSHS)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        //TSHS

        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }

        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //RF
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }

        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.metropolitan) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        //END OF METROPOLITAN


        //Jetset Forex Bureau Calculations

        //USD
        if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }


        // Euro
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //GB_Pound
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.RF)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //UGX
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }

        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }

        //KSHS
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.RF)) {
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1" + SpinnerValue2 + " = " + value + " " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2 = FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1 / value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){
                nDisplayFrom.setText(SpinnerValue2);
                nDisplayTo.setText(SpinnerValue3);
                nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/KSHS");
                nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                        double value = dataSnapshot.getValue(double.class);
                        double value2 = Double.valueOf(amount_entered.getText().toString());
                        nValue1 = value * value2;
                        manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                        amount_converted.setText(String.format("%.2f",nValue1)+"");

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



        }

        //TSHS

        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
                    nDisplayFrom.setText(SpinnerValue2);
                    nDisplayTo.setText(SpinnerValue3);
                    nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/TSHS");
                    nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                            double value = dataSnapshot.getValue(double.class);
                            double value2 = Double.valueOf(amount_entered.getText().toString());
                            nValue1 = value * value2;
                            manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/USD");
                    nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                        @Override
                        public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                            double value = dataSnapshot.getValue(double.class);
                            nValue2 = ((1/value) * nValue1);

                            amount_converted.setText(String.format("%.2f",nValue2)+"");
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.RF)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //RF
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.UGX)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.jetset) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.TSHS)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
        //END OF JETSET FOREX BUREAU



        //Shumuk Forex Bureau Calculations

        //USD
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.UGX)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.RF)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }


        // Euro
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        //GB_Pound
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.GB_Pound)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //UGX
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

        //KSHS
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



        }

        //TSHS

        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //RF
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });



        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/RF");

        }

        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.EURO)){

            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.shumuk) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        //END OF SHUMUK FOREX BUREAU


        //Prime Forex Bureau Rates

        //USD
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });
        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.DOLLAR)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //Euro
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.EURO)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }

        //UGX
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/USD");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/Euro");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.UGX)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = (1/value) * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue3+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }


        //KSHS
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Bureaus/Prime Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.KSHS)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Bureaus/Prime Forex Bureau Ltd/Selling/KSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Bureaus/Prime Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }


        //TSHS

        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.TSHS)&& SpinnerValue3.equals(ConvertConstants.RF)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/TSHS");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/RF");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }

        //RF

        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.DOLLAR)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/USD");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.EURO)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/Euro");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.GB_Pound)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.UGX)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);
                    amount_converted.setText(String.format("%.2f",nValue1)+"");

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.KSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/KSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        else if(SpinnerValue1.equals(ConvertConstants.prime) && SpinnerValue2.equals(ConvertConstants.RF)&& SpinnerValue3.equals(ConvertConstants.TSHS)){
            nDisplayFrom.setText(SpinnerValue2);
            nDisplayTo.setText(SpinnerValue3);
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/RF");
            nRef1.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    double value2 = Double.valueOf(amount_entered.getText().toString());
                    nValue1 = value * value2;
                    manipulatedvalue.setText("Rate is 1"+SpinnerValue2+" = " + value +" " + ConvertConstants.UGX);


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/TSHS");
            nRef2.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue2 = ((1/value) * nValue1);

                    amount_converted.setText(String.format("%.2f",nValue2)+"");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        }
        //END OF PRIME FOREX BUREAU CALCULATIONS

    }
}


