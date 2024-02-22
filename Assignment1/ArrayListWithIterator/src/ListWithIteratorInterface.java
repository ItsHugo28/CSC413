import java.util.Iterator;
/**
 An interface for the ADT list that has an iterator.

 @author Frank M. Carrano
 @author Timothy M. Henry
 @version 5.0
 */
public interface ListWithIteratorInterface<E> extends ListInterface<E>, Iterable<E>
{
    public Iterator<E> getIterator();
} // end ListWithIteratorInterface