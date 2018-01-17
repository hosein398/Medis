package com.holding.medis.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.holding.medis.R;

import java.util.ArrayList;


public class CountAdapter extends ArrayAdapter<Integer> {

    private ArrayList<Integer> mValues;
    private Context mContext;


    public CountAdapter(Context context, int resourceId, ArrayList<Integer> values) {
        super(context, resourceId,values);
        mContext = context;
        mValues = values;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View row= View.inflate(mContext, R.layout.spinner_item_list, null);
        final int count = mValues.get(position);
        TextView txtItemSpinner=(TextView)row.findViewById(R.id.txtItemSpinner);
        txtItemSpinner.setText("" + count);
        return row;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = View.inflate(mContext,R.layout.spinner_item_top, null);
        TextView txtTopSpinner=(TextView)row.findViewById(R.id.txtTopSpinner);
        txtTopSpinner.setText("" + mValues.get(position));
        return row;
    }
}
