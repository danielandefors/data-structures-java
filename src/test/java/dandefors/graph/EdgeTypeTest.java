package dandefors.graph;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 *
 */
public class EdgeTypeTest {

    @Test
    public void testValues() {
        EdgeType[] types = EdgeType.values();
        assertArrayEquals(new EdgeType[]{EdgeType.BACK, EdgeType.CROSS, EdgeType.FORWARD, EdgeType.TREE}, types);
    }

    @Test
    public void testValueOf() {
        assertEquals(EdgeType.BACK, EdgeType.valueOf("BACK"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueOfUnknown() {
        EdgeType.valueOf("XXX");
    }

}
