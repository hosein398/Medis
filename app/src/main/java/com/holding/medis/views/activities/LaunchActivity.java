package com.holding.medis.views.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.holding.medis.R;
import com.holding.medis.UserConfig;
import com.holding.medis.components.progressbutton.ProgressButton;
import com.holding.medis.contract.LaunchContract;
import com.holding.medis.presenters.LaunchPresenter;
import com.holding.medis.tools.UserTools;

public class LaunchActivity extends BaseActivity implements View.OnClickListener,LaunchContract.View {


    private ProgressButton btn_register;
    private Button btn_guest;
    private View line;
    private LaunchPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserConfig.getInstance().init(this, UserTools.getInstance().getLanguageType());
        createView(R.layout.activity_launch);
        initView();
        mPresenter = new LaunchPresenter(this);
        mPresenter.checkUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_guest:
                mPresenter.onClickGuestButton();
                break;
            case R.id.btn_register:
                mPresenter.onClickRegisterButton();
                break;
        }
    }

    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public void createView(int layout) {
        setContentView(layout);
    }

    @Override
    public void initView() {
        line = (View) findViewById(R.id.line);
        btn_guest = (Button) findViewById(R.id.btn_guest);
        btn_register = (ProgressButton) findViewById(R.id.btn_register);

        btn_guest.setOnClickListener(this);
        btn_register.setOnClickListener(this);
    }

    @Override
    public void hideButton() {
        btn_guest.setVisibility(View.GONE);
        line.setVisibility(View.GONE);
        btn_register.setVisibility(View.GONE);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openNextActivity(HomeActivity.class);
            }
        }, 8000);
    }

    @Override
    public void showButton() {
        btn_guest.setVisibility(View.VISIBLE);
        btn_register.setVisibility(View.VISIBLE);
        line.setVisibility(View.VISIBLE);
    }

    @Override
    public void openNextActivity(Class<?> activityClass) {
        if(activityClass == null){
            return;
        }
        Intent intent = new Intent(this, activityClass);
        this.startActivity(intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
