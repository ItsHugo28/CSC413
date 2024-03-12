/*************************************************
 File: PriorityQueueInterface.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Priority Queue interface
 *************************************************/
public interface PriorityQueueInterface<T extends Comparable<? super T>> {

    /**
     * Adds a new entry to this priority queue.
     *
     * @param newEntry an object
     */
    void add(T newEntry);

    /**
     * Removes and returns the item with the highest priority.
     *
     * @return either the object with the highest priority or, if the
     * priority queue is empty before the operation, null
     */
    T remove();

    /**
     * Retrieves the item with the highest priority.
     *
     * @return either the object with the highest priority or, if the
     * priority queue is empty, null
     */
    T peek();

    /**
     * Detects whether this priority queue is empty.
     *
     * @return true if the priority queue is empty, or false otherwise
     */
    boolean isEmpty();

    /**
     * Gets the size of this priority queue.
     *
     * @return the number of entries currently in the priority queue
     */
    int getSize();

    /**
     * Removes all entries from this priority queue
     */
    void clear();
}
