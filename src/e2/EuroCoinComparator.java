package e2;

import java.util.Comparator;

public class EuroCoinComparator implements Comparator<EuroCoin> {
    @Override
    public int compare(EuroCoin coin1, EuroCoin coin2) {
        int countryComparison = coin1.getCountry().compareTo(coin2.getCountry());
        if (countryComparison != 0) return countryComparison;

        int valueComparison = Integer.compare(coin2.getValue(), coin1.getValue());
        if (valueComparison != 0) return valueComparison;

        return Integer.compare(coin1.getYear(), coin2.getYear());
    }
}
