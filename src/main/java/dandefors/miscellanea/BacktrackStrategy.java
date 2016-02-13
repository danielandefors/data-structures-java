package dandefors.miscellanea;

/**
 * A strategy for the generic backtracking function.
 */
public interface BacktrackStrategy<T> {

    boolean isSolution(int k);

    boolean processSolution(int k);

    Iterable<T> getCandidates(int k);

    void makeMove(T candidate, int k);

    void unmakeMove(T candidate, int k);

}
