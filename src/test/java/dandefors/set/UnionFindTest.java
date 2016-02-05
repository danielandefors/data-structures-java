package dandefors.set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 */
public class UnionFindTest {

    @Test
    public void testUnionFind() {

        UnionFind u = new UnionFind(10);
        assertEquals(10, u.length());
        assertEquals(10, u.sets());
        for (int i = 0; i < u.length(); i++) {
            assertEquals(i, u.find(i));
            for (int j = 0; j < u.length(); j++) {
                assertEquals(i == j, u.same(i, j));
            }
        }

        // Create a new set with four elements
        u.union(2, 4);
        u.union(2, 7);
        u.union(9, 7);

        assertEquals(10, u.length());
        assertEquals(7, u.sets());

        int root = u.find(2);
        assertEquals(root, u.find(2));
        assertEquals(root, u.find(4));
        assertEquals(root, u.find(7));
        assertEquals(root, u.find(9));
        assertTrue(u.same(2, 4));
        assertTrue(u.same(2, 7));
        assertTrue(u.same(2, 9));
        assertTrue(u.same(4, 7));
        assertTrue(u.same(4, 9));
        assertTrue(u.same(7, 9));

        // Create a new set of two elements
        u.union(1, 8);
        assertEquals(10, u.length());
        assertEquals(6, u.sets());
        assertEquals(u.find(1), u.find(8));
        assertEquals(u.find(8), u.find(1));

        // These are already in the same set
        u.union(9, 2);
        assertEquals(10, u.length());
        assertEquals(6, u.sets());

        // Join two sets:
        // The root of the smaller set should be the root of the larger set
        int largerRoot = u.find(2);
        u.union(1, 2);
        assertEquals(largerRoot, u.find(1));


    }
}
