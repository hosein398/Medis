package com.holding.medis.views.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.holding.medis.R;
import com.holding.medis.UserConfig;
import com.holding.medis.constans.C;
import com.holding.medis.contract.RegisterContract;
import com.holding.medis.presenters.RegisterPresenter;
import com.holding.medis.tools.AnimHelper;
import com.holding.medis.tools.EditTextTools;
import com.holding.medis.tools.UserTools;

import org.greenrobot.eventbus.EventBus;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterContract.View {


    private EditText edt_surname;
    private ImageView btn_back;
    private ImageView btn_register;
    private EditText edt_firstname;
    private EditText edt_mobile;
    private ProgressBar prg_register;
    private RegisterPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserConfig.getInstance().init(this, UserTools.getInstance().getLanguageType());
        createView(R.layout.activity_register);
        initView();
        mPresenter = new RegisterPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                mPresenter.onClickBackButton();
                break;
            case R.id.btn_register:
                mPresenter.onClickRegisterButton();
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
        btn_back = (ImageView) findViewById(R.id.btn_back);
        btn_register = (ImageView) findViewById(R.id.btn_register);
        edt_firstname = (EditText) findViewById(R.id.edt_firstname);
        edt_surname = (EditText) findViewById(R.id.edt_surname);
        edt_mobile = (EditText) findViewById(R.id.edt_mobile);
        prg_register = (ProgressBar) findViewById(R.id.prg_register);
        btn_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void onBack() {
        onBackPressed();
    }

    @Override
    public String getMobile() {
        return edt_mobile.getText().toString();
    }

    @Override
    public String getFirstName() {
        return edt_firstname.getText().toString();
    }

    @Override
    public String getLastName() {
        return edt_surname.getText().toString();
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
    public void openVerificationActivity() {
        Intent intent = new Intent(RegisterActivity.this, VerificationActivity.class);
        EventBus.getDefault().postSticky(mPresenter.getUser());
        RegisterActivity.this.startActivityForResult(intent,C.VerificationActivity_request);
    }

    @Override
    public void showLoading() {
        AnimHelper.startAnimation(getAppContext(),btn_register,R.anim.icon_anim_fade_out);
        btn_register.setVisibility(View.GONE);
        AnimHelper.startAnimation(getAppContext(),prg_register,R.anim.icon_anim_fade_in);
        prg_register.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        AnimHelper.startAnimation(getAppContext(),btn_register,R.anim.icon_anim_fade_in);
        btn_register.setVisibility(View.VISIBLE);
        AnimHelper.startAnimation(getAppContext(),prg_register,R.anim.icon_anim_fade_out);
        prg_register.setVisibility(View.GONE);
    }

    @Override
    public boolean isVerifyForm() {
        if (EditTextTools.isEmptyOrNull(this, edt_firstname) ||
                EditTextTools.isEmptyOrNull(this, edt_surname) ||
                EditTextTools.isEmptyOrNull(this, edt_mobile) ||
                !EditTextTools.isMobileValid(this, edt_mobile, "98")) {
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if(resultCode != RESULT_OK){
            return;
        }
        switch (requestCode) {
            case C.VerificationActivity_request:
                UserTools.getInstance().setUser(mPresenter.getUser());
                openNextActivity(HomeActivity.class);
                break;
        }
        super.onActivityResult(requestCode, resultCode, intent);
    }
}
