package Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Class to get the departments associated within a company.
 * each department is populated with a certain amount of employees.
 * There is a one to many relationship in regards to employees.
 * 
 * @author Derek J. Grove
 *
 */
public class Department {

	private String deptCode;
	private String deptName;
	private String empManagerId;
	private Employee[] employees = new Employee[15];
	private int noEmployees;

	/**
	 * Constructor for Department
	 * @param deptCode - Unique String of characters to identify which employee
	 * 					 goes to which department
	 * @param deptName - String of characters that identify the department's name
	 * @param empManagerId - Unique String of characters to identify the employee
	 * 						 manager of the department
	 */
	public Department(String deptCode, String deptName, String empManagerId) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
		this.empManagerId = empManagerId;
	}

	/**
	 * Add each employee to the array
	 * @param employees array with an additional employee
	 */
	public void addEmployee(Employee employee) {
		employees[noEmployees++] = employee;
	}

	/**
	 * Get the number of employees
	 * @return noEmployees
	 */
	public int getNoEmployees() {
		return noEmployees;
	}

	/**
	 * Get the employee at a specified index
	 * @param n - the index variable
	 * @return the employee in the array
	 */
	public Employee getEmployee(int n) {
		return employees[n];
	}

	/**
	 * Get the unique department code
	 * @return deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * Get the department name
	 * @return deptName
	 */
	public String getDeptName() {
		return deptName;
	}

	/**
	 * Get the employee manager ID
	 * @return empManagerId
	 */
	public String getEmpManagerId() {
		return empManagerId;
	}

	/**
	 * Register which employee is the manager
	 * @param managerCode - unique identifier for the manager
	 * @return the name of the manager
	 */
	public String processManager(String managerCode) {
		for (int i = 0; i < noEmployees; i++) {
			Employee employee = getEmployee(i);
			if (employee.getEmployeeId().equals(managerCode)) {
				return employee.getfName() + " " + employee.getlName();
			}
		}
		return null;
	}

	/**
	 * Sort the array of employees from the first position in the array
	 * to the last, creating a new comparator of type Employee
	 */
	public void sort() {
		// TODO Auto-generated method stub
		Arrays.sort(employees, 0, noEmployees, new Comparator <Employee> () {
			
			/**
			 * Compare the last names of two employees at a time, the compare
			 * method automatically sorts the array as a bubblesort
			 * @param emp1 - the first employee name
			 * @param emp2 - the second employee name
			 * @return which employee goes first
			 */
			@Override
			public int compare(Employee emp1, Employee emp2) {
				// TODO Auto-generated method stub
				if (emp1.getlName().compareTo(emp2.getlName()) > 0) {
					return 1;
				} else if (emp1.getlName().compareTo(emp2.getlName()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
			
		});		
	}

}
