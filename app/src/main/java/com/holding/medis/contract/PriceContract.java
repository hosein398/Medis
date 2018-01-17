package com.holding.medis.contract;

import android.content.Context;
import android.view.ViewGroup;

import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.views.holders.CharityRowHolder;
import com.holding.medis.views.holders.PriceRowHolder;

import java.util.ArrayList;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface PriceContract {
    interface View {
        Context getAppContext();
        void initView();
        void showLoading();
        void hideLoading();
        void showCharities();
        void notifyDataSetChange();
        void notifyItemInserted(int layoutPosition);
        void notifyItemRemoved(int layoutPosition);
        void notifyItemRangeChanged(int positionStart, int itemCount);
    }

    interface Presenter {
        void setSelectedCharities(ArrayList<Charity> charities);
        ArrayList<Charity> getCharities();
        int getCharitiesCount();
        PriceRowHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(PriceRowHolder holder, int position);
    }
}
