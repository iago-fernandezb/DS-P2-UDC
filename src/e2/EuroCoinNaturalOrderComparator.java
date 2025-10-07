package e2;

import java.util.Comparator;

public class EuroCoinNaturalOrderComparator {
    public static final Comparator<EuroCoin> NATURAL_ORDER = (coin1, coin2) -> {
        int valueComparison = Integer.compare(coin2.valueInCents(), coin1.valueInCents());
        if (valueComparison != 0) return valueComparison;

        int countryComparison = coin1.country().compareTo(coin2.country());
        if (countryComparison != 0) return countryComparison;

        return coin1.design().compareTo(coin2.design());
    };
}
