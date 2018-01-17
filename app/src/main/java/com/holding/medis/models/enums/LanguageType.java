package com.holding.medis.models.enums;


import com.holding.medis.ApplicationLoader;
import com.holding.medis.R;
import com.holding.medis.tools.ValidationTools;

/**
 * Created by hosein on 2/11/2017.
 */

public enum LanguageType {
    Persian,
    English,
    French,
    German,
    Arabic;

    public int getValue()
    {
        return this.ordinal();
    }

    public static LanguageType forValue(int value)
    {
        return values()[value];
    }

    public static LanguageType forValue(String code)
    {
        if(ValidationTools.isEmptyOrNull(code)){
            return LanguageType.Persian;
        }
        switch (code){
            case "en":
                return LanguageType.English;
            case "fa":
                return LanguageType.Persian;
            case "fr":
                return LanguageType.French;
            case "de":
                return LanguageType.German;
            case "ar":
                return LanguageType.Arabic;
            default:
                return LanguageType.Persian;
        }
    }

    public static String  getStringCode(LanguageType languageType){
        switch(languageType){
            case Persian:
                return "fa";
            case English:
                return "en";
            case French:
                return "fr";
            case German:
                return "de";
            case Arabic:
                return "ar";
            default:
                return "fa";
        }
    }

    public  static String getString(LanguageType languageType){
        switch (languageType){
            case English:
                return "English";
            case Persian:
                return "فارسی";
            default:
                return "فارسی";
        }
    }

    @Override
    public String toString() {
        if(this == null){
            return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.Persian);
        }
        switch (this){
            case English:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.English);
            case Persian:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.Persian);
            case French:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.French);
            case German:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.German);
            case Arabic:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.Arabic);
            default:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.Persian);
        }
    }
}
