package com.holding.medis.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.holding.medis.R;
import com.holding.medis.components.stepper.Step;
import com.holding.medis.components.stepper.VerificationError;
import com.holding.medis.contract.PriceContract;
import com.holding.medis.presenters.CharityPresenter;
import com.holding.medis.presenters.PricePresenter;
import com.holding.medis.tools.UserTools;
import com.holding.medis.tools.ValidationTools;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.adapters.CharityAdapter;
import com.holding.medis.views.adapters.PriceAdapter;


/**
 * Created by hosein on 2/13/2017.
 */

public class PriceFragment extends Fragment implements View.OnClickListener, Step, PriceContract.View {

    public ViewGroup view;
    private RecyclerView rcl_charity;
    private PriceAdapter priceAdapter;
    private PricePresenter mPresenter;

    public PricePresenter getPresenter() {
        return mPresenter;
    }

    public static PriceFragment instance() {
        PriceFragment fragment = new PriceFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (view != null) {
            return view;
        }
        view = (ViewGroup) inflater.inflate(R.layout.fragment_price, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initView();
        mPresenter = new PricePresenter(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        HomeActivity activity = (HomeActivity) getActivity();
        activity.setSelectedCharities(activity.getSelectedCharities());
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public Context getAppContext() {
        return getActivity();
    }

    @Override
    public void initView() {
        rcl_charity = (RecyclerView) view.findViewById(R.id.rcl_charity);
        rcl_charity.setLayoutManager(new GridLayoutManager(getAppContext(), 1));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showCharities() {
        priceAdapter = new PriceAdapter(mPresenter);
        rcl_charity.setAdapter(priceAdapter);
    }

    @Override
    public void notifyDataSetChange() {
        priceAdapter.notifyDataSetChanged();
    }

    @Override
    public void notifyItemInserted(int layoutPosition) {
        priceAdapter.notifyItemInserted(layoutPosition);
    }

    @Override
    public void notifyItemRemoved(int layoutPosition) {
        priceAdapter.notifyItemRemoved(layoutPosition);
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        priceAdapter.notifyItemRangeChanged(positionStart, itemCount);
    }
}
