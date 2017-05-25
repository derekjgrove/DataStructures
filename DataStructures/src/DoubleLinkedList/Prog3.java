package DoubleLinkedList;
/**
 * COSC 310-001    Assignment 3
 * Prog3.java
 * 
 * A client class to create a menu-driven system that maintains
 * a list of students.
 * 
 * @author  Derek J. Grove
 *
 */
public class Prog3 {

	/**
	 * Main method to instantiate a menu.
	 * @param args - none.
	 */
	public static void main(String[] args) {
		System.out.println("Waiting List for COSC 1000");
		WaitListMenu menu = new WaitListMenu();
		menu.display();

	}

}
