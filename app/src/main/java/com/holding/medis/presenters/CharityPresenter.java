package com.holding.medis.presenters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.holding.medis.R;
import com.holding.medis.contract.CharityContract;
import com.holding.medis.contract.HomeContract;
import com.holding.medis.models.enums.ViewMode;
import com.holding.medis.models.medisObj.Charity;
import com.holding.medis.tools.AndroidUtilities;
import com.holding.medis.tools.UserTools;
import com.holding.medis.tools.ValidationTools;
import com.holding.medis.views.holders.CharityRowHolder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class CharityPresenter implements CharityContract.Presenter {

    private final CharityContract.View mView;
    private ArrayList<Charity> charities;
    private ArrayList<Charity> filterCharities;
    final Gson gson = new GsonBuilder().create();

    public CharityPresenter(CharityContract.View view) {
        this.mView = view;
        mView.showSpinnerItems(0, 1);
    }

    @Override
    public void loadCharities() {
        mView.showLoading();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mView == null || mView.getAppContext() == null){
                    return;
                }
                mView.hideLoading();
                ArrayList<Charity> charityArrayList = gson.fromJson(loadJson(), new TypeToken<List<Charity>>() {
                }.getType());
                CharityPresenter.this.charities = charityArrayList;
                CharityPresenter.this.filterCharities = charityArrayList;
                mView.showCharities();
                mView.showSpinnerItems(0, getCharitiesCount());
            }
        }, 1500);
    }

    @Override
    public ArrayList<Charity> getSelectedCharities() {
        ArrayList<Charity> selectedCharities = new ArrayList<>();
        if (ValidationTools.isEmptyOrNull(charities)) {
            return selectedCharities;
        }
        for(Charity charity : charities){
            if(charity.isSelected()){
                selectedCharities.add(charity);
            }
        }
        return selectedCharities;
    }

    @Override
    public void filter(String filter) {
        if (ValidationTools.isEmptyOrNull(filter)) {
            filterCharities = new ArrayList<>(charities);
        } else {
            filterCharities = new ArrayList<>();
            for (int i = 0; i < charities.size(); i++) {
                String name = ValidationTools.isEmptyOrNull(charities.get(i).getName()) ? "" : charities.get(i).getName();
                String address = ValidationTools.isEmptyOrNull(charities.get(i).getAddress()) ? "" : charities.get(i).getAddress();
                String topic = ValidationTools.isEmptyOrNull(charities.get(i).getTopic()) ? "" : charities.get(i).getTopic();
                String phone = ValidationTools.isEmptyOrNull(charities.get(i).getPhone()) ? "" : charities.get(i).getPhone();

                if (
                        name.toLowerCase().contains(filter.toLowerCase())
                                || address.toLowerCase().contains(filter.toLowerCase())
                                || topic.toLowerCase().contains(filter.toLowerCase())
                                || phone.toLowerCase().contains(filter.toLowerCase())

                        ) {
                    filterCharities.add(charities.get(i));
                }
            }
        }
        mView.notifyDataSetChange();
    }

    @Override
    public void selectAllCharity(boolean isSelect) {
        if (ValidationTools.isEmptyOrNull(getCharities())) {
            return;
        }

        for (Charity charity : getCharities()) {
            charity.setSelected(isSelect);
        }
        mView.notifyDataSetChange();
    }

    @Override
    public void selectRandomCharity(int value) {
        if (ValidationTools.isEmptyOrNull(getCharities())) {
            return;
        }
        if(getCharitiesCount() < value){
            value = getCharitiesCount() - 1;
        }
        for (Charity charity : getCharities()) {
            charity.setSelected(false);
        }
        ArrayList<Integer> randoms = new ArrayList<>();
        while (randoms.size() <= value) {
            int random = AndroidUtilities.nDigitRandomNo(0, getCharitiesCount());
            if (!randoms.contains(random)) {
                randoms.add(random);
            }
        }
        for (int i = 0; i < value; i++) {
            getCharities().get(randoms.get(i)).setSelected(true);
        }
        mView.notifyDataSetChange();
    }

    @Override
    public ArrayList<Charity> getCharities() {
        return filterCharities;
    }

    @Override
    public int getCharitiesCount() {
        return (null != getCharities() ? getCharities().size() : 0);
    }

    @Override
    public CharityRowHolder createViewHolder(ViewGroup parent, int viewType) {
        View view;
        CharityRowHolder mh;
        if (UserTools.getInstance().getViewMode() == ViewMode.Grid) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_charity_grid, null);
            mh = new CharityRowHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_charity_list, null);
            mh = new CharityRowHolder(view);
            RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(lp);
        }
        return mh;
    }


    @Override
    public void bindViewHolder(final CharityRowHolder holder, int position) {
        final Charity charity = getCharities().get(position);
        holder.txt_name.setText(charity.getName());
        holder.txt_topic.setText(charity.getTopic());
        holder.txt_address.setText(charity.getAddress());
        holder.smooth_checkbox.setChecked(charity.isSelected());
        holder.smooth_checkbox.setVisibility(charity.isSelected() ? View.VISIBLE : View.GONE);
        holder.layout_charity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mView.onClickCharityItem(charity);
                if (charity.isSelected()) {
                    charity.setSelected(false);
                    holder.smooth_checkbox.setChecked(false);
                    holder.smooth_checkbox.setVisibility(View.GONE);
                    return;
                }
                charity.setSelected(true);
                holder.smooth_checkbox.setVisibility(View.VISIBLE);
                holder.smooth_checkbox.setChecked(true, true);
            }
        });
    }

    private String loadJson() {
        String json = null;
        try {
            InputStream is = mView.getAppContext().getAssets().open("charity_json.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
