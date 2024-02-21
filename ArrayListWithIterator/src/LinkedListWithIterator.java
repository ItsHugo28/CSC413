/*************************************************
 File: ExpressionDriver.java
 By: Hugo Gomez
 Date: 2/19/2024
 Description: Program of the implementation of the
 linked list and the iterator
 *************************************************/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListWithIterator<E extends Comparable<? super E>> extends MyLList<E> {
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
        private ListNode nextNode;

        private IteratorForLinkedList() {
            nextNode = firstNode;
        }
        // Sees if there is still elements in the list
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
            E nextEntry = nextNode.data;
            nextNode = nextNode.next;
            return nextEntry;
        }
    }
}
