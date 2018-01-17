package com.holding.medis.views.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.holding.medis.R;
import com.holding.medis.components.SimpleSearchView;
import com.holding.medis.components.smoothcheckbox.SmoothCheckBox;
import com.holding.medis.components.stepper.Step;
import com.holding.medis.components.stepper.VerificationError;
import com.holding.medis.constans.C;
import com.holding.medis.contract.CharityContract;
import com.holding.medis.models.enums.LanguageType;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.presenters.CharityPresenter;
import com.holding.medis.tools.UserTools;
import com.holding.medis.tools.ValidationTools;
import com.holding.medis.views.activities.HomeActivity;
import com.holding.medis.views.adapters.CharityAdapter;
import com.holding.medis.views.adapters.CountAdapter;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;


/**
 * Created by hosein on 2/13/2017.
 */

public class CharityFragment extends Fragment implements View.OnClickListener, Step, CharityContract.View,SimpleSearchView.ListenerSimpleSearchView,SmoothCheckBox.OnCheckedChangeListener,Spinner.OnItemSelectedListener {

    public ViewGroup view;
    public ViewGroup container_search_view;
    public SimpleSearchView simpleSearchView;
    private RecyclerView rcl_charity;
    private CharityAdapter charityAdapter;
    private ProgressBar prg_charity;
    private Spinner spn_count;
    private SmoothCheckBox smooth_checkbox_all;
    private CharityPresenter mPresenter;

    public static CharityFragment instance() {
        CharityFragment fragment = new CharityFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = (ViewGroup) inflater.inflate(R.layout.fragment_charity, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initView();
        mPresenter = new CharityPresenter(this);
        mPresenter.loadCharities();
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        HomeActivity activity = (HomeActivity) getActivity();
        if(ValidationTools.isEmptyOrNull(mPresenter.getSelectedCharities())){
            mPresenter.selectRandomCharity(3);
        }
        activity.setSelectedCharities(mPresenter.getSelectedCharities());
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public Context getAppContext() {
        return getActivity();
    }


    @Override
    public void initView() {
        prg_charity = (ProgressBar) view.findViewById(R.id.prg_charity);
        rcl_charity = (RecyclerView) view.findViewById(R.id.rcl_charity);
        container_search_view = (ViewGroup) view.findViewById(R.id.container_search_view);
        smooth_checkbox_all = (SmoothCheckBox) view.findViewById(R.id.smooth_checkbox_all);
        spn_count = (Spinner) view.findViewById(R.id.spn_count);
        simpleSearchView = new SimpleSearchView(getAppContext());
        container_search_view.addView(simpleSearchView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);


        simpleSearchView.setListener(this);
        smooth_checkbox_all.setOnCheckedChangeListener(this);
        spn_count.setOnItemSelectedListener(this);
    }

    @Override
    public void showLoading() {
        prg_charity.setVisibility(View.VISIBLE);
        rcl_charity.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        prg_charity.setVisibility(View.GONE);
        rcl_charity.setVisibility(View.VISIBLE);
    }

    @Override
    public void showCharities() {
        rcl_charity.setLayoutManager(new GridLayoutManager(getAppContext(), UserTools.getInstance().getViewMode().getValue()));
        charityAdapter = new CharityAdapter(mPresenter);
        rcl_charity.setAdapter(charityAdapter);
    }

    @Override
    public void notifyDataSetChange() {
        if (charityAdapter != null) {
            charityAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void notifyItemInserted(int layoutPosition) {
        if (charityAdapter != null) {
            charityAdapter.notifyItemInserted(layoutPosition);
        }
    }

    @Override
    public void notifyItemRemoved(int layoutPosition) {
        if (charityAdapter != null) {
            charityAdapter.notifyItemRemoved(layoutPosition);
        }
    }

    @Override
    public void notifyItemRangeChanged(int positionStart, int itemCount) {
        if (charityAdapter != null) {
            charityAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }
    }

    @Override
    public void onClickCharityItem(Charity charity) {
        spn_count.setSelection(0);
    }

    @Override
    public void showSpinnerItems(int min, int max) {
        ArrayList<Integer> mValues = new ArrayList<>();
        for(int i = min ; i< max ; i++){
            mValues.add(i);
        }
        CountAdapter countAdapter = new CountAdapter(getAppContext(),android.R.layout.simple_spinner_item,mValues);
        spn_count.setAdapter(countAdapter);
    }

    @Override
    public void onTextChange(String newText) {
        if(charityAdapter == null){
            return;
        }
        mPresenter.filter(newText);
    }

    @Override
    public void onVoiceSearchClick() {
        startVoiceRecognitionActivity(C.PERMISSION_REQUEST_CODE_CAT_RECOGNIZER);
    }

    private void startVoiceRecognitionActivity(int requestCode) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getResources().getText(R.string.search_hint));
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, LanguageType.getStringCode(UserTools.getInstance().getLanguageType()));
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == C.PERMISSION_REQUEST_CODE_CAT_RECOGNIZER && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            simpleSearchView.setText(matches.get(0));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
        mPresenter.selectAllCharity(isChecked);
        spn_count.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int value = (int) spn_count.getSelectedItem();
        if(value == 0){
            return;
        }
        if(smooth_checkbox_all.isChecked()){
            smooth_checkbox_all.setChecked(false);
        }
        mPresenter.selectRandomCharity(value);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
