package Queue;
/**
 * COSC 310-001    Assignment 5
 * Customer.java
 * 
 * A class that provides the definition of the objects
 * that are used in this program.
 * 
 * @author  Derek J. Grove
 *
 */
public class Customer {

	private int custID;
	private int arrivalTime;
	private int processTime;
	private int timeInLine;

	/**
	 * Constructor with the attributes required to instantiate the customer.
	 * @param arrivalTime - the time arrived at the store.
	 * @param custID - unique identifier, order of arrival in this case.
	 * @param processTime - the amount of time it takes to satisfy the customer.
	 * @param timeInLine - the amount of time spent in line.
	 */
	public Customer(int arrivalTime, int custID, int processTime, int timeInLine) {
		this.arrivalTime = arrivalTime;
		this.custID = custID;
		this.processTime = processTime;
		this.timeInLine = timeInLine;
	}

	//Getter Methods
	
	public int getTimeInLine() {
		return timeInLine;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getCustID() {
		return custID;
	}
	
	//Setter Methods
	
	public int getProcessTime() {
		return processTime;
	}
	
	public void setTimeInLine(int timeInLine) {
		this.timeInLine = timeInLine;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

}
