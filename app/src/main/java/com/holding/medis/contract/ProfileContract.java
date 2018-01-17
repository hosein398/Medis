package com.holding.medis.contract;

import android.app.Activity;
import android.content.Context;

import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.medisObj.User;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public interface ProfileContract {

    interface View {
        Context getAppContext();
        Activity getActivity();
        void createView(int layout);
        void initView();
        void hideLoading();
        void showLoading();
        void setUserInfo(User user);
        void setTextCurrencyButton(CurrencyType currencyType);
        void onBack();
        void openNextActivity(Class<?> activityClass);
    }

    interface Presenter {
        void onClickBackButton();
        void onClickSaveButton();
        void onClickLanguageButton();
        void onClickCurrencyButton();
        void onClickLogoutButton();
    }
}
