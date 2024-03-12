/*************************************************
 File: PriorityQueue.java
 By: Hugo Gomez
 Date: 3/12/2024
 Description: Priority Queue Class
 *************************************************/
import java.util.Date;
import java.util.NoSuchElementException;

public class PriorityQueue<T extends AccountDTO> {

    private final LinkedList<T> queue;
    private final LinkedList<Date> creationDates;

    public PriorityQueue() {
        this.queue = new LinkedList<>();
        this.creationDates = new LinkedList<>();
    }

    public void add(T item) {
        int index = 0;
        Date currentDate = new Date();
        for (int i = 0; i < queue.size(); i++) {
            T element = queue.get(i);
            if (compareItems(item, element, currentDate) >= 0) {
                index++;
            } else {
                break;
            }
        }
        queue.add(index, item);
        creationDates.add(index, currentDate);
    }

    public T remove() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Priority queue is empty");
        }
        creationDates.remove(0);
        return queue.poll();
    }

    public T peek() {
        return queue.peek();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int getSize() {
        return queue.size();
    }

    public void clear() {
        queue.clear();
        creationDates.clear();
    }

    private int compareItems(T item1, T item2, Date currentDate) {
        Date creationDate1 = getCreationDate(item1, currentDate);
        Date creationDate2 = getCreationDate(item2, currentDate);


        int dateComparison = creationDate1.compareTo(creationDate2);
        if (dateComparison != 0) {
            return dateComparison;
        } else {

            double balance1 = item1.getBalance();
            double balance2 = item2.getBalance();
            return Double.compare(balance2, balance1);
        }
    }

    private Date getCreationDate(T item, Date currentDate) {
        int index = queue.indexOf(item);
        return index != -1 ? creationDates.get(index) : currentDate;
    }
}
