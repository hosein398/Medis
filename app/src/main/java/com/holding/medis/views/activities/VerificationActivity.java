package com.holding.medis.views.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holding.medis.R;
import com.holding.medis.UserConfig;
import com.holding.medis.components.TextViewCell;
import com.holding.medis.components.countdownview.CountdownView;
import com.holding.medis.contract.VerificationContract;
import com.holding.medis.presenters.RegisterPresenter;
import com.holding.medis.presenters.VerificationPresenter;
import com.holding.medis.tools.AnimHelper;
import com.holding.medis.tools.EditTextTools;
import com.holding.medis.tools.UserTools;

public class VerificationActivity extends BaseActivity implements View.OnClickListener,VerificationContract.View {

    public CountdownView cv_countdown;
    private ImageView btn_back;
    private ImageView btn_verify;
    private EditText edt_verify;
    private TextView btn_getcode;
    private ProgressBar prg_verify;
    private VerificationPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserConfig.getInstance().init(this, UserTools.getInstance().getLanguageType());
        createView(R.layout.activity_verification);
        initView();
        mPresenter = new VerificationPresenter(this);
        startTimer(70000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_back:
                mPresenter.onClickBackButton();
                break;
            case R.id.btn_verify:
                mPresenter.onClickVerifyButton();
                break;
            case R.id.btn_getcode:
                mPresenter.onClickGetCode();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public Context getAppContext() {
        return this;
    }

    @Override
    public void createView(int layout) {
        setContentView(layout);
    }

    @Override
    public void initView() {
        cv_countdown = (CountdownView) findViewById(R.id.cv_countdown);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_verify = (ImageView) findViewById(R.id.btn_verify);
        btn_getcode = (TextView) findViewById(R.id.btn_getcode);
        edt_verify = (EditText) findViewById(R.id.edt_verify);
        prg_verify = (ProgressBar) findViewById(R.id.prg_verfy);
        btn_getcode.setVisibility(View.GONE);

        btn_back.setOnClickListener(this);
        btn_verify.setOnClickListener(this);
        btn_getcode.setOnClickListener(this);
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public String getCode() {
        return edt_verify.getText().toString();
    }

    @Override
    public void finishActivity() {
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        VerificationActivity.this.finish();
    }

    @Override
    public void showLoading() {
        AnimHelper.startAnimation(getAppContext(),btn_verify,R.anim.icon_anim_fade_out);
        btn_verify.setVisibility(View.GONE);
        AnimHelper.startAnimation(getAppContext(),prg_verify,R.anim.icon_anim_fade_in);
        prg_verify.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        AnimHelper.startAnimation(getAppContext(),btn_verify,R.anim.icon_anim_fade_in);
        btn_verify.setVisibility(View.VISIBLE);
        AnimHelper.startAnimation(getAppContext(),prg_verify,R.anim.icon_anim_fade_out);
        prg_verify.setVisibility(View.GONE);
    }

    @Override
    public void startTimer(int milis) {
        btn_getcode.setVisibility(View.GONE);
        cv_countdown.start(milis);
        cv_countdown.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
            @Override
            public void onEnd(CountdownView cv) {
                finishTimer();
            }
        });
    }

    @Override
    public void finishTimer() {
        btn_getcode.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isVerifyForm() {
        if (EditTextTools.isEmptyOrNull(this, edt_verify)) {
            return false;
        }
        return true;
    }
}
