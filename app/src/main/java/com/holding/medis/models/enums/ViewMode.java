package com.holding.medis.models.enums;

/**
 * Created by hosein on 3/16/2017.
 */

public enum ViewMode {

    Grid(3),
    List(1);


    public static final int SIZE = Integer.SIZE;

    private int intValue;
    private static java.util.HashMap<Integer, ViewMode> mappings;
    private static java.util.HashMap<Integer, ViewMode> getMappings()
    {
        if (mappings == null)
        {
            synchronized (ViewMode.class)
            {
                if (mappings == null)
                {
                    mappings = new java.util.HashMap<Integer, ViewMode>();
                }
            }
        }
        return mappings;
    }

    private ViewMode(int value)
    {
        intValue = value;
        getMappings().put(value, this);
    }

    public int getValue()
    {
        return intValue;
    }

    public static ViewMode forValue(int value)
    {
        return getMappings().get(value);
    }

}
