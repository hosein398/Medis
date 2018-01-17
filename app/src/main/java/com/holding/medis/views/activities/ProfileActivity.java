package com.holding.medis.views.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holding.medis.R;
import com.holding.medis.UserConfig;
import com.holding.medis.components.stepper.StepperLayout;
import com.holding.medis.contract.ProfileContract;
import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.medisObj.User;
import com.holding.medis.presenters.ProfilePresenter;
import com.holding.medis.tools.AnimHelper;
import com.holding.medis.tools.UserTools;

public class ProfileActivity extends BaseActivity implements View.OnClickListener, ProfileContract.View {

    private ImageView btn_back;
    private ImageView btn_save;
    private ProgressBar prg_save;
    private ProfilePresenter mPresenter;
    private TextView txt_firstname;
    private TextView txt_surname;
    private TextView txt_mobile;
    private Button btn_language;
    private Button btn_currency;
    private Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserConfig.getInstance().init(this, UserTools.getInstance().getLanguageType());
        createView(R.layout.activity_profile);
        initView();
        mPresenter = new ProfilePresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                mPresenter.onClickBackButton();
                break;
            case R.id.btn_save:
                mPresenter.onClickSaveButton();
                break;

            case R.id.btn_language:
                mPresenter.onClickLanguageButton();
                break;

            case R.id.btn_currency:
                mPresenter.onClickCurrencyButton();
                break;

            case R.id.btn_logout:
                mPresenter.onClickLogoutButton();
                break;
        }
    }

    @Override
    public Context getAppContext() {
        return this;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void createView(int layout) {
        setContentView(layout);
    }

    @Override
    public void initView() {
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_save = (ImageView) findViewById(R.id.btn_save);
        prg_save = (ProgressBar) findViewById(R.id.prg_save);
        txt_firstname = (TextView) findViewById(R.id.txt_firstname);
        txt_surname = (TextView) findViewById(R.id.txt_surname);
        txt_mobile = (TextView) findViewById(R.id.txt_mobile);
        btn_language = (Button) findViewById(R.id.btn_language);
        btn_logout = (Button) findViewById(R.id.btn_logout);
        btn_currency = (Button) findViewById(R.id.btn_currency);

        btn_currency.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_language.setOnClickListener(this);
    }

    @Override
    public void showLoading() {
        AnimHelper.startAnimation(getAppContext(), btn_save, R.anim.icon_anim_fade_out);
        btn_save.setVisibility(View.GONE);
        AnimHelper.startAnimation(getAppContext(), prg_save, R.anim.icon_anim_fade_in);
        prg_save.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUserInfo(User user) {
        txt_firstname.setText(user.getFirstName());
        txt_mobile.setText(user.getMobile());
        txt_surname.setText(user.getSurName());
        setTextCurrencyButton(user.getCurrencyType());
    }

    @Override
    public void setTextCurrencyButton(CurrencyType currencyType) {
        btn_currency.setText(currencyType.toString());
    }

    @Override
    public void hideLoading() {
        AnimHelper.startAnimation(getAppContext(), btn_save, R.anim.icon_anim_fade_in);
        btn_save.setVisibility(View.VISIBLE);
        AnimHelper.startAnimation(getAppContext(), prg_save, R.anim.icon_anim_fade_out);
        prg_save.setVisibility(View.GONE);
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public void openNextActivity(Class<?> activityClass) {
        if (activityClass == null) {
            return;
        }
        Intent intent = new Intent(this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        openNextActivity(HomeActivity.class);
    }
}
