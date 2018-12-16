package com.forexexpress;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private GoogleApiClient mGoogleApiClient;
    private TextView logged_in_as;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private TextView tvUserName, tvUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);
        if (toolbar != null) {
            //setSupportActionBar(toolbar);
            toolbar.setTitle("Welcome");
        }

        mAuth = FirebaseAuth.getInstance();

        logged_in_as = findViewById(R.id.textViewWelcome);


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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);

        tvUserName = headerView.findViewById(R.id.tvUserName);
        tvUserEmail = headerView.findViewById(R.id.tvUserEmail);

        // Displaying the user details on the screen
        FirebaseUser user = mAuth.getCurrentUser();
        tvUserName.setText("Logged in as "+user.getDisplayName());
        tvUserEmail.setText(user.getEmail());


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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_bureau_ranking) {

            Intent bureauRankings = new Intent(this, BureauRankings.class);
            startActivity(bureauRankings);

        } else if (id == R.id.nav_locate_bureau) {

            Intent locateBureau = new Intent(this, LocationOption.class);
            startActivity(locateBureau);

        }else if (id == R.id.nav_convert_currency) {
            Intent convertCurrency = new Intent(this, Convert.class);
            startActivity(convertCurrency);

        }else if (id == R.id.nav_about) {
            Intent about_us = new Intent(this, About.class);
            startActivity(about_us);

        } else if (id == R.id.nav_help) {
            Intent help = new Intent(this, Help.class);
            startActivity(help);

        }else if (id==R.id.nav_logout){
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
        else if(id==R.id.nav_calculator){
            Intent calculator = new Intent(this, Calculator.class);
            startActivity(calculator);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}