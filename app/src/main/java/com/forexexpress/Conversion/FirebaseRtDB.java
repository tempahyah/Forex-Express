package com.forexexpress.Conversion;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;


public class FirebaseRtDB extends Application {
    public void onCreate(){
        super.onCreate();

        //store everything retrieved from firebase

        if (!FirebaseApp.getApps(this).isEmpty()){
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
