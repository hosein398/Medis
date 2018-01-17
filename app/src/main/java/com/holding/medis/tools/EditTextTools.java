package com.holding.medis.tools;

import android.content.Context;
import android.widget.EditText;

import com.holding.medis.R;

/**
 * Created by hossein-ra on 2/1/2017.
 */

public class EditTextTools {

    public static boolean isEmptyOrNull(Context context,EditText editText) {
        String input = editText.getText().toString();
        if (ValidationTools.isEmptyOrNull(input)) {
            editText.setError(context.getResources().getText(R.string.fill_field_start));
            return true;
        }
        return false;
    }

    public static boolean isMobileValid(Context context,EditText editText, String countryCallingCode) {
        String input = editText.getText().toString();
        if (!ValidationTools.isMobileValid(input, countryCallingCode)) {
            editText.setError(context.getResources().getText(R.string.mobile_number_entered_is_not_valid));
            return false;
        }
        return true;
    }

    public static boolean isEmailValid(Context context,EditText editText) {
        String input = editText.getText().toString();
        if (!ValidationTools.isEmailValid(input)) {
            editText.setError(context.getResources().getText(R.string.email_entered_is_not_valid));
            return false;
        }
        return true;
    }

    public static boolean isEnglishText(EditText editText) {
        String input = editText.getText().toString();
        if (!ValidationTools.isEnglish(input)) {
            editText.setError("لطفا اطلاعات خود را به لاتین وارد نمایید .");
            return false;
        }
        return true;
    }
}
