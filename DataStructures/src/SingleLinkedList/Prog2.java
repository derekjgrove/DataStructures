package SingleLinkedList;
import java.io.IOException;

/**
 * COSC 310-001    Assignment 2
 * Prog2.java
 * 
 * A client class to create a menu-driven system that maintains
 * a list of parts.
 * 
 * @author  Derek J. Grove
 *
 */
public class Prog2 {

	/**
	 * Main method to create the menu-driven system.
	 * @param args - none
	 * @throws IOException - thrown when there is an error while
	 * infiling from the CSV file
	 */
	public static void main(String[] args) throws IOException {
		PartsMenu partsMenu = new PartsMenu("parts.txt");
		partsMenu.display();
	}

}
