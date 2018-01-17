package com.holding.medis.views.dialogs;

import android.content.Context;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.holding.medis.R;
import com.holding.medis.models.enums.LanguageType;
import com.holding.medis.tools.UserTools;


public class LanguageBottomSheedDialog {

    private ListenerLanguageBottomSheedDialog listenerLanguageBottomSheedDialog;
    private Context context;

    public LanguageBottomSheedDialog(Context context){
       this.context = context;
    }

    public  interface ListenerLanguageBottomSheedDialog{
         void onClick(BottomSheetDialog bottomSheetDialog, LanguageType languageType);
    }

    public LanguageBottomSheedDialog setListener(ListenerLanguageBottomSheedDialog value) {
        listenerLanguageBottomSheedDialog = value;
        return this;
    }
    public void show(){
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.bottom_sheet_language, null);
        bottomSheetDialog.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 500, context.getResources().getDisplayMetrics()));
        bottomSheetDialog.show();
        Button btn_english = (Button) view.findViewById(R.id.btn_english);
        Button btn_persian = (Button) view.findViewById(R.id.btn_persian);
        btn_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                UserTools.getInstance().setLanguageType(LanguageType.English);
                if(listenerLanguageBottomSheedDialog != null){
                    listenerLanguageBottomSheedDialog.onClick(bottomSheetDialog,LanguageType.English);
                    return;
                }

            }
        });
        btn_persian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                UserTools.getInstance().setLanguageType(LanguageType.Persian);
                if(listenerLanguageBottomSheedDialog != null){
                    listenerLanguageBottomSheedDialog.onClick(bottomSheetDialog,LanguageType.Persian);
                    return;
                }
            }
        });

    }
}
