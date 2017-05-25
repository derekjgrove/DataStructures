package Queue;
import java.util.Random;

/**
 * COSC 310-001    Assignment 5
 * Simulation.java
 * 
 * A class that instantiates two queues, waitQ and arrivalQ, to
 * simulate how a line is dealt with when there is only one
 * employee available.
 * 
 * @author  Derek J. Grove
 *
 */
public class Simulation {

	private MyQueue<Customer> waitQ = new MyQueue<Customer>();
	private MyQueue<Customer> arrivalQ = new MyQueue<Customer>();
	private int time = 0;
	private int noCustomers = 0;
	private int longestTimeInLine = 0;
	private int totalTimeInLine = 0;
	private int longestLine = 1;
	private int linePerSecond = 1;
	private Random random = new Random();

	/**
	 * Constructor that creates as many customer objects that the
	 * user specifies, and then are enqueued into the arrivalQ.
	 * @param noCustomers - the number of customers, specified
	 * 						by the user.
	 */
	public Simulation(int noCustomers) {
		this.noCustomers = noCustomers;
		for (int i = 0; i < noCustomers; i++) {
			time += random.nextInt(4);
			Customer customer = new Customer(time, i + 1, 1, 0);
			arrivalQ.enqueue(customer);
		}
	}

	/**
	 * Run the simulation.
	 * Step1: set the timer to 0.
	 * Step2: while the arrivalQ is not empty, compare the arrival times of the
	 * 		  customers with the current simulation time if it is true, enqueue them to 
	 *        the waitQ after dequeueing them from the arrivalQ, do not increment the 
	 *        timer in case of other customers arriving at that time. Otherwise, if there
	 *        is a customer currently being waited on, set the process time of the
	 *        customer being waited on to itself subtracted by one if there is still time
	 *        to be processed. Otherwise dequeue them from the waitQ since they are done
	 *        being waited on and display the results and increment the timer.
	 * Step3: while there are still customers in the waitQ repeat the process stated
	 *        above, obviously without testing the arrivalQ, since there are no more 
	 *        customers arriving.
	 */
	public void run() {
		time = 0;
		while (!arrivalQ.isEmpty()) {
			if (arrivalQ.peek().getArrivalTime() == time) {
				Customer customer = arrivalQ.dequeue();
				waitQ.enqueue(customer);
				System.out
						.println("Arrival: Customer with identification number "
								+ customer.getCustID()
								+ " Arrived at "
								+ time
								+ " simulation seconds.");
			} else {
				time++;

				if (!waitQ.isEmpty()) {
					calculateStats();

					if (waitQ.peek().getProcessTime() == 0) {
						Customer customer = waitQ.dequeue();
						System.out
								.println("Transaction: Customer with identification number "
										+ customer.getCustID()
										+ " was processed at "
										+ time
										+ " simulation seconds.");
					} else if (waitQ.peek().getProcessTime() > 0) {
						waitQ.peek().setProcessTime(
								waitQ.peek().getProcessTime() - 1);
					}
				}
			}
		}
		
		//After the arrivalQ is empty, continue through the loop if there are
		//customers remaining in the waitQ.
		while (!waitQ.isEmpty()) {
			time++;

			calculateStats();

			if (waitQ.peek().getProcessTime() == 0) {
				Customer customer = waitQ.dequeue();
				System.out
						.println("Transaction: Customer with identification number "
								+ customer.getCustID()
								+ " was processed at "
								+ time + " simulation seconds.");
			} else if (waitQ.peek().getProcessTime() > 0) {
				waitQ.peek().setProcessTime(waitQ.peek().getProcessTime() - 1);
			}
		}
	}

	/**
	 * Step1: while the iterator has another customer to point to,
	 *        increment the time spent in line by all the customers
	 *        in the waitQ by 1 and test the customer to see if they have the
	 *        longest time spent in line. Also increment the total time spent in
	 *        line so that it can be divided by the amount of customers to give
	 *        the average time spent in line.
	 * Step2: Get the amount of people in line per second so that it can be
	 *        divided by the total simulation time to get the average amount
	 *        of people in line.
	 * Step3: Test the current amount of people in line to see if it is the 
	 *        longest line.
	 */
	public void calculateStats() {
		for (int i = 0; i < waitQ.size(); i++) {
			Customer customer = waitQ.iterator().next();
			customer.setTimeInLine(customer.getTimeInLine() + 1);
			totalTimeInLine++;
			
			if (customer.getTimeInLine() > longestTimeInLine) {
				longestTimeInLine = customer.getTimeInLine();
			}
		}
		
		linePerSecond = (waitQ.size() + linePerSecond);
		if (waitQ.size() > longestLine) {
			longestLine = waitQ.size();
		}
	}

	/**
	 * Display the statistics, only calculation that are done before display
	 * are described in calculateStats() comment block.
	 */
	public void stats() {
		System.out.println("\n\nStatistics: ");
		System.out.println("Longest time spent in line: " + longestTimeInLine
				+ " simulation seconds.");
		System.out.println("Average time spent in line: "
				+ (totalTimeInLine / noCustomers) + " simulation seconds.");
		System.out.println("Maximum amount of people in line: " + longestLine
				+ " customers.");
		System.out.println("Average amount of people in line: "
				+ (linePerSecond / time) + " customers.");
	}

}