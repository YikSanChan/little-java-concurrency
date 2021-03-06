package littlejava.util.concurrent;

/**
 * A Queue that
 * - wait for the queue to become non-empty when retrieving an element,
 * - wait for the queue to become non-full when storing an element.
 *
 * @param <E>
 */
public interface BlockingQueue<E> {
    /**
     * Inserts the specified element into this queue, waiting if necessary
     * for space to become available.
     *
     * @param e the element to add
     * @throws InterruptedException     if interrupted while waiting
     * @throws NullPointerException     if the specified element is null
     * @throws IllegalArgumentException if some property of the specified
     *                                  element prevents it from being added to this queue
     */
    void put(E e) throws InterruptedException;

    /**
     * Retrieves and removes the head of this queue, waiting if necessary
     * until an element becomes available.
     *
     * @return the head of this queue
     * @throws InterruptedException if interrupted while waiting
     */
    E take() throws InterruptedException;
}
