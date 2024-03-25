/*************************************************
 File: LinkedList.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Linked List class
 *************************************************/
public class LinkedList<T> {

    private Node<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> newNode = new Node<>(item);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedItem;
        if (index == 0) {
            removedItem = head.data;
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removedItem = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removedItem;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T removedData = head.data;
        head = head.next;
        size--;
        return removedData;
    }

    public void clear() {
        head = null;
        size = 0;
    }


    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int indexOf(Object obj) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(obj)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    private static class Node<T> {
        private final T data;
        private Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
