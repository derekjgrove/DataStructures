package Stack;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 4
 * Prog4.java
 * 
 * A class to create a new postfix calculator.
 * 
 * @author  Derek J. Grove
 *
 */
public class Prog4 {
	private static Scanner input = new Scanner(System.in);

	/**
	 * Main method to prompt the user for a string of integers and
	 * operators to send to eval();
	 * Print the result of the calculation.
	 * @param args - none.
	 */
	public static void main(String[] args) {
		System.out.println("Welcome to the postfix calculator!\n");
		System.out.println("Please enter a series of integers and "
				+ "algabraeic expressions separated by a space.");
		System.out.print("Calculation: ");
		String line = input.nextLine();
		EvalPostfix p = new EvalPostfix(line);
		int result = p.eval();
		System.out.println(result);
	}

}
