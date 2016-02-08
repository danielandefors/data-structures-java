package dandefors.stack;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class StacksTest {

    @Test
    public void testCreate() {
        new Stacks(); // 100% code coverage...
    }

    @Test
    public void testCreateLinkedStack() {
        Stack<Integer> s = Stacks.createLinkedStack();
        assertNotNull(s);
        assertThat(s, CoreMatchers.instanceOf(LinkedStack.class));
    }

    @Test
    public void testCreateIntStack() {
        IntStack s = Stacks.createIntStack();
        assertNotNull(s);
        assertThat(s, CoreMatchers.instanceOf(IntStack.class));
    }

}
