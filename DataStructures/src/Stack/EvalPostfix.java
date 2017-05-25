package Stack;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 4
 * EvalPostfix.java
 * 
 * Evaluate the string provided by the test class (Prog4)
 * and push the result to the stack.
 * 
 * @author  Derek J. Grove
 *
 */
public class EvalPostfix {
	private String post;

	/**
	 * Constructor to retrieve the string from Prog4
	 * @param post - the string of integers and operations.
	 */
	public EvalPostfix(String post) {
		this.post = post;
	}

	/**
	 * Evaluate the expression.
	 * Step1: split the expression into fields.
	 * Step2: create a new stack and set the length of the array
	 *        to how many fields there are.
	 * Step3: for as long as there are fields, test each character
	 *        if it is an integer, operation, or anything else,
	 *        and perform the operation provided.
	 * Step4: convert the character array into a string and parse
	 *        the string to an integer.
	 * Step5: try to push the integer to the stack.
	 * @return the result popped from the stack.
	 */
	public int eval() {
		String[] fields = post.split(" ");
		MyStack<Integer> myStack = new MyStack<Integer>(fields.length);
		for (int i = 0; i < fields.length; i++) {
			String currentField = fields[i];
			char[] numberBuilder = new char[currentField.length()];
			for (int j = 0; j < currentField.length(); j++) {
				if (Character.isDigit(currentField.charAt(j))) {
					numberBuilder[j] = currentField.charAt(j);
				} else if ((currentField.charAt(j) == '-')) {
					int right = myStack.pop();
					int left = myStack.pop();
					myStack.push(left - right);
				} else if ((currentField.charAt(j) == '+')) {
					int right = myStack.pop();
					int left = myStack.pop();
					myStack.push(left + right);
				} else if ((currentField.charAt(j) == '*')) {
					int right = myStack.pop();
					int left = myStack.pop();
					myStack.push(left * right);
				} else if ((currentField.charAt(j) == '/')) {
					int right = myStack.pop();
					int left = myStack.pop();
					myStack.push(left / right);
				} else {
					System.out.println("Error: " + "'" + currentField.charAt(j)
							+ "'" + " is not valid\n\n\n");
				}
			}
			String numberField = new String(numberBuilder);
			try {
				myStack.push(Integer.parseInt(numberField));
			} catch (Exception e) {

			}
		}
		return myStack.pop();
	}
}
