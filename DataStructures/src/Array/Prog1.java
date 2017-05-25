package Array;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Prog1 pulls together departments and employees. This class
 * has the main method to run the desired output. This class
 * also has two inner classes to load in the files for Department
 * and Employee.
 * 
 * @author Derek J. Grove
 *
 */
public class Prog1 {

	private Department[] departments = new Department[10];
	private int noDepartments;
	
	/**
	 * Sort each department within the array.
	 */
	public void sortDepartments() {
		for (int i = 0; i < departments.length; i++) {
			departments[i].sort();
		}
	}
	
	/**
	 * Add the departments to the company
	 * @param department - department to be added to the array
	 */
	public void addDepartment(Department department) {
		departments[noDepartments++] = department;
	}

	/**
	 * Get the number of departments in the array
	 * @return noDepartments
	 */
	public int getNoDepartments() {
		return noDepartments;
	}

	/**
	 * Get the department at a given index
	 * @param n - the index variable
	 * @return the department
	 */
	public Department getDepartment(int n) {
		return departments[n];
	}

	/**
	 * Look-up which employees go with which department
	 * @param deptCode - the employee's department code being passed in
	 * @return the department in which the employee belongs
	 */
	public Department lookupEmployee(String deptCode) {
		for (int i = 0; i < noDepartments; i++) {
			Department department = getDepartment(i);
			if (department.getDeptCode().equals(deptCode)) {
				return department;
			}
		}
		return null;
	}

	/**
	 * Load the departments into memory from the file specified
	 * @param departmentFileName - name of the file to load
	 * @throws IOException - is thrown if there is an error while inputing
	 * 						 or outputting
	 */
	private void loadDepartments(String departmentFileName) throws IOException {
		DeptTextReader reader = new DeptTextReader(departmentFileName);
		Department department = null;

		while ((department = reader.readDepartment()) != null) {
			addDepartment(department);
		}
		reader.close();
	}

	/**
	 * Load the employees into memory from the file specified
	 * @param employeeFileName - name of the file to load
	 * @throws IOException - is thrown if there is an error while inputing
	 * 						 or outputting
	 */
	private void loadEmployees(String employeeFileName) throws IOException {
		EmpTextReader reader = new EmpTextReader(employeeFileName);
		Department department = null;
		Employee employee;

		while ((employee = reader.readEmployee()) != null) {
			department = lookupEmployee(employee.getDeptCode());
			department.addEmployee(employee);
		}
		reader.close();
	}

	
	/**
	 * Inner class to read in the employees from the file. This class works
	 * as a helper class for Employee.
	 * @author Derek J. Grove
	 *
	 */
	class EmpTextReader {
		private BufferedReader in;

		/**
		 * Pass the file name to instantiate the buffered reader
		 * @param fileName - the file to be infiled
		 * @throws FileNotFoundException - is thrown if the file is not found
		 */
		public EmpTextReader(String fileName) throws FileNotFoundException {
			in = new BufferedReader(new FileReader(fileName));
		}

		/**
		 * Read in all attributes that are required for an employee
		 * @return the employee to be added to the array
		 * @throws IOException - is thrown if there is an error while inputing
		 * 						 or outputting
		 */
		public Employee readEmployee() throws IOException {
			String line = in.readLine();

			if (line == null) {
				return null;
			}

			String[] fields = line.split(" ");
			String employeeId = fields[0];
			String empConIndicator = fields[1];
			String deptCode = fields[2];
			String fName = fields[3];
			String lName = fields[4];
			float salary = Float.parseFloat(fields[5]);
			String hireDate = fields[6];
			int vacDays = Integer.parseInt(fields[7]);
			int training = Integer.parseInt(fields[8]);

			Employee employee = new Employee(employeeId, empConIndicator,
					deptCode, fName, lName, salary, hireDate, vacDays, training);
			return employee;

		}

		/**
		 * Close the buffered reader
		 * @throws IOException - is thrown if there is an error while inputing
		 * 						 or outputting
		 */
		public void close() throws IOException {
			in.close();
		}

	}
	

	/**
	 * Inner class to read in the departments from the file. This class works
	 * as a helper class for Department.
	 * @author Derek J. Grove
	 *
	 */
	class DeptTextReader {
		private BufferedReader in;

		/**
		 * 
		 * @param fileName
		 * @throws FileNotFoundException
		 */
		public DeptTextReader(String fileName) throws FileNotFoundException {
			in = new BufferedReader(new FileReader(fileName));
		}

		/**
		 * 
		 * @return the new department to be added to the array
		 * @throws IOException - is thrown if there is an error while inputing
		 * 						 or outputting
		 */
		public Department readDepartment() throws IOException {
			String line = in.readLine();

			if (line == null) {
				return null;
			}

			String[] fields = line.split(",");
			String deptCode = fields[0];
			String deptName = fields[1];
			String empManagerId = fields[2];

			Department department = new Department(deptCode, deptName,
					empManagerId);

			return department;
		}

		/**
		 * Close the buffered reader
		 * @throws IOException - is thrown if there is an error while inputing
		 * 						 or outputting
		 */
		public void close() throws IOException {
			in.close();
		}

	}
	

	/**
	 * Main method of Prog1
	 * @param args - none
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Prog1 prog1 = new Prog1();
		try {
			prog1.loadDepartments("department.txt");
			prog1.loadEmployees("employee.txt");
			prog1.sortDepartments();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		} 
		prog1.printReport();
	}
	
	/**
	 * Print the report, providing the department name, manager, staff size,
	 * and the employees' within the department. Including their ID, full
	 * name, hire date, salary, and vacation days
	 */
	public void printReport() {

		for (int i = 0; i < getNoDepartments(); i++) {
			Department department = getDepartment(i);
			System.out.println("\n" + department.getDeptName()
					+ " Department\n");
			int employees = department.getNoEmployees();
			System.out.printf("%-14s %s \n", "Manager:",
					department.processManager(department.getEmpManagerId()));
			System.out.printf("%s   %d \n\n", "Staff size: ", employees);
			System.out.printf(" %s %18s %14s %11s %18s \n", "ID",
					"Employee Name", "Hire Date", "Salary", "Vacation Days");
			for (int j = 0; j < employees; j++) {
				Employee employee = department.getEmployee(j);
				System.out.printf(" %s %3s %-17s %s %5s %3.2f %12d\n",
						employee.getEmployeeId(), " ", employee.getfName()
								+ " " + employee.getlName(),
						employee.getHireDate(), "$", employee.getSalary(),
						employee.getVacDays());
			}
		}
	}
	
}
