

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

/**
 * @version Requires JUnit 4
 * @author Maritha Dahlin
 */
public class JUnitTestStack {

	/**
	 * Creates an ArrayDeque of <code>stackSize</code> strings ["element0", "element1", ... ].
	 * According to the Java documentation, any implementation of the Deque interface is
	 * preferred to using the Stack class.
	 * @param <code>stackSize</code> the number of strings to be generated
	 * @return an ArrayDeque of <code>stackSize</code> strings ["element0", "element1", ... ]
	 * @throws IllegalArgumentException if <code>stackSize</code> &lt; 0
	 */
	private ArrayDeque<Object> generateArrayDequeOfTestData(int stackSize)
			throws IllegalArgumentException {

		// Check whether parameter stackSize is valid or not
		if (stackSize < 0) {
			throw new IllegalArgumentException("generateArrayDequeOfTestData - illegal argument: stackSize < 0");
		}

		// First create an ArrayDeque
		ArrayDeque<Object> arrayDequeOfTestData = new ArrayDeque<Object>(stackSize);
		// Then add the specified number of string elements
		for (int i = 0; i < stackSize; i++) {
			arrayDequeOfTestData.push("element" + i);
		}
		return arrayDequeOfTestData;
	}

	/**
	 * Create a stack containing the elements as specified by the given ArrayDeque.
	 * @param <code>arrayDequeOfTestData</code> the ArrayDeque that contains the
	 *        elements to be added to the stack
	 * @return a stack containing the elements as specified by the given
	 *         ArrayDeque of elements
   * @throws NullPointerException if the specified ArrayDeque equals null
	 */
	private IStack<Object> createStackOfTestData(ArrayDeque<Object> arrayDequeOfTestData) 
			throws NullPointerException {

		// Check whether parameter arrayDequeOfTestData is valid or not
		if (arrayDequeOfTestData == null) {
			throw new NullPointerException("createStackOfTestData - illegal argument: arrayDequeOfTestData == null");
		}

		// First create a stack
		IStack<Object> stackOfTestData = new Stack<Object>();
		// Then create an iterator over the elements in reverse order. Otherwise
		// they will be reversed anyway when simply pop()-ing and push()-ing them
		// onto the stack (and the tests will fail). Also, the pop() operation is
		// destructive and we want to keep the elements of the original ArrayDeque
		// in proper order.
		Iterator<Object> iterArrayDequeOfTestData = arrayDequeOfTestData.descendingIterator();
		// Finally add the elements from the Iterator to the stack
		while (iterArrayDequeOfTestData.hasNext()) {
			stackOfTestData.push(iterArrayDequeOfTestData.next());
		}

		return stackOfTestData;
	}

	/**
	 * Create a stack containing the elements as specified by the generated
	 * ArrayDeque of elements.
	 * @param <code>stackSize</code> the number of strings to be generated
	 * @return a stack containing the elements as specified by the generated
	 *         ArrayDeque of elements
	 * @throws IllegalArgumentException if <code>stackSize</code> &lt; 0
	 */
	private IStack<Object> createStackOfTestData(int stackSize)
			throws IllegalArgumentException {

		// Check whether parameter stackSize is valid or not
		if (stackSize < 0) {
			throw new IllegalArgumentException("createStackOfTestData - illegal argument: stacksize < 0");
		}

		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stackOfTestData = createStackOfTestData(arrayDequeOfTestData);

		return stackOfTestData;
	}

	/**
	 * Create a stack of <code>stackSize</code> elements.
	 * Assert that the stack not equals null, i.e. actually contains something.
	 */
	@Test
	public void testPush5ElementsOnEmptyStack() {

		int stackSize = 5;
		IStack<Object> stack = createStackOfTestData(stackSize);

		// Test that stack is not null
		assertNotNull(stack);
	}

	/**
	 * Create an empty stack and test size.
	 * Assert that size of stack is 0.
	 */
	@Test
	public void testSizeOfEmptyStack() {

		int stackSize = 0;
		IStack<Object> stack = createStackOfTestData(stackSize);

		// Test size after initial setup
		assertEquals(stackSize, stack.size());
	}

	/**
	 * Create a stack containing 5 elements and test size.
	 * Assert that size of stack is 5.
	 */
	@Test
	public void testSizeOfStackWith5Elements() {

		int stackSize = 5;
		IStack<Object> stack = createStackOfTestData(stackSize);

		// Test size after initial setup
		assertEquals(stackSize, stack.size());
	}

	/**
	 * Create an empty stack, push an element and test size.
	 * Assert that size of stack is 1.
	 */
	@Test
	public void testPushOneElementOnEmptyStack() {

		int stackSize = 0;
		IStack<Object> stack = createStackOfTestData(stackSize);

		stack.push(42);
		// Test size after push operation
		assertEquals(stackSize + 1, stack.size());
	}

	/**
	 * Create a stack containing 5 elements, push an element and test size.
	 * Assert that size of stack is 6.
	 */
	@Test
	public void testPushOneElementOnStackWith5Elements() {

		int stackSize = 5;
		IStack<Object> stack = createStackOfTestData(stackSize);

		stack.push(42);
		// Test size after push operation
		assertEquals(stackSize + 1, stack.size());
	}

	/**
	 * Create an empty stack and try to pop an element.
	 * Assert that the pop() method returns null.
	 */
	@Test
	public void testPopFromEmptyStack() {

		int stackSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Test pop on an empty stack
		assertNull(stack.pop());		
	}

	/**
	 * Create a stack containing 5 elements and try to pop elements.
	 * Assert that the elements of arrayDequeOfTestData equals the elements on
	 * the stack.
	 */
	@Test
	public void testPopFromStackWith5Elements() {

		int stackSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Check that the contents are equal
		while (!arrayDequeOfTestData.isEmpty()) {
			assertEquals(arrayDequeOfTestData.pop(), stack.pop());
		}
		// Test pop on an empty stack
		assertNull(stack.pop());
	}

	/**
	 * Create an empty stack and try to get the top element.
	 * Assert that the top() method returns null.
	 */
	@Test
	public void testTopOnEmptyStack() {

		int stackSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Test top() on an empty stack
		assertNull(stack.top());
	}

	/**
	 * Create a stack containing 5 elements and try to get each top element.
	 * Assert that the elements of arrayDequeOfTestData equals the elements of
	 * the stack.
	 */
	@Test
	public void testTopOnStackWith5Elements() {

		int stackSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Check that the contents are equal
		while (!arrayDequeOfTestData.isEmpty()) {
			System.out.println("testTopOnStackWith5Elements - top() = " + stack.top());
			assertEquals(arrayDequeOfTestData.peek(), stack.top());
			arrayDequeOfTestData.pop();
			stack.pop();
		}
	}

	/**
	 * Create an empty stack and try to search for an element.
	 * Assert that the top element equals null.
	 */
	@Test
	public void testSearchOnEmptyStack() {

		int stackSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Test search on an empty stack
		assertEquals(-1, stack.search("element0"));
	}

	/**
	 * Create a stack containing 5 elements and try to search for each element.
	 * Assert that the elements of arrayDequeOfTestData equals the elements of
	 * the stack.
	 */
	@Test
	public void testSearchOnStackWith5Elements() {

		int stackSize = 5;
		int index = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Check that the contents are equal
		while (!arrayDequeOfTestData.isEmpty()) {
			System.out.println("testSearchOnStackWith5Elements - search for " +
				arrayDequeOfTestData.peek() + " - index = " +
				stack.search(arrayDequeOfTestData.peek()));
			assertEquals(index++, stack.search(arrayDequeOfTestData.peek()));
			arrayDequeOfTestData.pop();
		}
	}

	/**
	 * Create an empty stack and convert to an array.
	 * Assert that the toArray() method returns null.
	 */
	@Test
	public void testToArrayOnEmptyStack()	{

		int stackSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Test that the returned value is null
		assertNull(stack.toArray());
	}

	/**
	 * Create a stack containing 5 elements and convert to an array.
	 * Assert that the ArrayDeque of test data equals the contents of the stack
	 * expressed as an array.
	 */
	@Test
	public void testToArrayOnStackWith5Elements()	{

		int stackSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Test that the arrays are equal
		assertTrue(Arrays.equals(arrayDequeOfTestData.toArray(), stack.toArray()));
	}

	/**
	 * Create an empty stack and convert to a string.
	 * Assert that the ArrayDeque of test data, converted to a string, equals the
	 * contents of the stack expressed as a string.
	 */
	@Test
	public void testToStringOnEmptyStack() {

		int stackSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Test that the strings are equal
		assertEquals(arrayDequeOfTestData.toString(), stack.toString());
	}

	/**
	 * Create a stack containing 5 elements and convert to a string.
	 * Assert that the ArrayDeque of test data, converted to a string, equals the
	 * contents of the stack expressed as a string.
	 */
	@Test
	public void testToStringOnStackWith5Elements() {

		int stackSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(stackSize);
		// Then create a stack consisting of the elements of the ArrayDeque
		IStack<Object> stack = createStackOfTestData(arrayDequeOfTestData);

		// Test that the strings are equal
		assertEquals(arrayDequeOfTestData.toString(), stack.toString());
	}
}