package SingleLinkedList;

/**
 * An implementation of the IQueue interface using {@code Node}s.
 *
 * @author Carl Granström
 * @version 2020-01-28
 * @see IDoubleLinkedList
 * @see IQueue
 */
public class Queue<E> extends DoubleLinkedList<E> implements IQueue<E> {

    public void offer(E element){
        super.add(size, element);
    }

    public E peek(){
        if (isEmpty()){ return null; }
        return head.mElement;
    }

    public E poll(){
        if (isEmpty()) {
            return null;
        }

        E element = remove(0);

        return element;
    }

    public int size(){ return size; }
}
