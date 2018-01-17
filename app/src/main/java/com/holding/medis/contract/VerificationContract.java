package com.holding.medis.contract;

import android.content.Context;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface VerificationContract {
    interface View{
        Context getAppContext();
        void createView(int layout);
        void initView();
        void onBack();
        String getCode();
        void finishActivity();
        void showLoading();
        void hideLoading();
        void startTimer(int milis);
        void finishTimer();
        boolean isVerifyForm();
    }

    interface Presenter{
        void onClickVerifyButton();
        void onClickBackButton();
        void onClickGetCode();
        void verify(int userId,String code);
    }
}
