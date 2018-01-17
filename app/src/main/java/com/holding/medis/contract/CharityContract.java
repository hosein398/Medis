package com.holding.medis.contract;

import android.content.Context;
import android.view.ViewGroup;

import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.views.holders.CharityRowHolder;

import java.util.ArrayList;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface CharityContract {
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
        void onClickCharityItem(Charity charity);
        void showSpinnerItems(int min, int max);
    }

    interface Presenter {
        void loadCharities();
        ArrayList<Charity> getSelectedCharities();
        void filter(String filter);
        void selectAllCharity(boolean isSelect);
        void selectRandomCharity(int value);
        ArrayList<Charity> getCharities();
        int getCharitiesCount();
        CharityRowHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(CharityRowHolder holder, int position);
    }
}
