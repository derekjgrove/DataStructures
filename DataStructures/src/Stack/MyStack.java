package Stack;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * COSC 310-001    Assignment 4
 * MyStack.java
 * 
 * A Stack implemented using an array with generic E
 * instead of using type object.
 * 
 * @author  Derek J. Grove
 *
 */
public class MyStack<E> {
	private E[] data;
	private int top;

	/**
	 * Constructor if the array capacity is
	 * not specified.
	 */
	public MyStack() {
		data = (E[]) new Object[10];
		top = -1;
	}

	/**
	 * Constructor if the array capacity
	 * is specified.
	 * @param capacity - array size.
	 */
	public MyStack(int capacity) {
		data = (E[]) new Object[capacity];
		top = -1;
	}

	/**
	 * Push an item to the top of the stack.
	 * @param item
	 */
	public void push(E item) {
		data[++top] = item;
	}

	/**
	 * Peek at the top element, but do not take
	 * the element out of the stack.
	 * @return - the data of the element on the top
	 * of the stack.
	 */
	public E peek() {
		if (top < 0) {
			throw new EmptyStackException();
		}
		return data[top];
	}

	/**
	 * Pop the element from the stack
	 * (Take the top element out of the stack).
	 * @return the data of the top element
	 */
	public E pop() {
		if (top < 0) {
			throw new EmptyStackException();
		} else {
			top--;
			return data[top + 1];
		}
	}

	/**
	 * Test the array if it is empty.
	 * @return true if array is empty.
	 */
	public boolean isEmpty() {
		return top < 0;
	}

	/**
	 * Convert the data from a memory address
	 * to a string, so it is legible.
	 */
	@Override
	public String toString() {
		return "MyStack [data=" + Arrays.toString(data) + ", top=" + top + "]";
	}

	/**
	 * Test the stack to make sure it is operating
	 * correctly.
	 * @param args - none.
	 */
	public static void main(String[] args) {
		MyStack<String> myStack = new MyStack<String>();
		
		System.out.println("Push one through five to the stack.");
		myStack.push("one");
		myStack.push("two");
		myStack.push("three");
		myStack.push("four");
		myStack.push("five");
		
		System.out.println("\nIs stack empty(Should be false): " + myStack.isEmpty());
		
		System.out.println("\nWhat is the top element of the stack: " + myStack.peek());
		
		System.out.println("\nPop the entire stack");
		System.out.print("Stack elements popped: ");
		while (!myStack.isEmpty()) {
			System.out.print(myStack.pop() + " ");
		}

		System.out.println("\n\nIs stack empty(Should be true): " + myStack.isEmpty());
	}

}
