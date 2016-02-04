package dandefors.tuple;

/**
 *
 */
public final class IntTuple {

    private final int first;
    private final int second;

    public IntTuple(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

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
