package com.forexexpress;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.forexexpress.Conversion.ConvertConstants;
import com.forexexpress.Conversion.FirebaseRtDB;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class BuyingActivity extends AppCompatActivity implements View.OnClickListener {

    TextView bureau1, bureau2, bureau3, bureau4, displayText,
            rate_1,  rate_2,  rate_3,  rate_4;

    double nValue1, nValue2, nValue3, nValue4;
    DatabaseReference nRef1, nRef2, nRef3, nRef4;

    public Button rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buying);
        FirebaseRtDB firebaseRtDB = new FirebaseRtDB();
        displayText=(TextView) findViewById(R.id.displayBuying);
        bureau1 = (TextView) findViewById(R.id.bureau1Buying);
        bureau2 = (TextView) findViewById(R.id.bureau2Buying);
        bureau3 = (TextView) findViewById(R.id.bureau3Buying);
        bureau4 = (TextView) findViewById(R.id.bureau4Buying);

        rate_1 = (TextView) findViewById(R.id.rate_1Buying);
        rate_2 = (TextView) findViewById(R.id.rate_2Buying);
        rate_3 = (TextView) findViewById(R.id.rate_3Buying);
        rate_4 = (TextView) findViewById(R.id.rate_4Buying);
        rank = (Button) findViewById(R.id.buyingUpdate);

        rank.setOnClickListener(this);

        display();
    }

    public void display() {
        if (BureauRankings.selectedItemInSpinner.equals("USD_buying")){
            displayText.setText("DOLLAR BUYING RATES RANKED");
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/USD");
            nRef1.keepSynced(true);
            nRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue1 = value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/USD");
            nRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue2 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef3= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/USD");
            nRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue3 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef4= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/USD");
            nRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue4 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //nValue1 constant = 1
            if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            //vary nValue2 = 1

            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varrying nValue3 = 1
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varying nValue 4 = 1
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2<=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2>=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else{

            }

        }
        if (BureauRankings.selectedItemInSpinner.equals("Euro_buying")){
            displayText.setText("EURO BUYING RATES RANKED");
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/Euro");
            nRef1.keepSynced(true);
            nRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);
                    nValue1 = value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/Euro");
            nRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue2 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef3= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/Euro");
            nRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue3 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef4= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/Euro");
            nRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue4 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //nValue1 constant = 1
            if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            //vary nValue2 = 1

            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varrying nValue3 = 1
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varying nValue 4 = 1
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2<=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2>=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else{

            }

        }
        if (BureauRankings.selectedItemInSpinner.equals("GB Pound_buying")){
            displayText.setText("POUND STERLING BUYING RATES RANKED");
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/GB Pound");
            nRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue1 = value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/GB Pound");
            nRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue2 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef3= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/GB Pound");
            nRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue3 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef4= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
            nRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue4 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //nValue1 constant = 1
            if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            //vary nValue2 = 1

            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varrying nValue3 = 1
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varying nValue 4 = 1
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2<=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2>=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else{

            }

        }
        if (BureauRankings.selectedItemInSpinner.equals("KSHS_buying")){
            displayText.setText("KENYA SHILLINGS BUYING RATES RANKED");
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/KSHS");
            nRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue1 = value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/KSHS");
            nRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue2 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef3= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/KSHS");
            nRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue3 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef4= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/KSHS");
            nRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue4 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //nValue1 constant = 1
            if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            //vary nValue2 = 1

            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varrying nValue3 = 1
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varying nValue 4 = 1
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2<=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2>=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else{

            }

        }
        if (BureauRankings.selectedItemInSpinner.equals("TSHS_buying")){
            displayText.setText("TANZANIA SHILLINGS BUYING RATES RANKED");
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/TSHS");
            nRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue1 = value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/TSHS");
            nRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue2 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef3= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/TSHS");
            nRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue3 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef4= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/TSHS");
            nRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue4 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //nValue1 constant = 1
            if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            //vary nValue2 = 1

            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varrying nValue3 = 1
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varying nValue 4 = 1
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2<=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2>=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else{

            }

        }
        if (BureauRankings.selectedItemInSpinner.equals("RF_buying")){
            displayText.setText("RWANDAN FRANC BUYING RATES RANKED");
            nRef1= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Metropolitan Forex Bureau Ltd/Buying/RF");
            nRef1.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue1 = value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef2= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Jetset Forex Bureau/Buying/RF");
            nRef2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue2 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef3= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Shumuk Forex Bureau/Buying/RF");
            nRef3.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue3 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            nRef4= FirebaseDatabase.getInstance().getReferenceFromUrl("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/RF");
            nRef4.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    double value = dataSnapshot.getValue(double.class);

                    nValue4 =  value;
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            //nValue1 constant = 1
            if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2>=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3>=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue2<=nValue3)&&(nValue2<=nValue4)&&(nValue3<=nValue4)){
                bureau1.setText(ConvertConstants.metropolitan);         rate_1.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            //vary nValue2 = 1

            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1>=nValue4)&&(nValue3<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1>=nValue4)&&(nValue3>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1>=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue2>=nValue1)&&(nValue2>=nValue3)&&(nValue2>=nValue4)&&(nValue1<=nValue3)&&(nValue1<=nValue4)&&(nValue3<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau1.setText(ConvertConstants.jetset);               rate_1.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varrying nValue3 = 1
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1>=nValue4)&&(nValue2<=nValue4)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1>=nValue4)&&(nValue2>=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau4.setText(ConvertConstants.prime);                rate_4.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1>=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2>=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau3.setText(ConvertConstants.prime);                rate_3.setText(nValue4+"");
            }
            else if((nValue3>=nValue1)&&(nValue3>=nValue2)&&(nValue3>=nValue4)&&(nValue1<=nValue2)&&(nValue1<=nValue4)&&(nValue2<=nValue4)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau1.setText(ConvertConstants.shumuk);               rate_1.setText(nValue3+"");
                bureau2.setText(ConvertConstants.prime);                rate_2.setText(nValue4+"");
            }

            //varying nValue 4 = 1
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1>=nValue3)&&(nValue2<=nValue3)){
                bureau2.setText(ConvertConstants.metropolitan);         rate_2.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1>=nValue3)&&(nValue2>=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau4.setText(ConvertConstants.shumuk);               rate_4.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1>=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau3.setText(ConvertConstants.metropolitan);         rate_3.setText(nValue1+"");
                bureau4.setText(ConvertConstants.jetset);               rate_4.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2>=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau2.setText(ConvertConstants.jetset);               rate_2.setText(nValue2+"");
                bureau3.setText(ConvertConstants.shumuk);               rate_3.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else if((nValue4>=nValue1)&&(nValue4>=nValue2)&&(nValue4>=nValue3)&&(nValue1<=nValue2)&&(nValue1<=nValue3)&&(nValue2<=nValue3)){
                bureau4.setText(ConvertConstants.metropolitan);         rate_4.setText(nValue1+"");
                bureau3.setText(ConvertConstants.jetset);               rate_3.setText(nValue2+"");
                bureau2.setText(ConvertConstants.shumuk);               rate_2.setText(nValue3+"");
                bureau1.setText(ConvertConstants.prime);                rate_1.setText(nValue4+"");
            }
            else{

            }

        }
    }
    @Override
    public void onClick(View v) {
        display();
    }

    @Override
    protected void onStart() {
        display();
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.buying_refresh, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== R.id.action_refresh){

                display();
        }
           return super.onOptionsItemSelected(item);
    }

}
