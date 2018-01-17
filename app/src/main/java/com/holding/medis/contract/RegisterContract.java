package com.holding.medis.contract;

import android.content.Context;

import com.holding.medis.models.medisObj.User;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface RegisterContract {

    interface View{
        Context getAppContext();
        void createView(int layout);
        void initView();
        void onBack();
        String getMobile();
        String getFirstName();
        String getLastName();
        void openNextActivity(Class<?> activityClass);
        void openVerificationActivity();
        void showLoading();
        void hideLoading();
        boolean isVerifyForm();
    }

    interface Presenter{
        void onClickRegisterButton();
        void onClickBackButton();
        User getUser();
        void register(User user);
    }
}
