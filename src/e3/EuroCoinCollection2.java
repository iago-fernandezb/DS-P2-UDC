package e3;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class EuroCoinCollection2 implements Iterable<EuroCoin2> {

    private final ArrayList<EuroCoin2> coins;
    private int modCount = 0;

    public EuroCoinCollection2() {
        this.coins = new ArrayList<>();
    }

    public boolean addCoin(EuroCoin2 coin) {
        if (coin != null && coin.valueInCents() > 0
                && coin.isValidCountry(coin.getCountry())
                && coin.isValidColor(coin.getColor())) {
            if (!coins.contains(coin)) {
                coins.add(coin);
                modCount++;
                return true;
            }
        }
        return false;
    }

    public boolean removeCoin(EuroCoin2 coin) {
        if (coins.remove(coin)) {
            modCount++;
            return true;
        }
        return false;
    }

    public int countCoins() {
        return coins.size();
    }

    public int totalNominalValue() {
        int totalValue = 0;
        for (EuroCoin2 coin : coins) {
            totalValue += coin.getValue();
        }
        return totalValue;
    }

    public boolean hasCoin(EuroCoin2 coin) {
        return coins.contains(coin);
    }

    @Override
    public Iterator<EuroCoin2> iterator() {
        return new EuroCoinIterator(null);
    }

    public Iterator<EuroCoin2> iterator(Country country) {
        return new EuroCoinIterator(country);
    }

    private class EuroCoinIterator implements Iterator<EuroCoin2> {
        private final Country countryFilter;
        private int currentIndex = 0;
        private int lastReturnedIndex = -1;
        private int expectedModCount;
        private boolean canRemove = false;

        public EuroCoinIterator(Country country) {
            this.countryFilter = country;
            this.expectedModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            checkForConcurrentModification();
            while (currentIndex < coins.size()) {
                if (countryFilter == null || coins.get(currentIndex).getCountry() == countryFilter) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public EuroCoin2 next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            lastReturnedIndex = currentIndex;
            currentIndex++;
            canRemove = true;
            return coins.get(lastReturnedIndex);
        }

        @Override
        public void remove() {
            checkForConcurrentModification();
            if (!canRemove) {
                throw new IllegalStateException("No se puede eliminar antes de llamar a next()");
            }
            coins.remove(lastReturnedIndex);
            modCount++;
            expectedModCount = modCount;
            currentIndex = lastReturnedIndex;
            canRemove = false;
        }

        private void checkForConcurrentModification() {
            if (expectedModCount != modCount) {
                throw new ConcurrentModificationException("La colección fue modificada durante la iteración");
            }
        }
    }

    public void clear() {
        coins.clear();
        modCount++;
    }
}
