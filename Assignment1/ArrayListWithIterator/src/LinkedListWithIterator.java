/*************************************************
 File: LinkedListWithIterator.java
 By: Hugo Gomez
 Date: 2/19/2024
 Description: Program of the implementation of the
 linked list and the iterator
 *************************************************/
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListWithIterator<E extends Comparable<? super E>> extends MyLList<E> implements ListWithIteratorInterface<E> {
    // Constructor
    public LinkedListWithIterator() {
        super();
    }

    // Returns the iterator from this list
    public Iterator<E> getIterator() {
        return new IteratorForLinkedList();
    }

    // Returns the iterator from the method
    public Iterator<E> iterator() {
        return getIterator();
    }

    // Class that implements the iterator from the linked list
    private class IteratorForLinkedList implements Iterator<E> {
        private boolean wasNextCalled;
        private ListNode nextNode;
        private ListNode currentNode;
        private ListNode previousNode;

        private IteratorForLinkedList() {
            nextNode = firstNode;
            currentNode = null;
            previousNode = null;
            wasNextCalled = false;
        }

        // Sees if there are still elements in the list
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        // Returns next element
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Illegal call to next(); iterator is after end of list.");
            }
            currentNode = nextNode;
            nextNode = nextNode.next;
            previousNode = (previousNode == null) ? firstNode : previousNode.next;
            wasNextCalled = true;
            return currentNode.data;
        }

        // Removes the last element returned by next()
        @Override
        public void remove() {
            if (!wasNextCalled) {
                throw new IllegalStateException("Illegal call to remove(); next() was not called.");
            }
            if (currentNode == null) {
                throw new IllegalStateException("Illegal call to remove(); iterator is after the end of the list.");
            }
            if (currentNode == firstNode) {
                firstNode = firstNode.next;
            } else {
                previousNode.next = currentNode.next;
            }
            wasNextCalled = false;
        }
    }
}
