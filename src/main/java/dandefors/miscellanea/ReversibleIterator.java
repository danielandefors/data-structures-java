package dandefors.miscellanea;

import java.util.Iterator;

/**
 * An iterator that can be reversed used to traverse in the opposite direction.
 */
public interface ReversibleIterator<T> extends Iterator<T> {

    boolean hasPrevious();

    T previous();

}
