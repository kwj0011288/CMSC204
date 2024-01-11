/*
 * @author Wonjae Kim
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.w3c.dom.Node;

//link list that go back and forth
public class BasicDoubleLinkedList<T> implements Iterable<T> {
	protected Node<T> head;
	protected Node<T> tail;
	protected int size;

	// initialize the value
	public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		size = 0;
	}

	// add the element to the end
	public void addToEnd(T data) {
		Node<T> node = new Node<>(data);
		if (isEmpty()) { // when the list is empty, set both head and tail to node
			head = node;
			tail = node;
		} else {
			tail.next = node;// since tail is the end, element should be added next tail
			node.prev = tail;// since this is doubly linked list, tail should be located before the element
			tail = node; // set tail as node
		}
		size++; // increment the size
	}

	// add the element to the front
	public void addToFront(T data) {
		Node<T> node = new Node<>(data);
		if (isEmpty()) { // when the list is empty, set both head and tail to node
			head = node;
			tail = node;
		} else {
			node.next = head;// since head is the front, set after node element as the head
			head.prev = node; // before head element is new element
			head = node; // set node as a head since it is in front of everything
		}
		size++;
	}

	public T getFirst() {
		if (size == 0) { // if size is 0, return null
			return null;
		}

		return head.getData(); // get head data
	}

	public T getLast() {
		return tail.getData(); // get tail data
	}

	public int getSize() { // get size
		return size;
	}

	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator();

	}

	@SuppressWarnings("hiding")
	public class Node<T> {
		private T data;
		private Node<T> prev;
		private Node<T> next;

		public Node(T data) {
			this.data = data;
			this.prev = null;
			this.next = null;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public Node<T> getPrev() {
			return prev;
		}

		public void setPrev(Node<T> prev) {
			this.prev = prev;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> next) {
			this.next = next;
		}

	}

	public boolean isEmpty() {
		return size == 0;
	}

	public BasicDoubleLinkedList<T>.Node<T> remove(T targetData, Comparator<T> comparator) {
		if (isEmpty()) {
			return null;
		}

		Node<T> current = head; // started from the head
		while (current != null) {
			if (comparator.compare(current.data, targetData) == 0) { // if targetData and data are the same
				if (head == current) {
					head = current.next; // if head is the targeted one, update the head
				} else {
					current.prev.next = current.next; // if head is not the target, update the next reference of the
														// previous node
				}

				if (tail == current) {
					tail = current.prev;// if tail is the targeted one, update the head
				} else {
					current.next.prev = current.prev;// if tail is not the target, update the prev reference of the next
														// node
				}
				size--; // decrement the size
				return current;
			}
			current = current.next; // move to next node
		}
		return null;
	}

	public T retrieveFirstElement() {
		if (size == 0) {
			return null;
		}

		T firstElement = head.data; // Get the data of the first element

		if (size == 1) {
			// Only one element in the list
			head = null;
			tail = null;
		} else {
			head = head.next; // Move the head to the next element
			if (head != null) {
				head.prev = null;
			}
		}

		size--; // Decrement the size of the list

		return firstElement; // Return the removed first element

	}

	/*
	 * Removes and returns the first element from the list. If there are no elements
	 * the method returns null. Do not implement this method using iterators.
	 */
	public T retrieveLastElement() {
		if (isEmpty()) { // if empty return null
			return null;
		}

		Node<T> last = tail; // reference to the last node
		tail = tail.prev; // set tail to previous node

		if (last.prev != null) {
			last.prev.next = null; // set second last node to null
		} else {
			head = null; // set fist node to null
		}
		tail = last.prev; // update the tail
		size--; // decrement the size

		return last.data;

	}

	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<>();

		Node<T> current = head; // set current to the first element

		if (isEmpty()) { // if empty, return the list
			return list;
		}
		while (current != null) {
			list.add(current.data); // add data to the list
			current = current.next; // move to the next node

			if (current == head) { // if reached back to the first node, break
				break;
			}
		}

		return list;// return the arraylist
	}

	/*
	 * A generic inner class named DoubleLinkedListIterator that implements java’s
	 * ListIterator interface (for the iterator method).
	 */
	public class DoubleLinkedListIterator implements ListIterator<T> {
		private Node<T> firstNode;
		private int index;
		private Node<T> lastNode;

		// constructor
		public DoubleLinkedListIterator() {
			firstNode = head;
			index = 0;
			lastNode = null;
		}

		public boolean hasNext() {
			return index < size;
		}

		public T next() throws NoSuchElementException {
			if (hasNext()) {
				T element = firstNode.getData(); // get data from the current node
				firstNode = firstNode.getNext(); // move to the second node
				index++; // increment the indexa
				return element; // return the data
			}
			throw new NoSuchElementException(); // else throw exception

		}

		public boolean hasPrevious() {
			return index > 0; // if it is bigger than 0, return true
		}

		public T previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (firstNode == null) {
				firstNode = tail; // if current node is null, set as the last node
			} else {
				firstNode = firstNode.getPrev(); // get previous node
			}
			lastNode = firstNode; // set lastnode to the current node
			index--; // decrement the index
			return lastNode.getData();
		}

		// from here, run UnsupportedOperationException
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();

		}

		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();

		}

		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();

		}

		public void add(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();

		}

	}

}
