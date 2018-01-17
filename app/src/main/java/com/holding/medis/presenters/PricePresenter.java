package com.holding.medis.presenters;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.holding.medis.ApplicationLoader;
import com.holding.medis.R;
import com.holding.medis.contract.CharityContract;
import com.holding.medis.contract.PriceContract;
import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.enums.ViewMode;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.tools.AndroidUtilities;
import com.holding.medis.tools.CurrencyTools;
import com.holding.medis.tools.UserTools;
import com.holding.medis.tools.ValidationTools;
import com.holding.medis.views.holders.CharityRowHolder;
import com.holding.medis.views.holders.PriceRowHolder;

import org.adw.library.widgets.discreteseekbar.DiscreteSeekBar;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class PricePresenter implements PriceContract.Presenter {

    private final PriceContract.View mView;
    private ArrayList<Charity> mCharities;

    public PricePresenter(PriceContract.View view) {
        this.mView = view;
    }

    @Override
    public void setSelectedCharities(ArrayList<Charity> charities) {
        this.mCharities = charities;
        if (!ValidationTools.isEmptyOrNull(mCharities)) {
            mView.showCharities();
            mView.notifyDataSetChange();
        }
    }

    @Override
    public ArrayList<Charity> getCharities() {
        return mCharities;
    }

    @Override
    public int getCharitiesCount() {
        return (null != getCharities() ? getCharities().size() : 0);
    }

    @Override
    public PriceRowHolder createViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_charity_price, null);
        PriceRowHolder mh = new PriceRowHolder(view);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return mh;
    }


    @Override
    public void bindViewHolder(final PriceRowHolder holder, int position) {
        final Charity charity = getCharities().get(position);


        holder.txt_name.setText(charity.getName());
        holder.seek_price.setProgress(charity.getPrice());
        holder.edt_price.setText("" + charity.getPrice());

        holder.seek_price.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                holder.edt_price.setText(seekBar.getProgress() == 0 ? "" + 10000 : "" + seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(DiscreteSeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                charity.setCurrencyType(UserTools.getInstance().getCurrencyType());
            }
        });
        holder.edt_price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                charity.setPrice(ValidationTools.isEmptyOrNull(editable.toString()) ? 0 : Integer.parseInt(editable.toString()));
            }
        });
    }
}
