package com.holding.medis.models.enums;

/**
 * Created by hosein on 2/17/2017.
 */

public enum FontType {

    IRANSansMobile(0),
    IRANSansMobile_Bold(1),
    IRANSansMobile_Light(2),
    IRANSansMobile_Medium(3),
    IRANSansMobile_UlteraLight(4),
    Icomoon(5);

    public static final int SIZE = Integer.SIZE;

    private int intValue;
    private static java.util.HashMap<Integer, FontType> mappings;
    private static java.util.HashMap<Integer, FontType> getMappings()
    {
        if (mappings == null)
        {
            synchronized (FontType.class)
            {
                if (mappings == null)
                {
                    mappings = new java.util.HashMap<Integer, FontType>();
                }
            }
        }
        return mappings;
    }

     FontType(int value)
    {
        intValue = value;
        getMappings().put(value, this);
    }

    public int getValue()
    {
        return intValue;
    }

    public static FontType forValue(int value)
    {
        return getMappings().get(value);
    }

    @Override
    public String toString() {
        switch (this){

            case IRANSansMobile:
                return "IRANSansMobile";
            case IRANSansMobile_Bold:
                return "IRANSansMobile_Bold";
            case IRANSansMobile_Light:
                return "IRANSansMobile_Light";
            case IRANSansMobile_Medium:
                return "IRANSansMobile_Medium";
            case IRANSansMobile_UlteraLight:
                return "IRANSansMobile_UlteraLight";
            case Icomoon:
                return "icomoon";
            default:
                return "IRANSansMobile";
        }
    }
}
