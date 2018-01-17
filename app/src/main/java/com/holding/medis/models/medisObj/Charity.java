package com.holding.medis.models.medisObj;

import com.holding.medis.models.enums.CurrencyType;
import com.holding.medis.tools.CurrencyTools;
import com.holding.medis.tools.UserTools;

/**
 * Created by hoseinraeisi on 9/12/17.
 */

public class Charity {

    private int id;
    private String name;
    private String topic;
    private String address;
    private String phone;
    private int icon;
    private boolean isSelected;
    private int price;
    private CurrencyType currencyType = CurrencyType.Toman;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
        price = 0 ;
    }

    public int getPrice() {
        return price == 0 ? CurrencyTools.convert(10000,getCurrencyType(), UserTools.getInstance().getCurrencyType()) : CurrencyTools.convert(price,getCurrencyType(), UserTools.getInstance().getCurrencyType());
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}
