package dandefors.tuple;

/**
 * A 2-tuple of ints.
 */
public final class IntTuple {

    private final int first;
    private final int second;

    /**
     * Create a new 2-tuple with the given values.
     *
     * @param first  The first value.
     * @param second The second value.
     */
    public IntTuple(int first, int second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Get the first value.
     *
     * @return The first value.
     */
    public int getFirst() {
        return first;
    }

    /**
     * Get the second value.
     *
     * @return The second value.
     */
    public int getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntTuple intTuple = (IntTuple) o;
        return first == intTuple.first && second == intTuple.second;
    }

    @Override
    public int hashCode() {
        return 31 * first + second;
    }

}
