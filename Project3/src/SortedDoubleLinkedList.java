/*
 * @author Wonjae Kim
 */

import java.util.Comparator;
import java.util.ListIterator;

/*
 * Implements a generic sorted double list using a provided Comparator. 
 * It extends BasicDoubleLinkedList class.
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	private Comparator<T> comparator;

	/*
	 * Creates an empty list that is associated with the specified comparator.
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	/*
	 * Inserts the specified element at the correct position in the sorted list.
	 */
	public void add(T data) {
		Node<T> node = new Node<>(data);
		if (isEmpty()) { // if linked list is empty, set both head and tail as new element
			head = node;
			tail = node;

			/*
			 * if comparison between data and head is less than 0, insert the element before
			 * head
			 */
		} else if (comparator.compare(data, head.getData()) < 0) {
			node.setNext(head);
			head.setPrev(node);
			head = node;
			/*
			 * if comparison between data and head is greater or equals to 0, insert the
			 * element after head
			 */
		} else if (comparator.compare(data, tail.getData()) >= 0) {
			node.setPrev(tail);
			tail.setNext(node);
			tail = node;
		} else {
			/*
			 * set current as a next element since new element is greather than or equal to
			 * the head
			 */
			Node<T> current = head.getNext();
			// loop it until find the correct position to add
			while (comparator.compare(data, current.getData()) > 0) {
				current = current.getNext();
			}

			Node<T> previous = current.getPrev(); // get previous node from current
			/*
			 * Insert the new node into the sorted linked list by updating the links of the
			 * surrounding nodes
			 */

			previous.setNext(node);
			node.setPrev(previous);
			node.setNext(current);
			current.setPrev(node);
		}
		size++; // increment the size
	}

	/*
	 * This operation is invalid for a sorted list.
	 */
	@Override
	public void addToFront(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/*
	 * This operation is invalid for a sorted list.
	 */
	@Override
	public void addToEnd(T data) throws UnsupportedOperationException {
		throw new UnsupportedOperationException("Invalid operation for sorted list");
	}

	/*
	 * Implements the iterator by calling the super class iterator method.
	 */
	public ListIterator<T> iterator() {
		return super.iterator();

	}

	/*
	 * Implements the remove operation by calling the super class remove method.
	 */
	public BasicDoubleLinkedList<T>.Node<T> remove(T data, Comparator<T> comparator) {
		return super.remove(data, comparator);

	}

}
