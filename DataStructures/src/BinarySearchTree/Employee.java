package BinarySearchTree;
/**
 * COSC 310-001    Assignment 6
 * Employee.java
 * 
 * A class that provides the required attributes of an employee and the
 * object that is being stored in MyBST.
 * 
 * @author  Derek J. Grove
 *
 */
public class Employee implements Comparable {

	private String empID;
	private String fName;
	private String lName;
	private double salary;

	/**
	 * Constructor to instantiate a full employee object of which supply all 
	 * attributes of an employee.
	 * @param empID - the identification of the employee
	 * @param fName - the first name of the employee
	 * @param lName - the last name of the employee
	 * @param salary - the salary of the employee
	 */
	public Employee(String empID, String fName, String lName, double salary) {
		super();
		this.empID = empID;
		this.fName = fName;
		this.lName = lName;
		this.salary = salary;
	}

	/**
	 * Constructor in which will be used to find an employee.
	 * Instead of supplying all attributes, just create a new
	 * employee with just the empID.
	 * @param empID - unique identifier of an employee
	 */
	public Employee(String empID) {
		this.empID = empID;
	}

	//Getters
	
	public String getEmpID() {
		return empID;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public double getSalary() {
		return salary;
	}

	/**
	 * Provide only the employeeID for the user to see, that way
	 * it is a quick overlook, and if more information is required
	 * than the user can look the employee up.
	 */
	@Override
	public String toString() {
		return empID;
	}

	/**
	 * Compare the empID, the employee's unique identifier.
	 * Determine if employee will go on left or right subtree.
	 */
	@Override
	public int compareTo(Object o) {
		if (this.empID.equals(((Employee) o).getEmpID())) {
			return 0;
		}
		if (empID.compareTo(((Employee) o).getEmpID()) == -1) {
			return -1;
		}
		if (empID.compareTo(((Employee) o).getEmpID()) == 1) {
			return 1;
		}
		return -1;

	}

}
