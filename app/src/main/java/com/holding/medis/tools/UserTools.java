package com.holding.medis.tools;


import android.content.Intent;

import com.holding.medis.constans.C;
import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.enums.LanguageType;
import com.holding.medis.models.enums.ViewMode;
import com.holding.medis.models.medisObj.User;

/**
 * Created by hosein on 2/11/2017.
 */

public class UserTools {

    private static UserTools instance;
    private static User user;
    public static UserTools getInstance() {
        if(instance == null){
            instance = new UserTools();
        }
        if(Prefs.getObject(C.USERPREF,User.class) == null){
            user = new User();
            Prefs.putObject(C.USERPREF,user);
        }else{
            user = Prefs.getObject(C.USERPREF,User.class);
        }
        return instance;
    }

    private UserTools() {
    }
    public final User getUser()
    {
        return user;
    }
    public final void setUser(User value)
    {
        Prefs.putObject(C.USERPREF,value);
    }


    public final Integer getId()
    {
        return user.getId();
    }
    public final void setId(Integer value)
    {
        user.setId(value);
        Prefs.putObject(C.USERPREF,user);
    }

    public final String getFirstName()
    {
        return user.getFirstName();
    }
    public final void setFirstName(String value)
    {
        user.setFirstName(value);
        Prefs.putObject(C.USERPREF,user);
    }

    public final String getMobile()
    {
        return user.getMobile();
    }
    public final void setMobile(String value)
    {
        user.setMobile(value);
        Prefs.putObject(C.USERPREF,user);
    }

    public final LanguageType getLanguageType()
    {
       return user.getLanguageType();
    }
    public final void setLanguageType(LanguageType value)
    {
        user.setLanguageType(value);
        Prefs.putObject(C.USERPREF,user);
    }

    public final ViewMode getViewMode()
    {
        return user.getViewMode();
    }
    public final void setViewMode(ViewMode value)
    {
        user.setViewMode(value);
        Prefs.putObject(C.USERPREF,user);
    }

    public final CurrencyType getCurrencyType()
    {
        return user.getCurrencyType();
    }
    public final void setCurrencyType(CurrencyType value)
    {
        user.setCurrencyType(value);
        Prefs.putObject(C.USERPREF,user);
    }
}
