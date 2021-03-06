

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;

import SingleLinkedList.DoubleLinkedList;
import SingleLinkedList.IDoubleLinkedList;
import org.junit.Test;

/**
 * @version Requires JUnit 4
 * @author Maritha Dahlin
 */
public class JUnitTestDoubleLinkedList {

	/**
	 * Create an ArrayList of <code>listSize</code> strings ["element0", "element1", ... ].
	 * @param <code>listSize</code> the number of strings to be generated
	 * @return an ArrayList of <code>listSize</code> strings ["element0", "element1", ... ]
	 * @throws IllegalArgumentException if <code>listSize</code> < 0
	 */
	private ArrayList<Object> generateArrayOfTestData(int listSize)
			throws IllegalArgumentException {

		// Check whether parameter listSize is valid or not
		if (listSize < 0) {
			throw new IllegalArgumentException("generateArrayOfTestData - illegal argument: listSize < 0");
		}

		// First create an ArrayList
		ArrayList<Object> arrayOfTestData = new ArrayList<Object>(listSize);
		// Then add the specified number of string elements
		for (int i = 0; i < listSize; i++) {
			arrayOfTestData.add("element" + i);
		}

		return arrayOfTestData;
	}

	/**
	 * Create a double linked list containing the elements as specified by the
	 * given ArrayList.
	 * @param <code>arrayOfTestData</code> the ArrayList that contains the
	 *        elements to be added to the list
	 * @return a double linked list containing the elements as specified by the
	 *         given ArrayList of elements
   * @throws NullPointerException if the specified ArrayList equals null
   */
	private IDoubleLinkedList<Object> createDoubleLinkedListOfTestData(ArrayList<Object> arrayOfTestData)
			throws NullPointerException {

		// Check whether parameter arrayOfTestData is valid or not
		if (arrayOfTestData == null) {
			throw new NullPointerException("createDoubleLinkedListOfTestData - illegal argument: arrayOfTestData == null");
		}

		// First create a double linked list
		IDoubleLinkedList<Object> doubleLinkedListOfTestData = new DoubleLinkedList<Object>();
		// Then add the elements from the arrayOfTestData parameter to the list
		for (int i = 0; i < arrayOfTestData.size(); i++) {
			doubleLinkedListOfTestData.add(arrayOfTestData.get(i));
		}

		return doubleLinkedListOfTestData;
	}

	/**
	 * Create a double linked list containing the elements as specified by the
	 * generated ArrayList of elements.
	 * @param <code>listSize</code> the number of strings to be generated
	 * @return a double linked list containing the elements as specified by the
	 *         generated ArrayList of elements
   * @throws IllegalArgumentException if the specified listsize < 0
	 */
	private IDoubleLinkedList<Object> createDoubleLinkedListOfTestData(int listSize)
			throws IllegalArgumentException {

		// Check whether parameter listSize is valid or not
		if (listSize < 0) {
			throw new IllegalArgumentException("createDoubleLinkedListOfTestData - illegal argument: listsize < 0");
		}

		// First create an ArrayList with string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> doubleLinkedListOfTestData = createDoubleLinkedListOfTestData(arrayOfTestData);

		return doubleLinkedListOfTestData;
	}

	/**
	 * Create a double linked list of <code>listSize</code> elements.
	 * Assert that the list not equals null, i.e. actually contains something.
	 */
	@Test
	public void testAdd5ElementsToEmptyList() {

		int listSize = 5;
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(listSize);

		// Test that list is not null
		assertNotNull(dll);
	}

	/**
	 * Create an empty double linked list and test size.
	 * Assert that size of list is 0.
	 */
	@Test
	public void testSizeOfEmptyList() {

		int listSize = 0;
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(listSize);

		// Test size after initial setup
		assertEquals(listSize, dll.size());
	}

	/**
	 * Create a double linked list containing 5 elements and test size.
	 * Assert that size of list is 5.
	 */
	@Test
	public void testSizeOfListWith5Elements() {

		int listSize = 5;
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(listSize);

		// Test size after initial setup
		assertEquals(listSize, dll.size());
	}

	/**
	 * Create an empty double linked list and clear the list.
	 * Assert that size of list is 0.
	 */
	@Test
	public void testClearOnEmptyList() {

		int listSize = 0;
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(listSize);

		dll.clear();
		// Test size after clear operation
		assertEquals(0, dll.size());
	}

	/**
	 * Create a double linked list containing 5 elements and clear the list.
	 * Assert that size of list is 0.
	 */
	@Test
	public void testClearOnListWith5Elements() {

		int listSize = 5;
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(listSize);

		dll.clear();
		// Test size after clear operation
		assertEquals(0, dll.size());
	}

	/**
	 * Create an empty double linked list, add an element and test size.
	 * Assert that size of list is 1.
	 */
	@Test
	public void testAddOneElementToEmptyList() {

		int listSize = 0;
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(listSize);

		dll.add(42);
		// Test size after add operation
		assertEquals(listSize + 1, dll.size());
	}

	/**
	 * Create a double linked list containing 5 elements, add an element and test
	 * size.
	 * Assert that size of list is 6.
	 */
	@Test
	public void testAddOneElementToListWith5Elements() {

		int listSize = 5;
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(listSize);

		dll.add(42);
		// Test size after add operation
		assertEquals(listSize + 1, dll.size());
	}

	/**
	 * Create a double linked list containing 5 elements and try to get an
	 * element using a negative index.
	 * Assert that an IndexOutOfBoundsException is thrown by the get() method.
	 */
	@Test
	public void testGetByNegativeIndexFromListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList with string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.get(-1);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testGetByNegativeIndexFromListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testGetByNegativeIndexFromListWith5Elements - test failed. The expected exception was not catched");
		}
	}

	/**
	 * Create an empty double linked list and try to get an element using index
	 * 0.
	 * Assert that an IndexOutOfBoundsException is thrown by the get() method.
	 */
	@Test
	public void testGetByIndex0FromEmptyList() {

		int listSize = 0;
		// First create an ArrayList with string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.get(0);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testGetByIndex0FromEmptyList - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testGetByIndex0FromEmptyList - test failed. The expected exception was not catched");
		}
	}

	/**
	 * Create a double linked list containing 5 elements and try to get each
	 * element using valid indexes.
	 * Assert that the elements of arrayOfTestData equals the elements in the
	 * double linked list.
	 */
	@Test
	public void testGetByIndexesFromListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList with string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Check that the contents are equal
		for (int i = 0; i < dll.size(); i++) {
			assertEquals(arrayOfTestData.get(i), dll.get(i));
		}
	}

	/**
	 * Create a double linked list containing 5 elements and try to get an
	 * element using a too large index.
	 * Assert that an IndexOutOfBoundsException is thrown by the get() method.
	 */
	@Test
	public void testGetByTooLargeIndexFromListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.get(dll.size());
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testGetByTooLargeIndexFromListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testGetByTooLargeIndexFromListWith5Elements - test failed. The expected exception was not catched");
		}
	}

	/**
	 * Create a double linked list containing 5 elements and try to add an
	 * element using a negative index.
	 * Assert that an IndexOutOfBoundsException is thrown by the get() method.
	 */
	@Test
	public void testAddWithNegativeIndexToListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.add(-1, 42);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testAddWithNegativeIndexToListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testAddWithNegativeIndexToListWith5Elements - test failed. The expected exception was not catched");
		}
	}

	/**
	 * Create an empty double linked list and try to add a series of elements
	 * using index 0.
	 * Assert that the element of arrayOfTestData equals the element in the
	 * double linked list.
	 */
	@Test
	public void testAddWithIndex0ToEmptyList() {

		int listSize = 0;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Add a series of new elements using index 0 to the ArrayList of test data and
		// the double linked list
		try {
			arrayOfTestData.add(0, 42);
			arrayOfTestData.add(0, "addedElement1");
			arrayOfTestData.add(0, "addedElement2");
			arrayOfTestData.add(0, "addedElement3");
			dll.add(0, 42);
			dll.add(0, "addedElement1");
			dll.add(0, "addedElement2");
			dll.add(0, "addedElement3");
		}
		catch (Exception e) {
			fail("testAddWithIndex0ToEmptyList - add() method failed");
		}

		// Check that the contents are equal
		for (int i = 0; i < dll.size(); i++) {
			assertEquals(arrayOfTestData.get(i), dll.get(i));
		}
	}

	/**
	 * Create a double linked list containing 5 elements and try to add elements
	 * using indexes 0 (first), 3 (in the middle) and 7 (last).
	 * Assert that the elements of arrayOfTestData equals the elements in the
	 * double linked list.
	 */
	@Test
	public void testAddWithIndexesToListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Add new elements first, in the middle and last to the ArrayList of test data
		// and the double linked list
		try {
			arrayOfTestData.add(0, 42);
			arrayOfTestData.add(3, "addedElement1");
			arrayOfTestData.add(7, "addedElement2");
			dll.add(0, 42);
			dll.add(3, "addedElement1");
			dll.add(7, "addedElement2");			
		}
		catch (Exception e) {
			fail("testAddWithIndexesToListWith5Elements - add() method failed");
		}

		// Check that the contents are equal
		for (int i = 0; i < dll.size(); i++) {
			assertEquals(arrayOfTestData.get(i), dll.get(i));
		}
	}

	/**
	 * Create a double linked list containing 5 elements and try to add an
	 * element using a too large index.
	 * Assert that an IndexOutOfBoundsException is thrown by the add() method.
	 */
	@Test
	public void testAddWithTooLargeIndexToListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.add(dll.size() + 1, "addedElement0");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testAddWithTooLargeIndexFromListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testAddWithTooLargeIndexFromListWith5Elements - test failed. The expected exception was not catched");
		}
	}

	/**
	 * Create an empty double linked list and try to find the index of an element
	 * in that list.
	 * Assert that -1 is returned by the indexOf() method.
	 */
	@Test
	public void testIndexOfOnEmptyList() {

		int listSize = 0;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test indexOf on an empty list => return -1
		assertEquals(-1, dll.indexOf("element0"));
	}

	/**
	 * Create a double linked list containing 5 elements and try to find the
	 * index of an element that does not exist.
	 * Assert that -1 is returned by the indexOf() method
	 */
	@Test
	public void testIndexOfOnListWith5ElementsWithNoMatchingElement() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test indexOf for an element that does not exist => return -1
		assertEquals(-1, dll.indexOf("thisElementDoesNotExist"));
	}

	/**
	 * Create a double linked list containing 5 elements and try to find the
	 * index of each element.
	 * Assert that the indexes of the elements of arrayOfTestData equals the
	 * indexes of the elements in the double linked list.
	 */
	@Test
	public void testIndexOfOnListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Check that the contents are equal
		for (int i = 0; i < dll.size(); i++) {
			assertEquals(arrayOfTestData.indexOf("element" + i), dll.indexOf("element" + i));
		}
	}

	/**
	 * Create an empty double linked list and try to find the last index of an
	 * element in that list.
	 * Assert that -1 is returned by the lastIndexOf() method.
	 */
	@Test
	public void testLastIndexOfOnEmptyList() {

		int listSize = 0;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test lastIndexOf on an empty list => return -1
		assertEquals(-1, dll.lastIndexOf("element0"));
	}

	/**
	 * Create a double linked list containing 5 elements and try to find the
	 * last index of an element that does not exist.
	 * Assert that -1 is returned by the lastIndexOf() method
	 */
	@Test
	public void testLastIndexOfOnListWith5ElementsWithNoMatchingElement() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test lastIndexOf for an element that does not exist => return -1
		assertEquals(-1, dll.lastIndexOf("thisElementDoesNotExist"));
	}

	/**
	 * Create a double linked list containing 5 elements and try to find the
	 * last index of each element.
	 * Assert that the indexes of the elements of arrayOfTestData equals the
	 * indexes of the elements in the double linked list.
	 */
	@Test
	public void testLastIndexOfOnListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Add an extra element0 last in the arrayOfTestData as well as in the
		// double linked list
		arrayOfTestData.add("element0");
		dll.add("element0");
		// Check that the contents are equal
		for (int i = 0; i < dll.size(); i++) {
			assertEquals(arrayOfTestData.lastIndexOf("element" + i), dll.lastIndexOf("element" + i));
		}
	}

	/**
	 * Create a double linked list containing 5 elements and try to remove an
	 * element using a negative index.
	 * Assert that an IndexOutOfBoundsException is thrown by the remove() method.
	 */
	@Test	
	public void testRemoveWithNegativeIndexFromListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.remove(-1);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveWithNegativeIndexFromListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testRemoveWithNegativeIndexFromListWith5Elements - test failed. The expected exception was not catched");
		}		
	}

	/**
	 * Create an empty double linked list and try to remove an element using
	 * index 0.
	 * Assert that an IndexOutOfBoundsException is thrown by the remove() method.
	 */
	@Test
	public void testRemoveWithIndex0FromEmptyList() {

		int listSize = 0;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.remove(0);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveWithIndex0FromEmptyList - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testRemoveWithIndex0FromEmptyList - test failed. The expected exception was not catched");
		}		
	}

	/**
	 * Create a double linked list containing 5 elements and try to remove
	 * elements using indexes 0 (first), 1 (in the middle) and 2 (last).
	 * Assert that the elements of arrayOfTestData equals the elements in the
	 * double linked list.
	 */
	@Test
	public void testRemoveWithIndexesFromListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Add new elements first, in the middle and last to the ArrayList of test data
		// and the double linked list
		try {
			arrayOfTestData.remove(0);
			arrayOfTestData.remove(1);
			arrayOfTestData.remove(2);
			dll.remove(0);
			dll.remove(1);
			dll.remove(2);

		}
		catch (Exception e) {
			fail("testRemoveWithIndexesFromListWith5Elements - remove() method failed");
		}

		// Check that the contents are equal
		for (int i = 0; i < dll.size(); i++) {
			assertEquals(arrayOfTestData.get(i), dll.get(i));
		}  	
	}

	/**
	 * Create a double linked list containing 5 elements and try to remove an
	 * element using a too large index.
	 * Assert that an IndexOutOfBoundsException is thrown by the remove() method.
	 */
	@Test
	public void testRemoveWithTooLargeIndexFromListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.remove(dll.size());
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testRemoveWithTooLargeIndexFromListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testRemoveWithTooLargeIndexFromListWith5Elements - test failed. The expected exception was not catched");
		}
	}

	/**
	 * Create a double linked list containing 5 elements and try to set an
	 * element using a negative index.
	 * Assert that an IndexOutOfBoundsException is thrown by the set() method.
	 */
	@Test	
	public void testSetWithNegativeIndexOnListWith5Element() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.set(-1, "updatedElement-1");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testSetWithNegativeIndexOnListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testSetWithNegativeIndexOnListWith5Elements - test failed. The expected exception was not catched");
		}		
	}

	/**
	 * Create an empty double linked list and try to set an element using index
	 * 0.
	 * Assert that an IndexOutOfBoundsException is thrown by the set() method.
	 */
	@Test
	public void testSetWithIndex0OnEmptyList() {

		int listSize = 0;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.set(0, "updatedElement0");
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testSetWithIndex0OnEmptyList - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testSetWithIndex0OnEmptyList - test failed. The expected exception was not catched");
		}		
	}

	/**
	 * Create a double linked list containing 5 elements and try to set elements
	 * using indexes 0 (first), 2 (in the middle) and 4 (last).
	 * Assert that the elements of arrayOfTestData equals the elements in the
	 * double linked list.
	 */
	@Test
	public void testSetWithIndexesOnListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Add new elements first, in the middle and last to the ArrayList of test data
		// and the double linked list
		try {
			arrayOfTestData.set(0, "updatedElement0");
			arrayOfTestData.set(2, "updatedElement2");
			arrayOfTestData.set(4, "updatedElement4");
			dll.set(0, "updatedElement0");
			dll.set(2, "updatedElement2");
			dll.set(4, "updatedElement4");			
		}
		catch (Exception e) {
			fail("testSetWithIndexesOnListWith5Elements() - set() method failed");
		}

		// Check that the contents are equal
		for (int i = 0; i < dll.size(); i++) {
			assertEquals(arrayOfTestData.get(i), dll.get(i));
		}  	
	}

	/**
	 * Create a double linked list containing 5 elements and try to set an
	 * element using a too large index.
	 * Assert that an IndexOutOfBoundsException is thrown by the set() method.
	 */
	@Test
	public void testSetWithTooLargeIndexOnListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Index out of range => IndexOutOfBoundException
		try {
			dll.remove(dll.size());
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("testSetWithTooLargeIndexOnListWith5Elements - IndexOutOfBoundException catched - " + e.getMessage());
			assertTrue(true);
		}
		catch (Exception e) {
			fail("testSetWithTooLargeIndexOnListWith5Elements - test failed. The expected exception was not catched");
		}
	}

	/**
	 * Create an empty double linked list and convert to an array.
	 * Assert that the toArray() method returns null.
	 */
	@Test
	public void testToArrayOnEmptyList()	{

		int listSize = 0;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test that the returned value is null
		assertNull(dll.toArray());
	}

	/**
	 * Create a double linked list containing 5 elements and convert to an array.
	 * Assert that the ArrayList of test data equals the contents of the double
	 * linked list expressed as an array.
	 */
	@Test
	public void testToArrayOnListWith5Elements()	{

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test that the arrays are equal
		assertTrue(Arrays.equals(arrayOfTestData.toArray(), dll.toArray()));
	}

	/**
	 * Create an empty double linked list and convert to a string.
	 * Assert that the ArrayList of test data, converted to a string, equals the
	 * contents of the double linked list expressed as a string.
	 */
	@Test
	public void testToStringOnEmptyList() {

		int listSize = 0;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test that the strings are equal
		assertEquals(arrayOfTestData.toString(), dll.toString());
	}

	/**
	 * Create a double linked list containing 5 elements and convert to a string.
	 * Assert that the ArrayList of test data, converted to a string, equals the
	 * contents of the double linked list expressed as a string.
	 */
	@Test
	public void testToStringOnListWith5Elements() {

		int listSize = 5;
		// First create an ArrayList of string elements that constitutes the test data
		ArrayList<Object> arrayOfTestData = generateArrayOfTestData(listSize);
		// Then create a double linked list consisting of the elements of the ArrayList
		IDoubleLinkedList<Object> dll = createDoubleLinkedListOfTestData(arrayOfTestData);

		// Test that the strings are equal
		assertEquals(arrayOfTestData.toString(), dll.toString());
	}
}