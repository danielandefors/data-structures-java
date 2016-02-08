package dandefors.tuple;

/**
 * A 2-tuple of some objects.
 *
 * @param <A> The first value type.
 * @param <B> The second value type.
 */
public class Tuple<A, B> {

    private final A first;
    private final B second;

    /**
     * Create a 2-tuple with the given objects.
     *
     * @param first  The first value.
     * @param second The second value.
     */
    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get the first value.
     *
     * @return The first value.
     */
    public A getFirst() {
        return first;
    }

    /**
     * Get the second value.
     *
     * @return The second value.
     */
    public B getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (first != null ? !first.equals(tuple.first) : tuple.first != null) return false;
        if (second != null ? !second.equals(tuple.second) : tuple.second != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        return result;
    }
}
