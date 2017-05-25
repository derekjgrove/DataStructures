package DoubleLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * COSC 310-001    Assignment 3
 * DLList.java
 * 
 * A Double Linked List of nodes that provides methods
 * from the Java.util Class.
 * 
 * @author  Derek J. Grove
 *
 */
public class DLList<E extends Comparable> {

	private Node<E> head = null;
	private Node<E> tail = null;
	private int size;

	/**
	 * Nested Class to allocate the list to handle Nodes
	 *
	 * @author Derek J. Grove
	 *
	 * @param <E> - Generic Data Type
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next;
		private Node<E> prev;

		/**
		 * Constructor for a node that does not reference
		 * another node.
		 * @param dataItem - the data stored
		 */
		public Node(E data, Node<E> next, Node<E> prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	/**
	 * Inner class that implements the iterator. the ListIter
	 * points to a given node while still having a reference to
	 * the previous data.
	 * 
	 * @author Derek J. Grove
	 *
	 */
	class ListIter implements Iterator<E> {
		private int index = 0;
		private Node<E> nextItem;
		private Node<E> lastItemReturned;

		/**
		 * Constructor for ListIter, traversing through the list
		 * @param inx - the index value for the list of nodes
		 */
		public ListIter(int inx) {
			if (inx < 0 || inx > size) {
				throw new IndexOutOfBoundsException("Invalid Index" + inx);
			}

			if (inx == size) {
				index = size;
				nextItem = null;
			} else {
				nextItem = head;
				for (int i = 0; i < inx; i++) {
					nextItem = nextItem.next;
				}
			}
		}

		/**
		 * Testing the node to see if it references the next node
		 * in the list.
		 * @return true
		 */
		@Override
		public boolean hasNext() {
			return nextItem != null;
		}

		/**
		 * Get the data of the next node in the list
		 * @return the next node with its reference pointer
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			index++;
			return lastItemReturned.data;
		}

		/**
		 * Testing the node to see if it references the previous
		 * node in the list.
		 * @return true
		 */
		public boolean hasPrevious() {
			return (nextItem == null && size != 0) || (nextItem.prev != null);
		}

		/**
		 * Get the data of the previous node in the list
		 * @return the previous node with its reference pointer
		 */
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			if (nextItem == null) {
				nextItem = tail;
			} else {
				nextItem = nextItem.prev;
			}
			lastItemReturned = nextItem;
			index--;
			return lastItemReturned.data;
		}

		/**
		 * Method not implemented in this case.
		 */
		@Override
		public void remove() {
		}

	}

	/**
	 * Returns a iterator at index 0 or the head attribute.
	 * @return the iterator
	 */
	public Iterator<E> iterator() {
		return new ListIter(0);
	}

	/**
	 * Returns a iterator at the last index or the tail attribute.
	 * @return the reverse iterator
	 */
	public Iterator<E> revIterator() {
		return new ListIter(size);
	}

	/**
	 * Get the data value at index
	 * @param index - the position of the element to return
	 * @return the data referenced by the index
	 */
	public E get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index);
		return node.data;
	}

	/**
	 * Get the node at the index
	 * @param index - the position of the node
	 * @return the node at the specified index
	 */
	private Node<E> getNode(int index) {
		Node<E> node = head;
		for (int i = 0; i < index && node != null; i++) {
			node = node.next;
		}
		return node;
	}

	/**
	 * Set the data referenced by the node
	 * @param index - the index of the node being referenced
	 * @param item - the dataItem referenced by the node
	 * @return the data of the specified node
	 */
	public void set(int index, E item) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		Node<E> node = getNode(index);
		E result = node.data;
		node.data = item;
		return;
	}

	/**
	 * Get the size of the list
	 * @return the size
	 */
	public int size() {
		return size;
	}

	/**
	 * Remove the first node from the list while maintaining
	 * the structure of the list
	 * @return the removed node's referenced data
	 */
	private E removeFirst() {
		Node<E> temp = head;
		if (head != null) {
			head = head.next;
		}
		if (temp != null) {
			size--;
			return temp.data;
		} else {
			return null;
		}
	}

	/**
	 * Delete the node from  the list at the given index
	 * @param i - the position where the node is to be deleted
	 * @return the next node
	 */
	public E remove(int i) {
		if (i < 0 || i > size) {
			throw new IndexOutOfBoundsException();
		}
		if (i == 0) {
			return removeFirst();
		}
		if (i > 0 || i < (size - 1)) {
			Node<E> node = getNode(i - 1);
			Node<E> temp = node.next;
			node.next = temp.next;
			node.prev = temp.prev;
			size--;
			return temp.data;
		} else
			return null;
	}

	/**
	 * Remove the node from the list
	 * @param o - the node to be removed
	 * @return
	 */
	public boolean remove(E o) {
		int i = find(o);
		remove(i);
		return true;
	}

	/**
	 * Add an item to the front of the list while maintaining
	 * the structure of the list
	 * @param item - the new node
	 */
	public void add(E element) {
		if (head == null) {
			head = new Node<E>(element, null, null);
			tail = head;
			size++;
		} else {
			tail.next = new Node<E>(element, null, tail);
			tail = tail.next;
			size++;
		}
	}

	/**
	 * Searches for the target and returns the position of
	 * the first occurrence
	 * @param target - the node to be founded
	 * @return the index of the occurrence
	 */
	public int find(E target) {
		int index = 0;
		Node<E> node = head;

		if (target.compareTo(node.data) == 0) {
			return index;
		}

		while (index < size) {
			if (target.compareTo(node.data) == 0) {
				return index;
			}
			node = node.next;
			index++;
		}
		return -1;

	}

}
