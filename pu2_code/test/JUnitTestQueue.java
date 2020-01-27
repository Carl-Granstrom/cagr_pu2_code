

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
public class JUnitTestQueue {

	/**
	 * Creates an ArrayDeque of <code>queueSize</code> strings ["element0", "element1", ... ].
	 * @param <code>queueSize</code> the number of strings to be generated
	 * @return an ArrayDeque of <code>queueSize</code> strings ["element0", "element1", ... ]
	 * @throws IllegalArgumentException if <code>queueSize</code> &lt; 0
	 */
	private ArrayDeque<Object> generateArrayDequeOfTestData(int queueSize)
			throws IllegalArgumentException {

		// Check whether parameter queueSize is valid or not
		if (queueSize < 0) {
			throw new IllegalArgumentException("generateArrayDequeOfTestData - illegal argument: queueSize < 0");
		}

		// First create an ArrayDeque
		ArrayDeque<Object> arrayDequeOfTestData = new ArrayDeque<Object>(queueSize);
		// Then add the specified number of string elements
		for (int i = 0; i < queueSize; i++) {
			arrayDequeOfTestData.offer("element" + i);
		}
		return arrayDequeOfTestData;
	}

	/**
	 * Create a queue containing the elements as specified by the given ArrayDeque.
	 * @param <code>arrayDequeOfTestData</code> the ArrayDeque that contains the
	 *        elements to be added to the queue
	 * @return a queue containing the elements as specified by the given
	 *         ArrayDeque of elements
   * @throws NullPointerException if the specified ArrayDeque equals null
	 */
	private IQueue<Object> createQueueOfTestData(ArrayDeque<Object> arrayDequeOfTestData)
			throws NullPointerException {

		// Check whether parameter arrayDequeOfTestData is valid or not
		if (arrayDequeOfTestData == null) {
			throw new NullPointerException("createQueueOfTestData - illegal argument: arrayDequeOfTestData == null");
		}

		// First create a queue
		IQueue<Object> queueOfTestData = new Queue<Object>();
		// Then create an iterator over the elements
		Iterator<Object> iterArrayDequeOfTestData = arrayDequeOfTestData.iterator();
		// Finally add the elements from the Iterator to the queue
		while (iterArrayDequeOfTestData.hasNext()) {
			queueOfTestData.offer(iterArrayDequeOfTestData.next());
		}

		return queueOfTestData;
	}

	/**
	 * Create a queue containing the elements as specified by the generated
	 * ArrayDeque of elements.
	 * @param <code>queueSize</code> the number of strings to be generated
	 * @return a queue containing the elements as specified by the generated
	 *         ArrayDeque of elements
	 * @throws IllegalArgumentException if <code>queueSize</code> &lt; 0

	 */
	private IQueue<Object> createQueueOfTestData(int queueSize)
			throws IllegalArgumentException {

		// Check whether parameter queueSize is valid or not
		if (queueSize < 0) {
			throw new IllegalArgumentException("createQueueOfTestData - illegal argument: queuesize < 0");
		}

		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queueOfTestData = createQueueOfTestData(arrayDequeOfTestData);

		return queueOfTestData;
	}

	/**
	 * Create a queue of <code>queueSize</code> elements.
	 * Assert that the queue not equals null, i.e. actually contains something.
	 */
	@Test
	public void testOffer5ElementsOnEmptyQueue() {

		int queueSize = 5;
		IQueue<Object> queue = createQueueOfTestData(queueSize);

		// Test that queue is not null
		assertNotNull(queue);
	}

	/**
	 * Create an empty queue and test size.
	 * Assert that size of queue is 0.
	 */
	@Test
	public void testSizeOfEmptyQueue() {

		int queueSize = 0;
		IQueue<Object> queue = createQueueOfTestData(queueSize);

		// Test size after initial setup
		assertEquals(queueSize, queue.size());
	}

	/**
	 * Create a queue containing 5 elements and test size.
	 * Assert that size of queue is 5.
	 */
	@Test
	public void testSizeOfQueueWith5Elements() {

		int queueSize = 5;
		IQueue<Object> queue = createQueueOfTestData(queueSize);

		// Test size after initial setup
		assertEquals(queueSize, queue.size());
	}

	/**
	 * Create an empty queue, offer an element and test size.
	 * Assert that size of queue is 1.
	 */
	@Test
	public void testOfferOneElementOnEmptyStack() {

		int queueSize = 0;
		IQueue<Object> queue = createQueueOfTestData(queueSize);

		queue.offer(42);
		// Test size after offer operation
		assertEquals(queueSize + 1, queue.size());
	}

	/**
	 * Create a queue containing 5 elements, offer an element and test size.
	 * Assert that size of queue is 6.
	 */
	@Test
	public void testOfferOneElementOnStackWith5Elements() {

		int queueSize = 5;
		IQueue<Object> queue = createQueueOfTestData(queueSize);

		queue.offer(42);
		// Test size after offer operation
		assertEquals(queueSize + 1, queue.size());
	}

	/**
	 * Create an empty queue and try to poll an element.
	 * Assert that the poll() method returns null.
	 */
	@Test
	public void testPollFromEmptyQueue() {

		int queueSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Test poll() on an empty queue
		assertNull(queue.poll());		
	}

	/**
	 * Create a queue containing 5 elements and try to poll elements.
	 * Assert that the elements of arrayDequeOfTestData equals the elements on
	 * the queue.
	 */
	@Test
	public void testPollFromQueueWith5Elements() {

		int queueSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Check that the contents are equal
		while (!arrayDequeOfTestData.isEmpty()) {
			assertEquals(arrayDequeOfTestData.pop(), queue.poll());
		}
		// Test poll on an empty queue
		assertNull(queue.poll());
	}

	/**
	 * Create an empty queue and try to peek at the first element.
	 * Assert that the peek() method returns null.
	 */
	@Test
	public void testPeekAtEmptyQueue() {

		int queueSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Test peek() on an empty queue
		assertNull(queue.peek());
	}

	/**
	 * Create a queue containing 5 elements and try to peek at each element.
	 * Assert that the elements of arrayDequeOfTestData equals the elements of
	 * the queue.
	 */
	@Test
	public void testPeekAtQueueWith5Elements() {

		int queueSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Check that the contents are equal
		while (!arrayDequeOfTestData.isEmpty()) {
			System.out.println("testPeekOnQueueWith5Elements - peek() = " + queue.peek());
			assertEquals(arrayDequeOfTestData.peek(), queue.peek());
			arrayDequeOfTestData.poll();
			queue.poll();
		}
	}

	/**
	 * Create an empty queue and convert to an array.
	 * Assert that the toArray() method returns null.
	 */
	@Test
	public void testToArrayOnEmptyQueue()	{

		int queueSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Test that the returned value is null
		assertNull(queue.toArray());
	}

	/**
	 * Create a queue containing 5 elements and convert to an array.
	 * Assert that the ArrayDeque of test data equals the contents of the queue
	 * expressed as an array.
	 */
	@Test
	public void testToArrayOnQueueWith5Elements()	{

		int queueSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Test that the arrays are equal
		assertTrue(Arrays.equals(arrayDequeOfTestData.toArray(), queue.toArray()));
	}

	/**
	 * Create an empty queue and convert to a string.
	 * Assert that the ArrayDeque of test data, converted to a string, equals the
	 * contents of the queue expressed as a string.
	 */
	@Test
	public void testToStringOnEmptyQueue() {

		int queueSize = 0;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Test that the strings are equal
		assertEquals(arrayDequeOfTestData.toString(), queue.toString());
	}

	/**
	 * Create a queue containing 5 elements and convert to a string.
	 * Assert that the ArrayDeque of test data, converted to a string, equals the
	 * contents of the queue expressed as a string.
	 */
	@Test
	public void testToStringOnQueueWith5Elements() {

		int queueSize = 5;
		// First generate an ArrayDeque with string elements that constitutes the
		// test data
		ArrayDeque<Object> arrayDequeOfTestData = generateArrayDequeOfTestData(queueSize);
		// Then create a queue consisting of the elements of the ArrayDeque
		IQueue<Object> queue = createQueueOfTestData(arrayDequeOfTestData);

		// Test that the strings are equal
		assertEquals(arrayDequeOfTestData.toString(), queue.toString());
	}
}