package com.holding.medis;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Build;
import android.util.Log;

import com.holding.medis.models.enums.LanguageType;

import java.util.Locale;


public class UserConfig {

    private static UserConfig instance;

    private UserConfig() {
    }

    public static UserConfig getInstance() {

        if (instance == null) {
            instance = new UserConfig();
        }
        return instance;
    }

    public void init(Activity activity, LanguageType languageType) {
        String languageToLoad = LanguageType.getStringCode(languageType);
        try {
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLocale(locale);
                config.setLayoutDirection(locale);
            } else {
                config.locale = locale;
            }
            activity.getBaseContext().getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());
        } catch (NullPointerException e) {
            Log.i("LOG", "Exception = " + e);
        }
    }
}
