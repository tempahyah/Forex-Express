package com.forexexpress.sample.rates;

import com.forexexpress.sample.rates.model.Bureau;

import java.util.List;

public interface BureausContract {

    interface View<Presenter>{

        void setPresenter(Presenter presenter);

        void showBureaus(List<Bureau> bureaus);

        void showError(String message);
    }

    interface Presenter {

        void start();

        void loadBureaus();
    }
}
