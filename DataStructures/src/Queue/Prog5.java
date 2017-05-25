package Queue;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 5
 * Prog5.java
 * 
 * A client class that instantiates the simulation class.
 * Provides the amount of customers that the simulation
 * will simulate.
 * 
 * @author  Derek J. Grove
 *
 */
public class Prog5 {
	private static Scanner input = new Scanner(System.in);
	private static int noCustomers = 0;

	/**
	 * Input and output to describe the simulator and input the
	 * the amount of customers the user would like to simulate.
	 */
	public static void display() {
		System.out.println("Welcome to the ice cream shop simulator.\n\n");
		System.out.print("How many customers will be simulated: ");
		noCustomers = input.nextInt();
		System.out.println("\n");
	}

	/**
	 * Call the display and instantiate the simulator with the user
	 * input. Run the simulator and display the statistics after.
	 * @param args - none.
	 */
	public static void main(String[] args) {
		display();
		Simulation iceCreamSim = new Simulation(noCustomers);
		iceCreamSim.run();
		iceCreamSim.stats();

	}

}
