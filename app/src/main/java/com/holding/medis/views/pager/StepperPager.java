package com.holding.medis.views.pager;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;


import com.holding.medis.ApplicationLoader;
import com.holding.medis.R;
import com.holding.medis.components.stepper.Step;
import com.holding.medis.components.stepper.adapter.AbstractFragmentStepAdapter;
import com.holding.medis.components.stepper.viewmodel.StepViewModel;
import com.holding.medis.tools.ValidationTools;

import java.util.ArrayList;

/**
 * Created by hosein on 6/28/2017.
 */

public class StepperPager extends AbstractFragmentStepAdapter {

    private ArrayList<Step> fragments;
    public StepperPager(ArrayList<Step> fragments, FragmentManager fm, Context context) {
        super(fm, context);
        this.fragments = fragments;
    }

    @Override
    public Step createStep(int position) {
        if(ValidationTools.isEmptyOrNull(fragments)){
            return null;
        }
        return  fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {

        return new StepViewModel.Builder(context)
                .setTitle(getPageTitle(position))
                .create();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return context.getResources().getText(R.string.select_charity);
            case 1:
                return context.getResources().getText(R.string.select_price);
            case 2:
                return context.getResources().getText(R.string.select_date);
            default:
                return context.getResources().getText(R.string.select_charity);
        }
    }
}
