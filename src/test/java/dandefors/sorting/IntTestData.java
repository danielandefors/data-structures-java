package dandefors.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 */
public class IntTestData {

    private Class<?> forTest;
    private String resource;

    private int[] data;
    private int[] expected;

    public IntTestData(String resource) {
        this(IntTestData.class, resource);
    }

    public IntTestData(Class<?> forTest, String resource) {
        this.forTest = forTest;
        this.resource = resource;
        try {
            load();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data: " + resource, e);
        }
    }

    public int[] getData() {
        return data.clone();
    }

    public int[] getExpected() {
        return expected.clone();
    }

    private void load() throws IOException {
        long begin = System.nanoTime();
        try (InputStream in = forTest.getResourceAsStream(resource)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "utf8"));
            data = toInts(reader.readLine());
            expected = toInts(reader.readLine());
        }
        long end = System.nanoTime();
        System.err.printf("Loaded %s in %.1f%n", resource, (end - begin) / 1000000f);
    }

    private static int[] toInts(String s) {
        String[] xs = s.split(",");
        int[] a = new int[xs.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = Integer.parseInt(xs[i]);
        }
        return a;
    }

}
