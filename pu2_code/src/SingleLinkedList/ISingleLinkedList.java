package SingleLinkedList;

/**
 * A list constitutes an ordered collection of elements. The user can add,
 * access, search for and remove elements by their zero based integer index.
 * @author Maritha Dahlin
 * @version 2020-01-22
 * @param <E> the type of elements held in this collection
 */
public interface ISingleLinkedList<E> {

	/**
	 * Appends the specified element to the end of this list.
	 * @param element element to be appended to the end of this list
	 */
	public void add(E element);

	/**
	 * Inserts the specified element at the specified index in this list. Shifts
	 * the element currently at that position (if any) and any subsequent
	 * elements to the right (adds one to their indices).
	 * @param index index at which the specified element is to be inserted
	 * @param element element to be inserted
	 * @throws IndexOutOfBoundsException if index &lt; 0 or index &gt; size()
	 */
	public void add(int index, E element) throws IndexOutOfBoundsException;

	/**
	 * Removes all of the elements from this list.  The list will be empty after
	 * this call returns.
	 */
	public void clear();

	/**
	 * Returns the element at the specified index in this list.
	 * @param index index of the element to return
	 * @return the element at the specified index in this list
	 * @throws IndexOutOfBoundsException if index &lt; 0 or index &ge; size()
	 */
	public E get(int index) throws IndexOutOfBoundsException;

	/**
	 * Returns the index of the first occurrence of the specified element in this
	 * list, or -1 if this list does not contain the element. More formally,
	 * returns the lowest index i such that (E == null ? get(i) == null :
	 * E.equals(get(i))), or -1 if there is no such index.
	 * @param element element to search for
	 * @return the index of the first occurrence of the specified element in
	 *         this list, or -1 if this list does not contain the element
	 */
	public int indexOf(E element);

	/**
	 * Removes the element at the specified index in this list. Shifts any
	 * subsequent elements to the left (subtracts one from their indices).
	 * Returns the element that was removed from the list.
	 * @param index the index of the element to be removed
	 * @return the element previously at the specified index
	 * @throws IndexOutOfBoundsException if index &lt; 0 or index &ge; size()
	 */
	public E remove(int index) throws IndexOutOfBoundsException;

	/**
	 * Replaces the element at the specified index in this list with the
	 * specified element.
	 * @param index index of the element to replace
	 * @param element element to be stored at the specified index
	 * @return the element previously at the specified index
	 * @throws IndexOutOfBoundsException if index &lt; 0 or index &ge; size()
	 */
	public E set(int index, E element) throws IndexOutOfBoundsException;

	/**
	 * Returns the number of elements in this list.
	 * @return the number of elements in this list
	 */
	public int size();

	/**
	 * Returns an array containing all of the elements in this list in proper
	 * sequence (from first to last element), or null if this list is empty.
	 * @return an array containing all of the elements in this list in proper
	 *         sequence, or null if this list is empty
	 */
	public E[] toArray();

	/**
	 * Returns a string representation of this list. The string representation
	 * consists of the list's elements in the order they are stored, enclosed in
	 * square brackets ("[]"). Adjacent elements are separated by the characters
	 * ", " (comma and space).
	 * @return a string representation of this list
	 */
	@Override
	public String toString();
}