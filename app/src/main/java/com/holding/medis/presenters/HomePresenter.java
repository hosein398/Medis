package com.holding.medis.presenters;

import android.view.ViewGroup;

import com.holding.medis.R;
import com.holding.medis.contract.HomeContract;
import com.holding.medis.contract.LaunchContract;
import com.holding.medis.models.enums.ViewMode;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.tools.UserTools;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.activities.ProfileActivity;
import com.holding.medis.views.activities.RegisterActivity;
import com.holding.medis.views.fragments.CharityFragment;
import com.holding.medis.views.holders.CharityRowHolder;

import java.util.ArrayList;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class HomePresenter implements HomeContract.Presenter {

    private final  HomeContract.View mView;

    public HomePresenter(HomeContract.View view) {
        this.mView = view;
        mView.setIconViewModeButton(UserTools.getInstance().getViewMode());
    }

    @Override
    public void onClickProfileButton() {
        if(UserTools.getInstance().getId() == null){
            mView.openNextActivity(RegisterActivity.class);
            return;
        }
        mView.openNextActivity(ProfileActivity.class);
    }

    @Override
    public void onClickViewModeButton(CharityFragment charityFragment) {
        if(charityFragment == null){
            return;
        }
        if(UserTools.getInstance().getViewMode() == ViewMode.Grid ){
            UserTools.getInstance().setViewMode(ViewMode.List);
            mView.setIconViewModeButton(ViewMode.List);
        }else{
            UserTools.getInstance().setViewMode(ViewMode.Grid);
            mView.setIconViewModeButton(ViewMode.Grid);
        }
        charityFragment.showCharities();
    }
}
