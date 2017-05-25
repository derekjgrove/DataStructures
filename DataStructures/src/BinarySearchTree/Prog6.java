package BinarySearchTree;
import java.io.IOException;

/**
 * COSC 310-001    Assignment 6
 * Prog6.java
 * 
 * A client class that uses the EmployeeList constructor to populate a binary
 * search tree with Employee objects read from a file
 * 
 * @author  Derek J. Grove
 *
 */
public class Prog6 {

	/**
	 * Main method to run the program.
	 * @param args - none.
	 * @throws IOException - thrown if there is an error while trying to input
	 * 						 from the file.
	 */
	public static void main(String[] args) throws IOException {
		EmployeeList menu = new EmployeeList("employee2.txt");
		menu.run();
	}

}
