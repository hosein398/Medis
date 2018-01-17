package com.holding.medis.views.holders;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.holding.medis.ApplicationLoader;
import com.holding.medis.R;
import com.holding.medis.components.smoothcheckbox.SmoothCheckBox;
import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.tools.CurrencyTools;
import com.holding.medis.tools.UserTools;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

/**
 * Created by hosein on 2/12/2017.
 */

public class PriceRowHolder extends RecyclerView.ViewHolder {

    public TextView txt_name;
    public ViewGroup layout_charity;
    public DiscreteSeekBar seek_price;
    public EditText edt_price;
    public TextView txt_currency;

    public PriceRowHolder(View view) {
        super(view);
        this.layout_charity = (ViewGroup) view.findViewById(R.id.layout_charity);
        this.txt_name = (TextView) view.findViewById(R.id.txt_name);
        this.seek_price = (DiscreteSeekBar) view.findViewById(R.id.seek_price);
        this.edt_price = (EditText) view.findViewById(R.id.edt_price);
        this.txt_currency = (TextView) view.findViewById(R.id.txt_currency);

        txt_currency.setText(
                ApplicationLoader.applicationContext.getResources().getText(R.string.amount)
                        + " ("
                        + UserTools.getInstance().getCurrencyType().toString()
                        + " ) : ");

        seek_price.setMax(CurrencyTools.convert(100000, CurrencyType.Toman, UserTools.getInstance().getCurrencyType()));
        seek_price.setMin(CurrencyTools.convert(10000, CurrencyType.Toman, UserTools.getInstance().getCurrencyType()));
    }
}
