package com.holding.medis.presenters;

import android.os.Handler;

import com.holding.medis.contract.VerificationContract;
import com.holding.medis.models.medisObj.User;
import com.holding.medis.tools.UserTools;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.activities.VerificationActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class VerificationPresenter implements VerificationContract.Presenter {

    private final VerificationContract.View mView;


    public VerificationPresenter(VerificationContract.View view) {
        this.mView = view;
    }

    @Override
    public void onClickVerifyButton() {

        if(!mView.isVerifyForm()){
            return;
        }
        User user = (User) EventBus.getDefault().removeStickyEvent(User.class);
        verify(user.getId(), mView.getCode());
    }

    @Override
    public void onClickBackButton() {
        mView.onBack();
    }

    @Override
    public void onClickGetCode() {
        mView.showLoading();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.hideLoading();
                mView.startTimer(70000);
            }
        }, 3000);
    }

    @Override
    public void verify(int userId, String code) {
        mView.showLoading();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.hideLoading();
                mView.finishActivity();
            }
        }, 3000);
    }
}
