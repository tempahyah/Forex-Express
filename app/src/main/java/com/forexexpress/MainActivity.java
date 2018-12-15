package com.forexexpress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.forexexpress.Conversion.FireBaseListAdapter;
import com.forexexpress.Update.Jetset_Update;
import com.forexexpress.Update.Metropolitan_Update;
import com.forexexpress.Update.Prime_Update;
import com.forexexpress.Update.Shumuk_Update;
import com.forexexpress.sample.rates.view.SelectActivity;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    private GoogleApiClient mGoogleApiClient;
    private TextView logged_in_as;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        logged_in_as = (TextView) findViewById(R.id.textViewWelcome);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        //End Config SignIn

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                }). addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    finish();
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));

                }

                else{
                    FirebaseUser user = mAuth.getCurrentUser();
                    if(user.getEmail().equals("metropolitanforex@gmail.com")){
                        finish();
                        Intent metropIntent = new Intent(MainActivity.this, Metropolitan_Update.class);
                        metropIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(metropIntent);
                    }
                    else if(user.getEmail().equals("jetsetforex@gmail.com")){
                        finish();
                        Intent jetsetIntent = new Intent(MainActivity.this, Jetset_Update.class);
                        jetsetIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(jetsetIntent);
                    }
                    else if(user.getEmail().equals("shumukforex@gmail.com")){
                        finish();
                        Intent shumukIntent = new Intent(MainActivity.this, Shumuk_Update.class);
                        shumukIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(shumukIntent);
                    }
                    else if(user.getEmail().equals("primeforex@gmail.com")){
                        finish();
                        Intent primeIntent = new Intent(MainActivity.this, Prime_Update.class);
                        primeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(primeIntent);
                    }
                    else{
                        logged_in_as.setText("Logged in as "+user.getEmail());
                    }
                }
            }
        };

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
                Intent myIntent = new Intent(view.getContext(), LocationOption.class);
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

        Button firebutton = (Button) findViewById(R.id.firebaseView);
        firebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), SelectActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });


    }

    @Override
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== R.id.action_logout){
            mAuth.signOut();
            // Google sign out
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {

                        }
                    });

            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (item.getItemId()== R.id.action_help){
            Intent help=new Intent();
            help.setClass(this,Help.class);
            startActivity(help);

        }
        if (item.getItemId()== R.id.action_about){
            Intent about=new Intent();
            about.setClass(this,AboutForex.class);
            startActivity(about);

        }
        return super.onOptionsItemSelected(item);
    }
}