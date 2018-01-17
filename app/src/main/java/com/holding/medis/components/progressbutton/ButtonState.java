package com.holding.medis.components.progressbutton;

/**
 * Created by hosein on 2/17/2017.
 */

public enum ButtonState {

    Connecting(1),
    Cancel(2),
    Pause(3),
    Start(4),
    Progress(5),
    Error(6),
    Complete(7);

    public static final int SIZE = Integer.SIZE;

    private int intValue;
    private static java.util.HashMap<Integer, ButtonState> mappings;
    private static java.util.HashMap<Integer, ButtonState> getMappings()
    {
        if (mappings == null)
        {
            synchronized (ButtonState.class)
            {
                if (mappings == null)
                {
                    mappings = new java.util.HashMap<Integer, ButtonState>();
                }
            }
        }
        return mappings;
    }

    private ButtonState(int value)
    {
        intValue = value;
        getMappings().put(value, this);
    }

    public int getValue()
    {
        return intValue;
    }

    public static ButtonState forValue(int value)
    {
        return getMappings().get(value);
    }

}
