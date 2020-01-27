package SingleLinkedList;

/**
 * A stack represents a last-in-first-out (LIFO) stack of elements.
 * Push and pop operations are provided, as well as a method to peek at the top
 * element on the stack and a method to search the stack for an element and
 * discover how far it is from the top.
 * @author Maritha Dahlin
 * @version 2020-01-22
 * @param <E> the type of elements held in this collection
 */
public interface IStack<E> {

  /**
   * Retrieves and removes the top element of this stack, or returns null if
   * this stack is empty.
   * @return the top element of this stack, or null if this stack is empty
	 */
  public E pop();

  /**
   * Pushes the specified element onto the top of this stack.
   * @param element element to be pushed onto this stack
   */
  public void push(E element);

  /**
   * Returns the 0-based position of an element on this stack. If the
   * element <i>element</i> occurs as an item in this stack, this method
   * returns the distance from the top of the stack of the occurrence nearest
   * the top of the stack; the topmost element on the stack is considered to be
   * at distance 0. The equals method is used to compare element to the items
   * in this stack.
   * @param element element to search for
   * @return the 0-based position from the top of the stack where the element
   *         is located; the return value -1 indicates that the element is not
   *         on the stack
   */
  public int search(E element);

  /**
   * Returns the number of elements in this stack.
   * @return the number of elements in this stack
   */
  public int size();

  /**
   * Returns an array containing all of the elements in this stack in proper
   * sequence (from the top to the bottom element), or null if this stack is
   * empty.
   * @return an array containing all of the elements in this stack in proper
   *         sequence, or null if this stack is empty
   */
  public E[] toArray();

  /**
   * Returns a string representation of this stack. The string representation
   * consists of the stacks's elements in the order they are stored, enclosed
   * in square brackets ("[]"). Adjacent elements are separated by the
   * characters ", " (comma and space).
   * @return a string representation of this stack
   */
  public String toString();

  /**
   * Retrieves, but does not remove, the top element of this stack, or returns
   * null if this stack is empty.
   * @return the top element of this stack, or null if this stack is empty
   */
  public E top();
}