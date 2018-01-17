package com.holding.medis.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.holding.medis.presenters.CharityPresenter;
import com.holding.medis.presenters.PricePresenter;
import com.holding.medis.views.holders.CharityRowHolder;
import com.holding.medis.views.holders.PriceRowHolder;

/**
 * Created by hosein on 2/12/2017.
 */

public class PriceAdapter extends RecyclerView.Adapter<PriceRowHolder> {


    private PricePresenter mPresenter;

    public PriceAdapter(PricePresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public PriceRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return mPresenter.createViewHolder(viewGroup,i);
    }


    @Override
    public void onBindViewHolder(final PriceRowHolder feedListRowHolder, final int position) {
        mPresenter.bindViewHolder(feedListRowHolder,position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCharitiesCount();
    }

}
