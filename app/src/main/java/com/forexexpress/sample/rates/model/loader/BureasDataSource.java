package com.forexexpress.sample.rates.model.loader;

import com.forexexpress.sample.rates.model.Bureau;
import java.util.List;

public interface BureasDataSource {

    interface LoadBureausCallback {

        void onBureausLoaded(List<Bureau> bureaus);

        void onBureausNotAvailable();
    }

    void loadBureaus(LoadBureausCallback callback);

}
