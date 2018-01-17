package com.holding.medis.contract;

import android.content.Context;
import android.view.ViewGroup;

import com.holding.medis.models.enums.ViewMode;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.models.medisObj.User;
import com.holding.medis.views.fragments.CharityFragment;
import com.holding.medis.views.holders.CharityRowHolder;

import java.util.ArrayList;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface HomeContract {

    interface View {
        Context getAppContext();
        void createView(int layout);
        void initView();
        void initSteps();
        int getCurrentStepPosition();
        void openNextActivity(Class<?> activityClass);
        void showLoading();
        void hideLoading();
        void setIconViewModeButton(ViewMode viewMode);
    }

    interface Presenter {

        void onClickProfileButton();
        void onClickViewModeButton(CharityFragment charityFragment);

    }
}
