package com.forexexpress.sample.rates.model.loader;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.forexexpress.sample.rates.model.Bureau;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRtDb implements BureasDataSource {

    private DatabaseReference database;
    private DatabaseReference database_node;

    public FirebaseRtDb(){
        connect();
    }
    
    public void connect() {
        database = FirebaseDatabase.getInstance().getReference();
        database = database.child("Bureau Data");
        database_node = database.child(CONSTANTS.BASE);
    }

    @Override
    public void loadBureaus(final LoadBureausCallback callback) {
        database_node.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Bureau> Bureaus = new ArrayList<Bureau>();

                //Get access to all of the immediate children of this snapshot (dataSnapshot)
                for (DataSnapshot Bureau_snapshot: dataSnapshot.getChildren()){
                    Bureau Bureau = Bureau_snapshot.getValue(Bureau.class);
                    Bureau.setName(Bureau_snapshot.getKey());
                    Bureaus.add(Bureau);
                    //The immediate children of this snapshot are returned
                }
                callback.onBureausLoaded(Bureaus);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onBureausNotAvailable();
            }
        });
    }
}
