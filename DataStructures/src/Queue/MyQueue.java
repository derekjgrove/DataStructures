package Queue;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * COSC 310-001    Assignment 5
 * MyQueue.java
 * 
 * A class that provides a linked list implementation of
 * a queue with a tail pointer to increase efficiency.
 * 
 * @author  Derek J. Grove
 *
 */
public class MyQueue<E> {
	private Node<E> front = null;
	private Node<E> rear = null;
	private int size;

	/**
	 * Node is a nested class to allocate the list to
	 * handle nodes.
	 * 
	 * @author Derek J. Grove
	 *
	 * @param <E> - generics
	 */
	private static class Node<E> {
		private E data;
		private Node<E> next;

		/**
		 * Constructor for Node if the node being created
		 * is the first one.
		 * @param dataItem - the data that is being pointed to.
		 */
		private Node(E dataItem) {
			data = dataItem;
			next = null;
		}

		/**
		 * Constructor for Node when a node is being
		 * added to the end of the list.
		 * @param dataItem - the data that is being pointed to.
		 * @param nodeRef - the pointer.
		 */
		private Node(E dataItem, Node<E> nodeRef) {
			data = dataItem;
			next = nodeRef;
		}
	}

	/**
	 * ListIter is a inner class that provides a more efficient
	 * way of traversing through the nodes.
	 * 
	 * @author Derek J. Grove
	 *
	 */
	public class ListIter implements Iterator<E> {
		private int index = 0;
		private Node<E> nextItem;
		private Node<E> lastItemReturned;

		/**
		 * Constructor for ListIter that begins at a certain index.
		 * @param inx
		 */
		public ListIter(int inx) {
			if (inx < 0 || inx > size) {
				throw new IndexOutOfBoundsException("Invalid Index" + inx);
			}

			if (inx == 0) {
				index = 0;
				nextItem = front;
			} else {
				nextItem = front;
				for (int i = 0; i < inx; i++) {
					nextItem = nextItem.next;
				}
			}
		}

		/**
		 * See if there is another item to be iterated over.
		 */
		@Override
		public boolean hasNext() {
			return nextItem != null;
		}

		/**
		 * Go to the next element in the list.
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
		 * method not implemented
		 */
		@Override
		public void remove() {
		}
	}

	/**
	 * Provide the iterator with the starting index
	 * @return the new ListIter at the starting index
	 */
	public Iterator<E> iterator() {
		return new ListIter(0);
	}

	/**
	 * Constructor for MyQueued
	 * front is the head attribute for the list
	 * rear is the tail attribute for the list
	 */
	public MyQueue() {
		front = null;
		rear = null;
		size = 0;
	}

	/**
	 * Enqueue the item onto the queue at the end.
	 * @param item - the item to be added.
	 */
	public void enqueue(E item) {
		Node<E> temp = rear;
		rear = new Node<E>(item, null);
		if (isEmpty()) {
			front = rear;
		} else {
			temp.next = rear;
		}
		size++;

	}

	/**
	 * Get the data element of the first node.
	 * @return the data of the first node.
	 */
	public E peek() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		} else {
			return front.data;
		}
	}

	/**
	 * Dequeue the node from the queue at the beggining.
	 * @return the data of the node being removed.
	 */
	public E dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		E item = front.data;
		front = front.next;
		size--;
		return item;
	}

	/**
	 * Get the size of the queue.
	 * @return the size.
	 */
	public int size() {
		return size;
	}

	/**
	 * Test to see if the queue is empty.
	 * @return true if the queue is empty.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * toString method to make the data field legible
	 * to an everyday reader.
	 */
	@Override
	public String toString() {
		return "MyQueue [front=" + front + ", rear=" + rear + ", size=" + size
				+ "]";
	}

	/**
	 * Main method to make sure the linked-list implementation of a queue
	 * is working correctly.
	 * @param args - none.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyQueue<String> myQueue = new MyQueue<String>();

		System.out.println("Add one to the queue \n");
		myQueue.enqueue("one");

		System.out.println("Peek at the front of the queue: " + myQueue.peek());
		System.out.println("Queue size: " + myQueue.size());
		System.out.println("Is the queue empty: " + myQueue.isEmpty());

		System.out.println("\n\n\n\nRemove one from the queue\n");
		myQueue.dequeue();
		try {
			System.out.println("Queue size: " + myQueue.size());
			System.out.println("Is the queue empty: " + myQueue.isEmpty());
			System.out.println("Attempt to peek at the empty queue: ");
			System.out.println(myQueue.peek());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("\n\n\n\nAdd one through five to the queue");
		myQueue.enqueue("one");
		myQueue.enqueue("two");
		myQueue.enqueue("three");
		myQueue.enqueue("four");
		myQueue.enqueue("five");
		System.out
				.println("While the queue has items, display the queue size, first item in the queue, and the item being dequeued");

		while (!myQueue.isEmpty()) {
			System.out.println("\nQueue size: " + myQueue.size());
			System.out.println("First item in the queue: " + myQueue.peek());
			System.out.println("Dequeue from the queue: " + myQueue.dequeue());
		}

	}

}
