package com.holding.medis.components;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.holding.medis.R;
import com.holding.medis.constans.C;
import com.holding.medis.tools.AndroidUtilities;
import com.holding.medis.tools.ValidationTools;

import java.util.List;


public class SimpleSearchView extends LinearLayout {

    private ImageView imgClearSearchView;
    private ImageView imgVoiceSearch;
    private EditText search_view;
    private ListenerSimpleSearchView listenerSimpleSearchView;

    public interface ListenerSimpleSearchView {
        void onTextChange(String newText);
        void onVoiceSearchClick();
    }

    public void setListener(ListenerSimpleSearchView listener) {
        listenerSimpleSearchView = listener;
    }

    public SimpleSearchView(Context context) {
        super(context);
        build(context);
    }

    public SimpleSearchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        build(context);
    }

    public SimpleSearchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        build(context);
    }

    private void build(final Context context) {
        LayoutInflater.from(context).inflate(R.layout.simple_search_view, this);
        initViews();
        search_view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (listenerSimpleSearchView != null) {
                    listenerSimpleSearchView.onTextChange(charSequence.toString());
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        imgClearSearchView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!ValidationTools.isEmptyOrNull(search_view.getText().toString())){
                    search_view.setText("");
                }
            }
        });

        imgVoiceSearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(context == null){
                    return;
                }
                PackageManager pm = context.getPackageManager();
                List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
                if (activities.size() == 0) {
                    Toast.makeText(context, getResources().getText(R.string.recognizer_not_present), Toast.LENGTH_LONG).show();

                } else {
                    if (listenerSimpleSearchView != null) {
                        listenerSimpleSearchView.onVoiceSearchClick();
                        return;
                    }
                }
            }
        });
    }

    public void setText(String input){
        search_view.setText(input);
        invalidate();
    }


    public String getText(){
        return search_view.getText().toString();
    }


    private void initViews() {
        imgClearSearchView = (ImageView) findViewById(R.id.imgClearSearchView);
        imgVoiceSearch = (ImageView) findViewById(R.id.imgVoiceSearch);
        search_view = (EditText) findViewById(R.id.search_view);

    }
}
