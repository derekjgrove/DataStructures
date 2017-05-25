package SingleLinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * COSC 310-001    Assignment 2
 * SLList.java
 * 
 * A Single Linked List of nodes that provides methods
 * from the Java.util Class.
 * 
 * @author  Derek J. Grove
 *
 */
public class SLList<E extends Comparable> {

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

		/**
		 * Constructor for a node that does not reference
		 * another node.
		 * @param dataItem - the data stored
		 */
		private Node(E dataItem) {
			data = dataItem;
			next = null;
		}

		/**
		 * Constructor for a node with a reference to 
		 * another node.
		 * @param dataItem - the data stored
		 * @param nodeRef - the node referenced by the node
		 */
		private Node(E dataItem, Node<E> nodeRef) {
			data = dataItem;
			next = nodeRef;
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
	public class ListIter implements Iterator<E> {
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

			if (inx == 0) {
				index = 0;
				nextItem = head;
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
		 * @return the next node
		 */
		@Override
		public boolean hasNext() {
			return nextItem != null;
		}

		/**
		 * Get the data of the next node in the list
		 * @return the next node with its reference pointer
		 * @param lastItemReturned - temp variable
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
		 * Unimplemented Method
		 */
		@Override
		public void remove() {
		}
	}

	/**
	 * Constructor for the iterator
	 * @return a new iterator starting at index 0
	 */
	public Iterator<E> iterator() {
		return new ListIter(0);
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
	public void add(E item) {
		if (head == null) {
			head = new Node<E>(item, null);
			size++;
		} else {
			head = new Node<E>(item, head);
			size++;
		}
	}

	/**
	 * Insert the node at the index
	 * @param index - the position within the list
	 * @param element - the node to be inserted
	 */
	public void addNode(int index, E element) {
		if (index == 0) {
			add(element);
		} else {
			Node<E> node = getNode(index - 1);
			node.next = new Node<E>(element, node.next);
			size++;
		}
	}

	/**
	 * Append the node to the end of the list
	 * @param e - the node to be appended
	 * @return true
	 */
	public boolean addNode(E e) {
		addNode(size, e);
		return true;
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

	/**
	 * Get the string formatted data item of the node to display
	 * to the console instead of displaying the memory address
	 */
	public String toString() {
		Node<E> node = head;
		String str = "";
		while (node != null) {
			str = str + node.data.toString() + "\n";
			node = node.next;
		}
		return str;
	}
	
	public static void main(String args[]) {
		SLList<String> list = new SLList<String>();
		
		System.out.println("Add items to the head of the list\n\n");
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
	
		System.out.println("List should be: \nfour\nthree\ntwo\none\n");
		System.out.print("Got:\n" + list);
		System.out.println("\n");
		
		System.out.print("Find index at three(Should get 1): ");
		System.out.println(list.find("three"));
		System.out.println("\n");
		
		System.out.print("Get the first node data(Should be four): ");
		System.out.println(list.get(0));
		System.out.println("\n");
		
		System.out.print("Get the first node(Should be the referenced memory address): ");
		System.out.println(list.getNode(0));
		System.out.println("\n");
		
		System.out.print("Get the size of the list(Should be 4): ");
		System.out.println(list.size());
		System.out.println("\n");
		
		System.out.print("Remove the first Node and display the data being deleted(Should be four): ");
		System.out.println(list.remove(0));
		System.out.println("\n");
		
		System.out.println("Display new list Should be: \nthree\ntwo\none\n");
		System.out.println("Got:\n" + list);
		
		System.out.println("Add four at index 1 \n");
		list.addNode(1, "four");
		
		System.out.println("Display new list Should be: \nthree\nfour\ntwo\none\n");
		System.out.println("Got:\n" + list);
		
		System.out.println("Size of the list: " + list.size());
		System.out.println("Delete the list\n");
		
		int originalSize = list.size();
		for (int i = 0; i < originalSize; i++) {
			list.remove(0);
		}
		System.out.println("Size of the list(Should be 0): " + list.size());
		
		System.out.println("Display list(Should have nothing): ");
		System.out.println("Got:\n" + list);
	}
	
}