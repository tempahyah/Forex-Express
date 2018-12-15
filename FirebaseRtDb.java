package ug.co.rcc.rcc.items.model.source;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import ug.co.rcc.rcc.common.CONSTANTS;
import ug.co.rcc.rcc.common.Firebase;
import ug.co.rcc.rcc.items.model.Item;

/**
 * Created by Mungujakisa Nickson
 * on 6/10/2017.
 */

public class FirebaseRtDb implements Firebase, ItemsDataSource {

    private DatabaseReference database;
    private DatabaseReference database_node;

    private static FirebaseRtDb INSTANCE = null;

    public static FirebaseRtDb getInstance(){
        if(INSTANCE == null){
            INSTANCE = new FirebaseRtDb();
        }
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public FirebaseRtDb(){
        connect();
    }

    @Override
    public void connect() {
        database = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void setNode(String NODE) {
        switch (NODE){
            case CONSTANTS.SERMONS:
                database_node = database.child(CONSTANTS.DEVOTIONS);
                break;
            case CONSTANTS.DEVOTIONS:
                database_node = database.child(CONSTANTS.DEVOTIONS);
                break;
            case CONSTANTS.MESSAGES:
                database_node = database.child(CONSTANTS.DEVOTIONS);
                break;
            case CONSTANTS.EVENTS:
                database_node = database.child(CONSTANTS.DEVOTIONS);
                break;
            default:
                database_node = database.child(CONSTANTS.DEVOTIONS);
                break;
        }
    }

    @Override
    public void loadItems(final LoadItemsCallback callback) {
        database_node.addValueEventListener(new ValueEventListener() {
            List<Item> items = new ArrayList<Item>();
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item_snapshot: dataSnapshot.getChildren()){
                    Item item = item_snapshot.getValue(Item.class);
                    items.add(item);
                }
                callback.onItemsLoaded(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onItemsNotAvailable();
            }
        });
    }

}
