package com.holding.medis.contract;

import android.content.Context;
import android.view.ViewGroup;

import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.views.holders.PriceRowHolder;

import java.util.ArrayList;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface CalenderContract {
    interface View {
        Context getAppContext();
        void initView();
        void setTotalAmount(int totalAmount, CurrencyType currencyType);
    }

    interface Presenter {
        void calculatTotalAmount(ArrayList<Charity> charities);
    }
}
