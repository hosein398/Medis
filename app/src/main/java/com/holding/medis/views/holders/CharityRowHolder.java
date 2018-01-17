package com.holding.medis.views.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.holding.medis.R;
import com.holding.medis.components.smoothcheckbox.SmoothCheckBox;

/**
 * Created by hosein on 2/12/2017.
 */

public class CharityRowHolder extends RecyclerView.ViewHolder {

    public TextView txt_name;
    public TextView txt_topic;
    public TextView txt_address;
    public ViewGroup layout_charity;
    public SmoothCheckBox smooth_checkbox;

    public CharityRowHolder(View view) {
        super(view);
        this.layout_charity = (ViewGroup) view.findViewById(R.id.layout_charity);
        this.txt_name = (TextView) view.findViewById(R.id.txt_name);
        this.txt_topic = (TextView) view.findViewById(R.id.txt_topic);
        this.txt_address = (TextView) view.findViewById(R.id.txt_address);
        this.smooth_checkbox = (SmoothCheckBox) view.findViewById(R.id.smooth_checkbox);
    }
}
