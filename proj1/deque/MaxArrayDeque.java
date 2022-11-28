package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    /**
     * The default Comparator.
     */
    private final Comparator<T> c;

    /**
     * Creates a MaxArrayDeque with the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        this.c = c;
    }

    /**
     * Returns the maximum element in the deque as governed by the previously
     * given Comparator. If the MaxArrayDeque is empty, simply return null.
     */
    public T max() {
        return max(c);
    }

    /**
     * Returns the maximum element in the deque as governed by the parameter
     * comparator. If the MaxArrayDeque is empty, simply return null.
     */
    public T max(Comparator<T> comparator) {
        T maxItem = null;
        for (T item : this) {
            if (maxItem == null || comparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }
}
