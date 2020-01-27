package SingleLinkedList;

/**
 * A queue typically, but not necessarily, order elements in a first-in-first-out
 * (FIFO). Among the exceptions are priority queues, which order elements
 * according to a supplied comparator, or the elements' natural ordering, and
 * LIFO queues (or stacks) which order the elements last-in-first-out (LIFO).
 * Whatever the ordering used, the head of the queue is that element which would
 * be removed by a call to poll(). In a FIFO queue, all new elements are
 * inserted at the tail of the queue. Other kinds of queues may use different
 * placement rules.
 * @author Maritha Dahlin
 * @version 2020-01-22
 * @param <E> the type of elements held in this collection
 */
public interface IQueue<E> {

  /**
   * Inserts the specified element into this queue.
   * @param element element to be inserted into this queue
   */
  public void offer(E element);

  /**
   * Retrieves, but does not remove, the head element of this queue, or returns
   * null if this queue is empty.
   * @return the head element of this queue, or null if this queue is empty
   */
  public E peek();

  /**
   * Retrieves and removes the head element of this queue, or returns null if
   * this queue is empty.
   * @return the head element of this queue, or null if this queue is empty
   */
  public E poll();

  /**
   * Returns the number of elements in this queue.
   * @return the number of elements in this queue
   */
  public int size();

  /**
   * Returns an array containing all of the elements in this queue in proper
   * sequence (from the first to the last element), or null if this queue is
   * empty.
   * @return an array containing all of the elements in this queue in proper
   *         sequence, or null if this queue is empty
   */
  public E[] toArray();

  /**
   * Returns a string representation of this queue. The string representation
   * consists of the queue's elements in the order they are stored, enclosed
   * in square brackets ("[]"). Adjacent elements are separated by the
   * characters ", " (comma and space).
   * @return a string representation of this queue
   */
  @Override
  public String toString();
}