package e3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class EuroCoinCollection2Test {

    private EuroCoinCollection2 collection;
    private EuroCoin2 coin1, coin2, coin3, coin4, coin5, coin6;

    @BeforeEach
    void setUp() {
        collection = new EuroCoinCollection2();

        coin1 = new EuroCoin2(200, Country.ES, "Moneda de 2€", CoinColor.GOLD, 2002);
        coin2 = new EuroCoin2(100, Country.FR, "Moneda de 1€", CoinColor.GOLD, 2001);
        coin3 = new EuroCoin2(50, Country.ES, "Moneda de 50c", CoinColor.BRONZE, 2003);
        coin4 = new EuroCoin2(10, Country.DE, "Moneda de 10c", CoinColor.BRONZE, 2004);
        coin5 = new EuroCoin2(5, Country.IT, "Moneda de 5c", CoinColor.BRONZE, 2005);
        coin6 = new EuroCoin2(200, Country.ES, "Moneda Especial de 2€", CoinColor.GOLD, 2006);

        collection.addCoin(coin1);
        collection.addCoin(coin2);
        collection.addCoin(coin3);
        collection.addCoin(coin4);
        collection.addCoin(coin5);
        collection.addCoin(coin6);
    }

    @Test
    void testIterateAllCoins() {
        int count = 0;
        for (EuroCoin2 coin : collection) {
            assertNotNull(coin);
            assertTrue(collection.hasCoin(coin));
            count++;
        }
        assertEquals(6, count);
    }

    @Test
    void testIterateCoinsByCountry() {
        Iterator<EuroCoin2> spanishCoins = collection.iterator(Country.ES);
        int count = 0;
        while (spanishCoins.hasNext()) {
            EuroCoin2 coin = spanishCoins.next();
            assertEquals(Country.ES, coin.getCountry());
            count++;
        }
        assertEquals(3, count);
    }

    @Test
    void testIterateCoinsByNonExistentCountry() {
        Iterator<EuroCoin2> greekCoins = collection.iterator(Country.GR);
        assertFalse(greekCoins.hasNext());
        assertThrows(NoSuchElementException.class, greekCoins::next);
    }

    @Test
    void testIteratorNoSuchElementException() {
        Iterator<EuroCoin2> frenchCoins = collection.iterator(Country.FR);
        assertTrue(frenchCoins.hasNext());
        assertEquals(coin2, frenchCoins.next());
        assertFalse(frenchCoins.hasNext());
        assertThrows(NoSuchElementException.class, frenchCoins::next);
    }

    @Test
    void testIteratorRemove() {
        Iterator<EuroCoin2> allCoins = collection.iterator();
        EuroCoin2 firstCoin = allCoins.next();
        allCoins.remove();

        assertEquals(5, collection.countCoins());
        assertFalse(collection.hasCoin(firstCoin));

        assertThrows(IllegalStateException.class, allCoins::remove);

        EuroCoin2 secondCoin = allCoins.next();
        allCoins.remove();
        assertEquals(4, collection.countCoins());
        assertFalse(collection.hasCoin(secondCoin));
    }

    @Test
    void testRemoveAllCoinsUsingIterator() {
        Iterator<EuroCoin2> allCoins = collection.iterator();
        while (allCoins.hasNext()) {
            allCoins.next();
            allCoins.remove();
        }
        assertEquals(0, collection.countCoins());
    }

    @Test
    void testFailFastOnAdd() {
        Iterator<EuroCoin2> allCoins = collection.iterator();
        collection.addCoin(new EuroCoin2(5, Country.NL, "Moneda de 5c", CoinColor.BRONZE, 2005));
        assertThrows(ConcurrentModificationException.class, allCoins::next);
    }

    @Test
    void testFailFastOnRemoveExternal() {
        Iterator<EuroCoin2> allCoins = collection.iterator();
        allCoins.next();
        collection.removeCoin(coin2);
        assertThrows(ConcurrentModificationException.class, allCoins::next);
    }

    @Test
    void testFailFastOnClear() {
        Iterator<EuroCoin2> allCoins = collection.iterator();
        allCoins.next();
        collection.clear();
        assertThrows(ConcurrentModificationException.class, allCoins::next);
    }

    @Test
    void testEqualityAndHashCode() {
        EuroCoin2 similarCoin = new EuroCoin2(200, Country.ES, "Moneda de 2€", CoinColor.GOLD, 2002);
        assertEquals(coin1, similarCoin);
        assertEquals(coin1.hashCode(), similarCoin.hashCode());

        EuroCoin2 differentCoin = new EuroCoin2(200, Country.ES, "Moneda de 2€", CoinColor.GOLD, 2003);
        assertNotEquals(coin1, differentCoin);
        assertNotEquals(coin1.hashCode(), differentCoin.hashCode());
    }
    @Test
    void testAddInvalidCoin() {
        EuroCoin2 invalidCoin = new EuroCoin2(0, Country.ES, "Moneda inválida", CoinColor.BRONZE, 2000);
        assertFalse(collection.addCoin(invalidCoin));
        assertEquals(6, collection.countCoins());

    }


    @Test
    void testAddDuplicateCoin() {
        EuroCoin2 duplicateCoin = new EuroCoin2(200, Country.ES, "Moneda de 2€", CoinColor.GOLD, 2002);
        assertFalse(collection.addCoin(duplicateCoin));
        assertEquals(6, collection.countCoins());
    }

    @Test
    void testAddCoinWithExtremeValues() {
        EuroCoin2 highValueCoin = new EuroCoin2(Integer.MAX_VALUE, Country.ES, "Moneda especial", CoinColor.GOLD_SILVER, 2000);
        EuroCoin2 oldYearCoin = new EuroCoin2(10, Country.ES, "Moneda antigua", CoinColor.BRONZE, 1900);

        assertTrue(collection.addCoin(highValueCoin));
        assertTrue(collection.addCoin(oldYearCoin));
        assertEquals(8, collection.countCoins());

        assertTrue(collection.hasCoin(highValueCoin));
        assertTrue(collection.hasCoin(oldYearCoin));
    }

    @Test
    void testClearCollection() {
        assertEquals(6, collection.countCoins());
        collection.clear();
        assertEquals(0, collection.countCoins());
        Iterator<EuroCoin2> iterator = collection.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorWithNoMatchingCountry() {
        Iterator<EuroCoin2> iterator = collection.iterator(Country.GR);
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testRemoveLastElementWithIterator() {
        Iterator<EuroCoin2> iterator = collection.iterator();
        EuroCoin2 lastCoin = null;
        while (iterator.hasNext()) {
            lastCoin = iterator.next();
        }

        iterator.remove();
        assertFalse(collection.hasCoin(lastCoin));
        assertEquals(5, collection.countCoins());
    }

    @Test
    void testAddCoinAfterClear() {
        collection.clear();
        assertEquals(0, collection.countCoins());

        EuroCoin2 newCoin = new EuroCoin2(100, Country.BE, "Nueva moneda", CoinColor.GOLD_SILVER, 2020);
        assertTrue(collection.addCoin(newCoin));
        assertEquals(1, collection.countCoins());
        assertTrue(collection.hasCoin(newCoin));
    }

    @Test
    void testIteratorForSingleCountryWithMultipleCoins() {
        collection.addCoin(new EuroCoin2(1, Country.ES, "Moneda de 1c", CoinColor.GOLD_SILVER, 2007));

        Iterator<EuroCoin2> spanishCoins = collection.iterator(Country.ES);
        int count = 0;
        while (spanishCoins.hasNext()) {
            EuroCoin2 coin = spanishCoins.next();
            assertEquals(Country.ES, coin.getCountry());
            count++;
        }
        assertEquals(4, count);
    }
}
