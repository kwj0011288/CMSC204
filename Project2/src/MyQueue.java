import java.util.ArrayList;
/**
 * @author Wonjae Kim
 */
public class MyQueue<T> implements QueueInterface<T> {

	private int queueSize;
	private ArrayList<T> queue;

	/**
	 * provide two constructors 1. takes an int as the size of the queue 2. default
	 * constructor - uses a default as the size of the queue
	 * 
	 */
	public MyQueue(int queueSize) {
		this.queueSize = queueSize;
		this.queue = new ArrayList<>();
	}

	public MyQueue() {

	}

	/**
	 * Determines if Queue is empty
	 * 
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty() {
		if (queue.size() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Determines of the Queue is Full
	 * 
	 * @return true if Queue is full, false if not
	 */
	public boolean isFull() {
		if (queue.size() == queueSize) {
			return true;
		}
		return false;
	}

	/**
	 * Deletes and returns the element at the front of the Queue
	 * 
	 * @return the element at the front of the Queue
	 * @throws QueueUnderflowException if queue is empty
	 */
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
			throw new QueueUnderflowException("Queue is empty");
		}
		return queue.remove(0);
	}

	/**
	 * Returns number of elements in the Queue
	 * 
	 * @return the number of elements in the Queue
	 */
	public int size() {
		return queue.size();
	}

	/**
	 * Adds an element to the end of the Queue
	 * 
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful
	 * @throws QueueOverflowException if queue is full
	 */
	public boolean enqueue(T e) throws QueueOverflowException {
		if (isFull()) {
			throw new QueueOverflowException("Queue is full");
		}
		return queue.add(e);
	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning
	 * of the string is the front of the queue
	 * 
	 * @return string representation of the Queue with elements
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < queue.size(); i++) {
			result += queue.get(i);
			if (i < queue.size() - 1) {
				result += "";
			}
		}
		return result;

	}

	/**
	 * Returns the string representation of the elements in the Queue, the beginning
	 * of the string is the front of the queue Place the delimiter between all
	 * elements of the Queue
	 * 
	 * @return string representation of the Queue with elements separated with the
	 *         delimiter
	 */
	public String toString(String delimiter) {
		String result = "";
		for (int i = 0; i < queue.size(); i++) {
			result += queue.get(i);
			if (i < queue.size() - 1) {
				result += delimiter;
			}
		}
		return result;

	}

	/**
	 * Fills the Queue with the elements of the ArrayList, First element in the
	 * ArrayList is the first element in the Queue YOU MUST MAKE A COPY OF LIST AND
	 * ADD THOSE ELEMENTS TO THE QUEUE, if you use the list reference within your
	 * Queue, you will be allowing direct access to the data of your Queue causing a
	 * possible security breech.
	 * 
	 * @param list elements to be added to the Queue
	 * @throws QueueOverflowException if queue is full
	 * 
	 */
	public void fill(ArrayList<T> list) throws QueueOverflowException {
		if (list.size() > queueSize) {
			throw new QueueOverflowException("Queue is full");
		}

		ArrayList<T> copy = new ArrayList<>(list);

		for (T element : copy) {
			if (isFull()) {
				throw new QueueOverflowException("Queue capacity exceeded. Cannot fill the Queue.");
			}
			queue.add(element);
		}

	}

}
