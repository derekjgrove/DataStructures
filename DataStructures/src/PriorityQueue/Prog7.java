package PriorityQueue;
import java.io.IOException;

/**
 * COSC 310-001    Assignment 7
 * Prog7.java
 * 
 * A client class that instantiates the AstroReport class,
 * passes the file and runs the program.
 * 
 * @author  Derek J. Grove
 *
 */
public class Prog7 {

	/**
	 * Main method to run the program.
	 * @param args - none.
	 * @throws IOException - thrown if there is an error while trying to input
	 * 						 from the file.
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AstroReport astro = new AstroReport("astro.txt");
		astro.run();

	}

}
