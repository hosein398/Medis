package com.holding.medis.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.holding.medis.R;
import com.holding.medis.models.enums.FontType;
import com.holding.medis.tools.AndroidUtilities;
import com.holding.medis.tools.ValidationTools;

/**
 * Created by hoseinraeisi on 9/11/17.
 */

public class ButtonCell extends AppCompatButton {

    private String fontName;
    private FontType fontType;

    public ButtonCell(Context context) {
        super(context);
        init(context,null);
    }

    public ButtonCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ButtonCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttributes(context,attrs);
        if(!ValidationTools.isEmptyOrNull(fontName)){
            this.setTypeface(AndroidUtilities.getTypeface("fonts/" + fontName + ".ttf"));
        }

    }

    private void initAttributes(Context context, AttributeSet attributeSet) {
        TypedArray attr = getTypedArray(context, attributeSet, R.styleable.ViewCell);
        if (attr == null) {
            return;
        }
        try {
            fontType = FontType.forValue(attr.getInteger(R.styleable.ViewCell_vc_font,0));
            fontName = fontType.toString();
        } finally {
            attr.recycle();
        }
    }

    protected int getColor(int id) {
        return getResources().getColor(id);
    }

    protected TypedArray getTypedArray(Context context, AttributeSet attributeSet, int[] attr) {
        return context.obtainStyledAttributes(attributeSet, attr, 0, 0);
    }
}
