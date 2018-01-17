package com.holding.medis.views.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.holding.medis.R;
import com.holding.medis.UserConfig;
import com.holding.medis.components.stepper.Step;
import com.holding.medis.components.stepper.StepperLayout;
import com.holding.medis.components.stepper.VerificationError;
import com.holding.medis.contract.HomeContract;
import com.holding.medis.models.enums.ViewMode;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.presenters.HomePresenter;
import com.holding.medis.tools.UserTools;
import com.holding.medis.views.fragments.CalenderFragment;
import com.holding.medis.views.fragments.CharityFragment;
import com.holding.medis.views.fragments.PriceFragment;
import com.holding.medis.views.pager.StepperPager;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements View.OnClickListener, HomeContract.View,StepperLayout.StepperListener {


    private StepperLayout mStepperLayout;
    private ImageView btn_viewmode;
    private ImageView btn_profile;
    private CharityFragment charityFragment;
    private PriceFragment priceFragment;
    private CalenderFragment calenderFragment;
    private HomePresenter mPresenter;
    private ArrayList<Charity> selectedCharities;



    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UserConfig.getInstance().init(this, UserTools.getInstance().getLanguageType());
        createView(R.layout.activity_home);
        initView();
        initSteps();
        mPresenter = new HomePresenter(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_profile:
                mPresenter.onClickProfileButton();
                break;
            case R.id.btn_viewmode:
                mPresenter.onClickViewModeButton(charityFragment);
                break;
        }
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
        btn_profile = (ImageView) findViewById(R.id.btn_profile);
        btn_viewmode = (ImageView) findViewById(R.id.btn_viewmode);
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);

        mStepperLayout.setOffscreenPageLimit(1000);
        mStepperLayout.setListener(this);
        btn_viewmode.setOnClickListener(this);
        btn_profile.setOnClickListener(this);
    }

    @Override
    public void initSteps() {
        ArrayList<Step> fragments = new ArrayList<>();
        charityFragment = CharityFragment.instance();
        priceFragment = PriceFragment.instance();
        calenderFragment = CalenderFragment.instance();
        fragments.add(charityFragment);
        fragments.add(priceFragment);
        fragments.add(calenderFragment);
        mStepperLayout.setAdapter(new StepperPager(fragments, getSupportFragmentManager(), this));
    }

    @Override
    public int getCurrentStepPosition() {
        return (mStepperLayout == null ? 0 : mStepperLayout.getCurrentStepPosition());
    }

    @Override
    public void openNextActivity(Class<?> activityClass) {
        if (activityClass == null) {
            return;
        }
        Intent intent = new Intent(HomeActivity.this, activityClass);
        HomeActivity.this.startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void setIconViewModeButton(ViewMode viewMode) {
        if(viewMode == ViewMode.List){
            btn_viewmode.setImageResource(R.drawable.ic_view_list_black_24dp);
        }else{
            btn_viewmode.setImageResource(R.drawable.ic_view_module_black_24dp);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onCompleted(View completeButton) {
        String url = "http://www.medis-holding.com";
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {
        if(newStepPosition == 0){
            btn_viewmode.setVisibility(View.VISIBLE);
            return;
        }
        btn_viewmode.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onReturn() {

    }

    public ArrayList<Charity> getSelectedCharities() {
        return selectedCharities;
    }

    public void setSelectedCharities(ArrayList<Charity> selectedCharities) {
        this.selectedCharities = selectedCharities;
        priceFragment.getPresenter().setSelectedCharities(selectedCharities);
        calenderFragment.getPresenter().calculatTotalAmount(selectedCharities);
    }
}
