package com.holding.medis.models.enums;

import com.holding.medis.ApplicationLoader;
import com.holding.medis.R;

/**
 * Created by hosein on 3/16/2017.
 */

public enum CurrencyType {

    Toman(0),
    Rial(1),
    Dollar(2);


    public static final int SIZE = Integer.SIZE;

    private int intValue;
    private static java.util.HashMap<Integer, CurrencyType> mappings;
    private static java.util.HashMap<Integer, CurrencyType> getMappings()
    {
        if (mappings == null)
        {
            synchronized (CurrencyType.class)
            {
                if (mappings == null)
                {
                    mappings = new java.util.HashMap<Integer, CurrencyType>();
                }
            }
        }
        return mappings;
    }

    CurrencyType(int value)
    {
        intValue = value;
        getMappings().put(value, this);
    }

    public int getValue()
    {
        return intValue;
    }

    public static CurrencyType forValue(int value)
    {
        return getMappings().get(value);
    }

    @Override
    public String toString() {
        switch (this){

            case Toman:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.toman);
            case Rial:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.rial);
            case Dollar:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.dollar);
            default:
                return (String) ApplicationLoader.applicationContext.getResources().getText(R.string.toman);
        }
    }
}
