package BinarySearchTree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 6
 * EmployeeList.java
 * 
 * A class that stores Employee objects in the MyBST class, and provides
 * a menu to access the tree and add new employees and search for them.
 * 
 * @author  Derek J. Grove
 *
 */
public class EmployeeList {
	private Scanner input = new Scanner(System.in);
	private BufferedReader in;
	private MyBST<Employee> employees = new MyBST<Employee>();

	/**
	 * Constructor that reads employee information from a file,
	 * creates the employee objects, and adds the objects to the
	 * tree.
	 * @param fileName - the file in which the FileReader will read from.
	 * @throws IOException - thrown if there is an error while trying
	 * 						 to input from the file.
	 */
	public EmployeeList(String fileName) throws IOException {

		in = new BufferedReader(new FileReader(fileName));
		String line;
		while ((line = in.readLine()) != null) {
			String[] fields = line.split(" ");

			String empID = fields[0];
			String fName = fields[1];
			String lName = fields[2];
			double salary = Double.parseDouble(fields[3]);

			if (empID.length() > 3) {
				empID = empID.substring(0, 2);
			}
			if (fName.length() > 20) {
				fName = fName.substring(0, 19);
			}
			if (lName.length() > 15) {
				lName = lName.substring(0, 14);
			}

			Employee employee = new Employee(empID, fName, lName, salary);
			employees.add(employee);
		}

		in.close();
	}

	/**
	 * Provides a menu based system.
	 */
	public void run() {
		System.out.printf("%s\n%s\n%s\n%s\n%s\n\n%s",
				"Please select from the following options.", "1. Display tree",
				"2. Find Employee", "3. Add Employee", "0. Exit", "Choice: ");
		int choice = input.nextInt();
		while (choice != 0) {

			if (choice == 1) {
				System.out.println();
				displayTree();
			} else if (choice == 2) {
				System.out.println();
				findEmployee();
			} else if (choice == 3) {
				System.out.println();
				addEmployee();
			} else {
				System.out.println("\nInvalid option: " + choice);
			}

			System.out.printf("\n\n%s\n%s\n%s\n%s\n%s\n\n%s",
					"Please select from the following options.",
					"1. Display tree", "2. Find Employee", "3. Add Employee",
					"0. Exit", "Choice: ");
			choice = input.nextInt();

		}
	}

	/**
	 * Display the binary search tree, with indentations to specify levels.
	 */
	public void displayTree() {
		System.out.println(employees);
	}

	/**
	 * Request the employee id and then return and display the full information
	 * for that employee if the employee exists.
	 */
	public void findEmployee() {
		Scanner input = new Scanner(System.in);
		System.out.print("Employee ID: ");
		String empID = input.next();
		Employee emp = new Employee(empID);
		Employee target = employees.find(emp);
		if (target == null) {
			System.out.println("Employee ID: " + empID + " was not found");
		} else {
			System.out
					.printf("\n%-20s%-20s%-20s%s\n%-15s%-25s%-20s%s\n    %-11s%-25s%-20s$%11.2f",
							"Employee ID", "First Name", "Last Name", "Salary",
							"-----------", "--------------------",
							"---------------", "------------",
							target.getEmpID(), target.getfName(),
							target.getlName(), target.getSalary());
		}

	}

	/**
	 * Request the employee information and add the employee object to the tree.
	 * Alert user if the employee is not added.
	 */
	public void addEmployee() {
		System.out.print("Employee ID: ");
		String empID = input.next();
		System.out.print("Employee First Name: ");
		String fName = input.next();
		System.out.print("Employee Last Name: ");
		String lName = input.next();
		System.out.print("Employee Salary: ");
		double salary = input.nextDouble();
		Employee employee = new Employee(empID, fName, lName, salary);
		employees.add(employee);
		System.out.println("Added: " + fName);
	}

}
