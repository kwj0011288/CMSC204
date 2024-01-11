import java.util.ArrayList;
/**
 * @author Wonjae Kim
 */
public class MyStack<T> implements StackInterface<T> {

	private int stackSize;
	private int capacity;
	private int top;
	private T[] array;

	/**
	 * Provide two constructors 1. takes in an int as the size of the stack 2.
	 * default constructor - uses default as the size of the stack
	 */
	@SuppressWarnings("unchecked")
	public MyStack(int stackSize) {
		this.stackSize = stackSize;
		this.capacity = stackSize;
		this.top = -1;
		this.array = (T[]) new Object[capacity];

	}

	public MyStack() {

	}

	/**
	 * Determines if Stack is empty
	 * 
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty() {
		if (top == -1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Determines if Stack is full
	 * 
	 * @return true if Stack is full, false if not
	 */
	public boolean isFull() {
		return top == capacity - 1;
	}

	/**
	 * Deletes and returns the element at the top of the Stack
	 * 
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException("Stack is empty");
		}

		T element = array[top];
		top--;
		return element;

	}

	/**
	 * Returns the element at the top of the Stack, does not pop it off the Stack
	 * 
	 * @return the element at the top of the Stack
	 * @throws StackUnderflowException if stack is empty
	 */
	public T top() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException("Stack is empty");
		}
		return array[top];
	}

	/**
	 * Number of elements in the Stack
	 * 
	 * @return the number of elements in the Stack
	 */
	public int size() {
		return top + 1;

	}

	/**
	 * Adds an element to the top of the Stack
	 * 
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 * @throws StackOverflowException if stack is full
	 */
	public boolean push(T e) throws StackOverflowException {
		if (isFull()) {
			throw new StackOverflowException("Stack is Full");
		}
		top++;
		array[top] = e;
		return true;
	}

	/**
	 * Returns the elements of the Stack in a string from bottom to top, the
	 * beginning of the String is the bottom of the stack
	 * 
	 * @return an string which represent the Objects in the Stack from bottom to top
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i <= top; i++) {
			result += array[i];

		}
		return result;
	}

	/**
	 * Returns the string representation of the elements in the Stack, the beginning
	 * of the string is the bottom of the stack Place the delimiter between all
	 * elements of the Stack
	 * 
	 * @return string representation of the Stack from bottom to top with elements
	 *         separated with the delimiter
	 */
	public String toString(String delimiter) {
		String result = "";
		for (int i = 0; i <= top; i++) {
			result += array[i];
			if (i < top) {
				result += delimiter;
			}

		}
		return result;
	}

	/**
	 * Fills the Stack with the elements of the ArrayList, First element in the
	 * ArrayList is the first bottom element of the Stack YOU MUST MAKE A COPY OF
	 * LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the list reference
	 * within your Stack, you will be allowing direct access to the data of your
	 * Stack causing a possible security breech.
	 * 
	 * @param list elements to be added to the Stack from bottom to top
	 * @throws StackOverflowException if stack gets full
	 */
	public void fill(ArrayList<T> list) throws StackOverflowException {
		if (list.size() > stackSize) {
			throw new StackOverflowException("Stack is full");
		}

		ArrayList<T> copy = new ArrayList<>(list);

		for (int i = 0; i < copy.size(); i++) {
			T element = copy.get(i);
			push(element);
		}
	}
}
