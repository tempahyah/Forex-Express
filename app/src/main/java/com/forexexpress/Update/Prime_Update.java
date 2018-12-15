package com.forexexpress.Update;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.forexexpress.LoginActivity;
import com.forexexpress.MainActivity;
import com.forexexpress.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Prime_Update extends AppCompatActivity {
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth mAuth;

    NotificationCompat.Builder notification;
    private static final int uniqueID = 2345;

    private Button SubmitRates, LogoutBureau;
    private Firebase    nRef1, nRef2, nRef3, nRef4, nRef5,   nRef6,
            nRef7, nRef8, nRef9, nRef10, nRef11, nRef12;

    private ProgressDialog nProgressDialog;


    private EditText    usd_buying, usd_selling,
            euro_buying, euro_selling,
            gbPound_buying, gbPound_selling,
            kshs_buying, kshs_selling,
            tshs_buying, tshs_selling,
            rf_buying, rf_selling;
    private TextView logged_in_as;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime__update);

        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        Firebase.setAndroidContext(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        nProgressDialog = new ProgressDialog(this);
        logged_in_as =(TextView)findViewById(R.id.loggedInPrime);
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    finish();
                    startActivity(new Intent(Prime_Update.this, LoginActivity.class));

                }
                else{
                    FirebaseUser user = mAuth.getCurrentUser();
                    logged_in_as.setText("Logged in as "+user.getEmail());
                }
            }
        };
        LogoutBureau = (Button) findViewById(R.id.logoutPrimeBureau);
        usd_buying = (EditText) findViewById(R.id.usd_buying_prime); usd_selling = (EditText) findViewById(R.id.usd_selling_prime);
        euro_buying = (EditText) findViewById(R.id.euro_buying_prime); euro_selling = (EditText) findViewById(R.id.euro_selling_prime);
        gbPound_buying = (EditText) findViewById(R.id.gbPound_buying_prime); gbPound_selling = (EditText) findViewById(R.id.gbPound_selling_prime);
        kshs_buying = (EditText) findViewById(R.id.kshs_buying_prime); kshs_selling = (EditText) findViewById(R.id.kshs_selling_prime);
        tshs_buying = (EditText) findViewById(R.id.tshs_buying_prime); tshs_selling = (EditText) findViewById(R.id.tshs_selling_prime);
        rf_buying = (EditText) findViewById(R.id.rf_buying_prime); rf_selling = (EditText) findViewById(R.id.rf_selling_prime);

        LogoutBureau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                finish();
                startActivity(new Intent(Prime_Update.this, LoginActivity.class));
            }
        });
        SubmitRates = (Button) findViewById(R.id.submit_Rates_prime);

        SubmitRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(usd_buying.getText().length()<1 || usd_selling.getText().length()<1 || euro_buying.getText().length()<1 || euro_selling.getText().length()<1 ||
                        gbPound_buying.getText().length()<1 || gbPound_selling.getText().length()<1 || kshs_buying.getText().length()<1 || kshs_selling.getText().length()<1 ||
                        tshs_buying.getText().length()<1 || tshs_selling.getText().length()<1 || rf_buying.getText().length()<1 || rf_selling.getText().length()<1){

                    Toast.makeText(Prime_Update.this, "Please make sure all fields are filled in",Toast.LENGTH_SHORT).show();

                    return;

                }
                nRef1 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/USD");
                double usdbuy = Double.parseDouble(usd_buying.getText().toString());
                nRef1.setValue(usdbuy);

                nRef2 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/USD");
                double usdsell = Double.parseDouble(usd_selling.getText().toString());
                nRef2.setValue(usdsell);

                nRef3 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/Euro");
                double eurobuy = Double.parseDouble(euro_buying.getText().toString());
                nRef3.setValue(eurobuy);

                nRef4 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/Euro");
                double eurosell = Double.parseDouble(euro_selling.getText().toString());
                nRef4.setValue(eurosell);

                nRef5 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/GB Pound");
                double GB_Poundbuy = Double.parseDouble(gbPound_buying.getText().toString());
                nRef5.setValue(GB_Poundbuy);

                nRef6 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/GB Pound");
                double GBPoundsell = Double.parseDouble(gbPound_selling.getText().toString());
                nRef6.setValue(GBPoundsell);

                nRef7 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/KSHS");
                double kshsbuy = Double.parseDouble(kshs_buying.getText().toString());
                nRef7.setValue(kshsbuy);

                nRef8 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/KSHS");
                double kshssell = Double.parseDouble(kshs_selling.getText().toString());
                nRef8.setValue(kshssell);

                nRef9 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/TSHS");
                double tshsbuy = Double.parseDouble(tshs_buying.getText().toString());
                nRef9.setValue(tshsbuy);

                nRef10 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/TSHS");
                double tshssell = Double.parseDouble(tshs_selling.getText().toString());
                nRef10.setValue(tshssell);

                nRef11 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Buying/RF");
                double rfbuy = Double.parseDouble(rf_buying.getText().toString());
                nRef11.setValue(rfbuy);

                nRef12 = new Firebase("https://forexexpress-db211.firebaseio.com/Bureaus/Prime Forex Bureau Ltd/Selling/RF");
                double rfsell = Double.parseDouble(rf_selling.getText().toString());
                nRef12.setValue(rfsell);

                nProgressDialog.setMessage("Sending Updated Rates.....");
                nProgressDialog.show();

                Toast.makeText(Prime_Update.this, "Prime Forex Bureau Ltd Rates Sent",
                        Toast.LENGTH_SHORT).show();
                nProgressDialog.dismiss();

                builder.setTitle("Proceed");
                builder.setMessage("Do you want to do more updates?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        notifyMe();
                        finish();
                        Intent mainIntent = new Intent(Prime_Update.this, Metropolitan_Update.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        notifyMe();
                        mAuth.signOut();
                        finish();
                        startActivity(new Intent(Prime_Update.this, LoginActivity.class));
                    }
                });
                AlertDialog alert = builder.show();

            }
        });
    }
    private void notifyMe() {
        //Build Notification
        notification.setSmallIcon(R.drawable.ic_launcher);
        notification.setTicker("Forex Express");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Forex Rates Update");
        notification.setContentText("The rates for Prime Forex Bureau Ltd have been updated in the database");

        Intent intent = new Intent(this, Prime_Update.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        // Builds the Notofication and Issues it
        NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        nm.notify(uniqueID,notification.build());


    }
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
