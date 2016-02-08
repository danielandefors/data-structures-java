package dandefors.stack;

/**
 * Convenience methods for stacks.
 */
public final class Stacks {

    Stacks() {
    }

    public static IntStack createIntStack() {
        return new IntStack();
    }

    public static <T> Stack<T> createLinkedStack() {
        return new LinkedStack<>();
    }

}
