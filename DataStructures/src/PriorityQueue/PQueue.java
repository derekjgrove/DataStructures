package PriorityQueue;
import java.util.Arrays;

/**
 * COSC 310-001    Assignment 7
 * PQueue.java
 * 
 * Priority Queue implemented using a min heap. The queue is array-based.
 * Smallest item is always removed first from the priority queue.
 * 
 * @author  Derek J. Grove
 *
 */
public class PQueue<E> {
	private E[] pQueue;
	private int size = 0;

	/**
	 * Constructor to create an array of generics
	 * initialize size to zero
	 */
	public PQueue() {
		pQueue = (E[]) new Object[10];
		size = 0;
	}

	/**
	 * Add an item into the priority queue, determine if the queue satisfies
	 * the requirements of a min heap after insertion.
	 * Reallocate the array if size is equal to capacity.
	 * @param item - the item to added
	 * @return true if add was successful
	 */
	public boolean add(E item) {
		if (size >= pQueue.length - 1) {
			pQueue = Arrays.copyOf(pQueue, pQueue.length * 2);
		}

		pQueue[size++] = item;
		int child = size - 1;
		int parent = (child - 1) / 2;
		while (parent >= 0 && compare(pQueue[parent], pQueue[child]) > 0) {
			swap(parent, child);
			child = parent;
			parent = (child - 1) / 2;
		}
		return true;
	}

	/**
	 * Determine if there are no items in the array
	 * @return true if null
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Remove the smallest entry and return it if the queue is not empty.
	 * Move the last item to the root index and re-sort the queue
	 * Reallocate array capacity if there are un-needed slots.
	 * @return the root item before removed
	 */
	public E remove() {
		if (size <= pQueue.length / 2) {
			pQueue = Arrays.copyOf(pQueue, pQueue.length / 2);
		}

		E result = pQueue[0];
		pQueue[0] = pQueue[size - 1];
		size--;
		pQueue[size] = null;
		int parent = 0;
		while (true) {
			int leftChild = (2 * parent) + 1;
			int rightChild = leftChild + 1;
			if (leftChild >= size) {
				break;
			}
			int minChild = leftChild;
			if (rightChild < size
					&& compare(pQueue[leftChild], pQueue[rightChild]) > 0) {
				minChild = rightChild;
			}
			if (compare(pQueue[parent], pQueue[minChild]) > 0) {
				swap(parent, minChild);
				parent = minChild;
			} else {
				break;
			}
		}
		return result;
	}

	/**
	 * Swap parent and child if the child is less than the parent
	 * @param parent - the predecessor
	 * @param child - the successor
	 */
	private void swap(int parent, int child) {
		E tempParent = pQueue[parent];
		pQueue[parent] = pQueue[child];
		pQueue[child] = tempParent;
	}

	/**
	 * Compare two items using the natural ordering of the compareTo()
	 * @param left - left child
	 * @param right - right child
	 * @return negative if L < R and positive if R > L
	 */
	private int compare(E left, E right) {
		return ((Comparable<E>) left).compareTo(right);
	}

	/**
	 * toString method to read the data instead of memory address of
	 * the data
	 */
	@Override
	public String toString() {
		return "PQueue [pQueue=" + Arrays.toString(pQueue) + ", size=" + size + "]";
	}

	/**
	 * Test the priority queue implemented using a min heap.
	 * Output shows the array contents, minHeap[0] will be the root,
	 * minHeap[1] will be the start of the next level(level 1), minHeap[3]
	 * will be start of the following level(level 2) and so on.
	 * 
	 * @param args - none.
	 */
	public static void main(String[] args) {
		PQueue<Integer> minHeap = new PQueue<Integer>();

		minHeap.add(12);
		minHeap.add(2);
		minHeap.add(3);
		minHeap.add(18);
		minHeap.add(5);
		minHeap.add(10);
		minHeap.add(7);
		minHeap.add(8);
		minHeap.add(1);
		minHeap.add(6);

		System.out.println(minHeap);

		while (!minHeap.isEmpty()) {
			minHeap.remove();
			System.out.println(minHeap);
		}

	}

}
