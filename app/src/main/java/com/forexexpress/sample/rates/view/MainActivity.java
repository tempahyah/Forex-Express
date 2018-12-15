package com.forexexpress.sample.rates.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.forexexpress.R;
import com.forexexpress.sample.rates.BureausContract;
import com.forexexpress.sample.rates.model.Bureau;
import com.forexexpress.sample.rates.model.loader.CONSTANTS;
import com.forexexpress.sample.rates.model.loader.FirebaseRtDb;
import com.forexexpress.sample.rates.presenter.BureausPresenter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BureausContract.View {

    private BureausContract.Presenter presenter;
    private BureausAdapter bureaus_adpater;
    private ListView list_view;
    private List<Bureau> bureaus;
    private FirebaseRtDb firebase;
    private TextView rankingSelection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_values);
        rankingSelection = (TextView) findViewById(R.id.rankingSelection);
        setUpToolbar();
        setUpListView();
        setUpBureausRepository();
        startPresenter();
        getPassedIn();
        //sortList();
    }

    //private void sortList() {

    //}

    private void startPresenter() {
        presenter = new BureausPresenter(this, firebase);
        presenter.start();
    }

    private void setUpToolbar() {

    }

    private void setUpListView() {
        list_view = (ListView) findViewById(R.id.lv_bureaus);
        setUpAdapter();
        list_view.setAdapter(bureaus_adpater);

    }

    private void setUpBureausRepository() {
        firebase = new FirebaseRtDb();
    }

    private void setUpAdapter(){
        bureaus = new ArrayList<>();
        bureaus_adpater = new BureausAdapter(this, R.layout.bureau_entry, bureaus);
    }

    private void getPassedIn() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){

            bureaus_adpater.setCurrency(bundle.getString(CONSTANTS.CURRENCY));
            bureaus_adpater.setExchangeType(bundle.getString(CONSTANTS.EXCHANGE_TYPE));

            rankingSelection.setText(bundle.getString(CONSTANTS.CURRENCY)+"  "+ bundle.getString(CONSTANTS.EXCHANGE_TYPE)+" "+" RATES RANKED");


        }
    }

    @Override
    public void setPresenter(Object presenter) {
        this.presenter = (BureausPresenter) presenter;
    }

    @Override
    public void showBureaus(List list) {
        /**/
        this.bureaus.clear();
        this.bureaus.addAll(list);
        bureaus_adpater.notifyDataSetChanged();
        /**/
        //bureaus_adpater.refresh(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.buying_refresh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        presenter.loadBureaus();
        return true;
    }
}
