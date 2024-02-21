/*************************************************
 File: ExpressionDriver.java
 By: Hugo Gomez
 Date: 2/19/2024
 Description: Program that shows the
 LinkedListWithIterator working
 *************************************************/

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyLList<String> myList = new MyLList<>();

        // Adds elements to myList
        myList.add("A");
        myList.add("B");
        myList.add("C");

        // Create an instance
        LinkedListWithIterator<String> listWithIterator = new LinkedListWithIterator<>();

        // Adds elements to listWithIterator
        listWithIterator.add("A");
        listWithIterator.add("B");
        listWithIterator.add("C");

        // Gets the instances on the linked list
        Iterator<String> iterator = listWithIterator.getIterator();

        // Iterates and prints the elements
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
