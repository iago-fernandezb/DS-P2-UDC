package e2;

import java.util.Objects;

public record EuroCoin(int valueInCents, Country country, String design, CoinColor color, int year) {

    public EuroCoin(int valueInCents,Country country,String design, CoinColor color, int year){
        this.valueInCents = valueInCents;
        this.color = color;
        this.country = country;
        this.design = design;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EuroCoin euroCoin = (EuroCoin) o;
        return valueInCents == euroCoin.valueInCents &&
                color == euroCoin.color &&
                country == euroCoin.country &&
                year == euroCoin.year &&
                Objects.equals(design, euroCoin.design);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueInCents, country, design, color, year);
    }

    public int getValue() {
        return valueInCents;
    }

    public Country getCountry() {
        return country;
    }

    public String getDesign() {
        return design;
    }

    public CoinColor getColor() {
        return color;
    }

    public int getYear() {
        return year;
    }

    public boolean isValidCountry(Country country) {
        if (country == null) {
            return false;
        }
        for (Country validCountry : Country.values()) {
            if (validCountry == country) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidColor(CoinColor color) {
        if (color == null) {
            return false;
        }
        for (CoinColor validColor : CoinColor.values()) {
            if (validColor == color) {
                return true;
            }
        }
        return false;
    }


}
