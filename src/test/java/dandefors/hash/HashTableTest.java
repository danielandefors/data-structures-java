package dandefors.hash;

import dandefors.TestData;
import dandefors.table.SymbolTableTest;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.hamcrest.number.OrderingComparison.*;
import static org.junit.Assert.*;

/**
 *
 */
public class HashTableTest extends SymbolTableTest {
    @Override
    protected HashTable<String, String> createTable() {
        return new HashTable<>();
    }

    @Test
    public void testLoadFactor() {
        HashTable<String, String> table = createTable();
        String[] input = TestData.LOREM;
        for (String s : input) {
            table.put(s, s);
            assertTrue(table.contains(s));
            assertThat(table.getLoadFactor(), lessThanOrEqualTo(0.75f));
        }
        for (String s : input) {
            table.delete(s);
            assertFalse(table.contains(s));
            if (table.size() > 2) {
                assertThat(table.getLoadFactor(), greaterThanOrEqualTo(0.1875f));
            }
        }
        assertEquals(0, table.size());
        assertTrue(table.isEmpty());
    }

    @Test
    public void testPerformance() {

        Random r = new Random();
        Integer[] data = new Integer[1000];
        for (int i = 0; i < data.length; i++) {
            data[i] = r.nextInt(10000000);
        }

        double[] times = new double[199];

        // Test hash table
        for (int i = 0; i < times.length; i++) {
            times[i] = testHashTable(data);
        }
        Arrays.sort(times);
        System.out.printf("HashTable: %.2f\n", times[times.length / 2]);

        // Test hash map
        for (int i = 0; i < times.length; i++) {
            times[i] = testHashMap(data);
        }
        Arrays.sort(times);
        System.out.printf("HashMap: %.2f\n", times[times.length / 2]);

    }

    private double testHashTable(Integer[] data) {
        long begin = System.nanoTime();
        HashTable<Integer, Integer> table = new HashTable<>();
        for (Integer x : data) {
            table.put(x, x);
        }
        long end = System.nanoTime();
        return (end - begin) / 1000000.0;
    }

    private double testHashMap(Integer[] data) {
        long begin = System.nanoTime();
        Map<Integer, Integer> table = new HashMap<>();
        for (Integer x : data) {
            table.put(x, x);
        }
        long end = System.nanoTime();
        return (end - begin) / 1000000.0;
    }

}
