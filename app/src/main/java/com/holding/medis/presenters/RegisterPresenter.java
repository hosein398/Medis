package com.holding.medis.presenters;

import android.os.Handler;

import com.holding.medis.contract.LaunchContract;
import com.holding.medis.contract.RegisterContract;
import com.holding.medis.models.medisObj.User;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.activities.RegisterActivity;
import com.holding.medis.views.activities.VerificationActivity;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class RegisterPresenter implements RegisterContract.Presenter {

    private final  RegisterContract.View mView;
    private User mUser;

    public RegisterPresenter(RegisterContract.View view) {
        this.mView = view;
    }


    @Override
    public void onClickRegisterButton() {
        if(!mView.isVerifyForm()){
            return;
        }
        mView.showLoading();
        mUser = new User(12,mView.getFirstName(),mView.getLastName(),mView.getMobile());
        register(mUser);
    }

    @Override
    public void onClickBackButton() {
        mView.onBack();
    }

    @Override
    public User getUser() {
        return mUser;
    }

    @Override
    public void register(User user) {

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.hideLoading();
                mView.openVerificationActivity();
            }
        }, 3000);
    }
}
