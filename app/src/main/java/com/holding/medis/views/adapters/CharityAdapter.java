package com.holding.medis.views.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.holding.medis.presenters.CharityPresenter;
import com.holding.medis.presenters.HomePresenter;
import com.holding.medis.views.holders.CharityRowHolder;

/**
 * Created by hosein on 2/12/2017.
 */

public class CharityAdapter extends RecyclerView.Adapter<CharityRowHolder> {


    private CharityPresenter mPresenter;

    public CharityAdapter(CharityPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public CharityRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return mPresenter.createViewHolder(viewGroup,i);
    }


    @Override
    public void onBindViewHolder(final CharityRowHolder feedListRowHolder, final int position) {
        mPresenter.bindViewHolder(feedListRowHolder,position);
    }

    @Override
    public int getItemCount() {
        return mPresenter.getCharitiesCount();
    }

}
