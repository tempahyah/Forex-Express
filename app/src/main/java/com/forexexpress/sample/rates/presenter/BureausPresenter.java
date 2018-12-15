package com.forexexpress.sample.rates.presenter;

import com.forexexpress.sample.rates.BureausContract;
import com.forexexpress.sample.rates.model.Bureau;
import com.forexexpress.sample.rates.model.loader.BureasDataSource;
import com.forexexpress.sample.rates.model.loader.CONSTANTS;
import com.forexexpress.sample.rates.model.loader.FirebaseRtDb;

import java.util.List;

public class BureausPresenter implements BureausContract.Presenter {

    private BureausContract.View view;
    private FirebaseRtDb firebase;

    public BureausPresenter(BureausContract.View view, FirebaseRtDb firebase){
        this.view = view;
        this.firebase = firebase;
        view.setPresenter(this);
    }

    @Override
    public void start() {
        loadBureaus();
    }


    @Override
    public void loadBureaus() {
        firebase.loadBureaus(new BureasDataSource.LoadBureausCallback() {
            @Override
            public void onBureausLoaded(List<Bureau> bureaus) {
                view.showBureaus(bureaus);
            }

            @Override
            public void onBureausNotAvailable() {
                view.showError("Error");
            }
        });
    }

}
