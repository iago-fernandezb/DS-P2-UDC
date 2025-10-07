package e2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EuroCoinCollectionTest {

    private EuroCoinCollection collection;
    private EuroCoin coin1, coin2, coin3, coin4, coin5, coin6;

    @BeforeEach
    void setUp() {
        collection = new EuroCoinCollection();
        coin1 = new EuroCoin(200, Country.ES, "Moneda 200 ESP Juan", CoinColor.GOLD, 2002);
        coin2 = new EuroCoin(100, Country.FR, "Moneda 100 FR", CoinColor.GOLD, 2003);
        coin3 = new EuroCoin(50, Country.DE, "Moneda 50 DE", CoinColor.BRONZE, 2004);
        coin4 = new EuroCoin(200, Country.IT, "Moneda 200 IT", CoinColor.GOLD, 2001);
        coin5 = new EuroCoin(50, Country.DE, "Moneda 50 DE", CoinColor.BRONZE, 2005);
        coin6 = new EuroCoin(200, Country.ES, "Moneda 200 ESP Carlos", CoinColor.GOLD, 2006);
    }

    @Test
    public void testAddCoin() {
        assertTrue(collection.addCoin(coin1));
        assertTrue(collection.addCoin(coin2));
        assertTrue(collection.addCoin(coin3));
        assertFalse(collection.addCoin(coin1));
        assertEquals(3, collection.countCoins());
    }

    @Test
    void testRemoveCoin() {
        collection.addCoin(coin1);
        assertTrue(collection.removeCoin(coin1));
        assertEquals(0, collection.countCoins());

        assertFalse(collection.removeCoin(coin1));
    }

    @Test
    void testCountCoins() {
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        assertEquals(2, collection.countCoins());
    }

    @Test
    void testTotalNominalValue() {
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        assertEquals(300, collection.totalNominalValue());
    }

    @Test
    void testSortCoins_NaturalOrder() {
        collection.addCoin(coin4);
        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(coin3);
        collection.addCoin(coin5);
        collection.addCoin(coin6);

        collection.sortCoins();

        assertEquals(coin6, collection.getCoins().get(0));
        assertEquals(coin1, collection.getCoins().get(1));
        assertEquals(coin4, collection.getCoins().get(2));
        assertEquals(coin2, collection.getCoins().get(3));
        assertEquals(coin3, collection.getCoins().get(4));
        assertEquals(coin5, collection.getCoins().get(5));
    }

    @Test
    void testSortCoins_CustomComparator() {
        collection.addCoin(coin2);
        collection.addCoin(coin1);
        collection.addCoin(coin3);
        collection.addCoin(coin4);
        collection.addCoin(coin5);
        collection.addCoin(coin6);

        collection.sortCoins(new EuroCoinComparator());

        assertEquals(coin3, collection.getCoins().get(0));
        assertEquals(coin5, collection.getCoins().get(1));
        assertEquals(coin1, collection.getCoins().get(2));
        assertEquals(coin6, collection.getCoins().get(3));
        assertEquals(coin2, collection.getCoins().get(4));
        assertEquals(coin4, collection.getCoins().get(5));
    }

    @Test
    void testEmptyCollection() {
        assertEquals(0, collection.countCoins());
        collection.sortCoins();
    }

    @Test
    void testSingleElementCollection() {
        collection.addCoin(coin1);
        collection.sortCoins();
        assertEquals(1, collection.countCoins());
        assertTrue(collection.hasCoin(coin1));
    }
}
