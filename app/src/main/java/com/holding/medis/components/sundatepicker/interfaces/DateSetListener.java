package com.holding.medis.components.sundatepicker.interfaces;

import android.support.annotation.Nullable;

import java.util.Calendar;


public interface DateSetListener {
    void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year);
}
