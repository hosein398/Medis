package com.holding.medis.views.dialogs;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.holding.medis.R;
import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.enums.LanguageType;
import com.holding.medis.tools.UserTools;


public class CurrencyBottomSheedDialog {

    private ListenerCurrencyBottomSheedDialog listenerCurrencyBottomSheedDialog;
    private Context context;

    public CurrencyBottomSheedDialog(Context context){
       this.context = context;
    }

    public  interface ListenerCurrencyBottomSheedDialog{
         void onClick(BottomSheetDialog bottomSheetDialog, CurrencyType currencyType);
    }

    public CurrencyBottomSheedDialog setListener(ListenerCurrencyBottomSheedDialog value) {
        listenerCurrencyBottomSheedDialog = value;
        return this;
    }
    public void show(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.bottom_sheet_currency, null);
        bottomSheetDialog.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, context.getResources().getDisplayMetrics()));
        bottomSheetDialog.show();
        Button btn_toman = (Button) view.findViewById(R.id.btn_toman);
        Button btn_dollar = (Button) view.findViewById(R.id.btn_dollar);
        Button btn_rial = (Button) view.findViewById(R.id.btn_rial);
        btn_toman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                UserTools.getInstance().setCurrencyType(CurrencyType.Toman);
                if(listenerCurrencyBottomSheedDialog != null){
                    listenerCurrencyBottomSheedDialog.onClick(bottomSheetDialog,CurrencyType.Toman);
                    return;
                }

            }
        });
        btn_dollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                UserTools.getInstance().setCurrencyType(CurrencyType.Dollar);
                if(listenerCurrencyBottomSheedDialog != null){
                    listenerCurrencyBottomSheedDialog.onClick(bottomSheetDialog,CurrencyType.Dollar);
                    return;
                }
            }
        });

        btn_rial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                UserTools.getInstance().setCurrencyType(CurrencyType.Rial);
                if(listenerCurrencyBottomSheedDialog != null){
                    listenerCurrencyBottomSheedDialog.onClick(bottomSheetDialog,CurrencyType.Rial);
                    return;
                }
            }
        });

    }
}
