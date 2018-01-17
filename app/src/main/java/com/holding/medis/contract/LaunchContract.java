package com.holding.medis.contract;

import android.content.Context;

import com.holding.medis.views.activities.BaseActivity;
import com.holding.medis.views.activities.HomeActivity;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface LaunchContract {

    interface View{
        Context getAppContext();
        void createView(int layout);
        void initView();
        void hideButton();
        void showButton();
        void openNextActivity(Class<?> activityClass);
    }

    interface Presenter{
        void onClickRegisterButton();
        void onClickGuestButton();
        void checkUser();
    }
}
