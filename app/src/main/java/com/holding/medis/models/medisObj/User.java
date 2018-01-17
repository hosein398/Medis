package com.holding.medis.models.medisObj;

import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.models.enums.LanguageType;
import com.holding.medis.models.enums.ViewMode;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class User {

    private Integer id;
    private String firstName;
    private String surName;
    private String mobile;
    private LanguageType languageType = LanguageType.English;
    private ViewMode viewMode = ViewMode.List;
    private CurrencyType currencyType = CurrencyType.Toman;



    public User(Integer id, String firstName, String surName, String mobile) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.mobile = mobile;
    }

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public LanguageType getLanguageType() {
        return languageType;
    }

    public void setLanguageType(LanguageType languageType) {
        this.languageType = languageType;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
