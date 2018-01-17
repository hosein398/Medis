package com.holding.medis.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.holding.medis.R;
import com.holding.medis.components.stepper.Step;
import com.holding.medis.components.stepper.VerificationError;
import com.holding.medis.components.sundatepicker.DatePicker;
import com.holding.medis.components.sundatepicker.components.DateItem;
import com.holding.medis.components.sundatepicker.interfaces.DateSetListener;
import com.holding.medis.contract.CalenderContract;
import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.presenters.CalenderPresenter;

import java.util.Calendar;
import java.util.Locale;

import static android.R.attr.id;


/**
 * Created by hosein on 2/13/2017.
 */

public class CalenderFragment extends Fragment implements View.OnClickListener, Step, DateSetListener,CalenderContract.View {

    public ViewGroup view;
    private TextView txt_date_selected;
    private TextView txt_total_amount;
    private CalenderPresenter mPresenter;

    public CalenderPresenter getPresenter() {
        return mPresenter;
    }

    public static CalenderFragment instance() {
        CalenderFragment fragment = new CalenderFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view != null) {
            return view;
        }
        view = (ViewGroup) inflater.inflate(R.layout.fragment_calender, null);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(layoutParams);
        initView();
        mPresenter = new CalenderPresenter(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txt_date_selected:

                DatePicker.Builder builder = new DatePicker
                        .Builder()
                        .id(10)
                        //.showYearFirst(true)
                        //.closeYearAutomatically(true)
                        //.minYear(1393)
                        .future(false);

                builder.date(1 ,1 ,1396);
                builder.build(this)
                        .show(getActivity().getSupportFragmentManager(), "");
                break;
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
        Date mStartDate = new Date();
        mStartDate.setDate(day, month, year);
        txt_date_selected.setText(mStartDate.getDate());
    }

    @Override
    public Context getAppContext() {
        return getActivity();
    }

    @Override
    public void initView() {
        txt_date_selected = (TextView) view.findViewById(R.id.txt_date_selected);
        txt_total_amount = (TextView) view.findViewById(R.id.txt_total_amount);

        txt_date_selected.setOnClickListener(this);
    }


    @Override
    public void setTotalAmount(int totalAmount, CurrencyType currencyType) {
        txt_total_amount.setText("" + totalAmount + "  " + currencyType.toString());
    }

    class Date extends DateItem {
        String getDate() {
            Calendar calendar = getCalendar();

            return String.format(Locale.US,
                    "%d/%d/%d (%d/%d/%d)",
                    getYear(), getMonth(), getDay(),
                    calendar.get(Calendar.YEAR),
                    +calendar.get(Calendar.MONTH) + 1,
                    +calendar.get(Calendar.DAY_OF_MONTH));
        }
    }
}
