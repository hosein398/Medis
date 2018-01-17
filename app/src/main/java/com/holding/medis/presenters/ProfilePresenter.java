package com.holding.medis.presenters;

import android.os.Handler;
import android.support.design.widget.BottomSheetDialog;

import com.holding.medis.UserConfig;
import com.holding.medis.constans.C;
import com.holding.medis.contract.LaunchContract;
import com.holding.medis.contract.ProfileContract;
import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.enums.LanguageType;
import com.holding.medis.tools.Prefs;
import com.holding.medis.tools.UserTools;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.activities.LaunchActivity;
import com.holding.medis.views.activities.RegisterActivity;
import com.holding.medis.views.dialogs.CurrencyBottomSheedDialog;
import com.holding.medis.views.dialogs.LanguageBottomSheedDialog;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private final  ProfileContract.View mView;

    public ProfilePresenter(ProfileContract.View view) {
        this.mView = view;
        mView.setUserInfo(UserTools.getInstance().getUser());
    }


    @Override
    public void onClickBackButton() {
        mView.openNextActivity(HomeActivity.class);
    }

    @Override
    public void onClickSaveButton() {
        mView.showLoading();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.hideLoading();
                mView.onBack();
            }
        }, 3000);
    }

    @Override
    public void onClickLanguageButton() {
        new LanguageBottomSheedDialog(mView.getAppContext()).setListener(new LanguageBottomSheedDialog.ListenerLanguageBottomSheedDialog() {
            @Override
            public void onClick(BottomSheetDialog bottomSheetDialog, LanguageType languageType) {
                UserConfig.getInstance().init(mView.getActivity(),languageType);
                mView.getActivity().finish();
                mView.getAppContext().startActivity(mView.getActivity().getIntent());
            }
        }).show();
    }

    @Override
    public void onClickCurrencyButton() {
        new CurrencyBottomSheedDialog(mView.getAppContext()).setListener(new CurrencyBottomSheedDialog.ListenerCurrencyBottomSheedDialog() {
            @Override
            public void onClick(BottomSheetDialog bottomSheetDialog, CurrencyType currencyType) {
                mView.setTextCurrencyButton(currencyType);
            }
        }).show();
    }

    @Override
    public void onClickLogoutButton() {
        Prefs.remove(C.USERPREF);
        mView.openNextActivity(LaunchActivity.class);
    }
}
