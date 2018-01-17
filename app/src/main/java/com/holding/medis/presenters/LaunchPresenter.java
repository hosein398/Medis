package com.holding.medis.presenters;

import com.holding.medis.contract.LaunchContract;
import com.holding.medis.tools.UserTools;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.activities.RegisterActivity;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class LaunchPresenter implements LaunchContract.Presenter {

    private final  LaunchContract.View mView;

    public LaunchPresenter(LaunchContract.View view) {
        this.mView = view;
    }

    @Override
    public void onClickRegisterButton() {
        mView.openNextActivity(RegisterActivity.class);
    }

    @Override
    public void onClickGuestButton() {
        mView.openNextActivity(HomeActivity.class);
    }

    @Override
    public void checkUser() {
        if(UserTools.getInstance().getId() == null){
            mView.showButton();
            return;
        }
        mView.hideButton();
    }
}
