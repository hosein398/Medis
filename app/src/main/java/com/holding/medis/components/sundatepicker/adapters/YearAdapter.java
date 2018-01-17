package com.holding.medis.components.sundatepicker.adapters;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.holding.medis.R;
import com.holding.medis.components.sundatepicker.components.JDF;
import com.holding.medis.components.sundatepicker.components.SquareTextView;
import com.holding.medis.components.sundatepicker.interfaces.DateInterface;


public class YearAdapter extends RecyclerView.Adapter<YearAdapter.ViewHolder> {
    private int[] mYears;
    private int mCurrentYear;
    private DateInterface mCallback;

    public YearAdapter(DateInterface callback, int[] years) {
        mYears = years;
        mCallback = callback;
        mCurrentYear = new JDF().getIranianYear();
    }

    private boolean isSelected(int year) {
        return mYears[year] == mCallback.getYear();
    }

    private boolean isThisYear(int year) {
        return mYears[year] == mCurrentYear;
    }

    public int getSelectedYear() {
        for (int i = 0; i < getItemCount(); i++) {
            if (isSelected(i))
                return i;
        }
        return 0;
    }

    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_year, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(String.valueOf(mYears[position]));
        holder.mTextView.setSelected(isSelected(position));
        holder.mTextView.setChecked(isThisYear(position));
    }

    @Override
    public int getItemCount() {
        return mYears.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private SquareTextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (SquareTextView) itemView.findViewById(R.id.text);
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mCallback != null) {
                mCallback.setYear(mYears[getLayoutPosition()]);
                notifyDataSetChanged();
            }
        }
    }
}