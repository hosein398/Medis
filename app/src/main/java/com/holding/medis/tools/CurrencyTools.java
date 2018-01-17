package com.holding.medis.tools;

import com.holding.medis.models.enums.CurrencyType;

/**
 * Created by hoseinraeisi on 9/13/17.
 */

public class CurrencyTools {

    public static int convert(int amount, CurrencyType from, CurrencyType to) {
        switch (from) {

            case Toman:
                return (int) (amount * getRateFromToman(to));
            case Rial:
                return (int) (amount * getRateFromRial(to));
            case Dollar:
                return (int) (amount * getRateFromDollar(to));
            default:
                return (int) (amount * getRateFromToman(to));

        }
    }

    private static float getRateFromToman(CurrencyType to) {
        switch (to) {

            case Toman:
                return 1;
            case Rial:
                return 10;
            case Dollar:
                return 1 / 3000.0f;
            default:
                return 1;
        }
    }

    private static float getRateFromRial(CurrencyType to) {
        switch (to) {

            case Toman:
                return 1 / 10.0f;
            case Rial:
                return 1;
            case Dollar:
                return 1 / 30000.0f;
            default:
                return 1 / 10.0f;
        }
    }

    private static float getRateFromDollar(CurrencyType to) {
        switch (to) {

            case Toman:
                return 3000;
            case Rial:
                return 30000;
            case Dollar:
                return 1;
            default:
                return 3000;
        }
    }
}
