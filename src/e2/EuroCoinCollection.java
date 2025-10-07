package e2;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EuroCoinCollection {

    private ArrayList<EuroCoin> coins;

    public EuroCoinCollection() {
        this.coins = new ArrayList<>();
    }


    public boolean addCoin(EuroCoin coin) {
        if (coin.valueInCents()>0 && coin != null && coin.isValidCountry(coin.getCountry()) && coin.isValidColor(coin.getColor())) {
            if (!coins.contains(coin)) {
                coins.add(coin);
                return true;
            }
            return false;
        }
        else return false;
    }

    public boolean removeCoin(EuroCoin coin) {
        return coins.remove(coin);
    }

    public int countCoins() {
        return coins.size();
    }

    public int totalNominalValue() {
        int totalValue = 0;
        for (EuroCoin coin : coins) {
            totalValue += coin.getValue();
        }
        return totalValue;
    }

    public boolean hasCoin(EuroCoin coin) {
        return coins.contains(coin);
    }

    public void sortCoins() {
        coins.sort(EuroCoinNaturalOrderComparator.NATURAL_ORDER);
    }

    // Ordena usando un Comparator personalizado
    public void sortCoins(Comparator<EuroCoin> comparator) {
        coins.sort(comparator);
    }

    // Metodo para obtener una vista de la lista de monedas (para uso en tests)
    public ArrayList<EuroCoin> getCoins() {
        return this.coins;
    }
}