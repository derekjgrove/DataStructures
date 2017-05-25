package Array;

/**
 * Employee gets the employees associated with each department.
 * There is a many to one relationship regarding a department.
 * 
 * @author Derek J. Grove
 *
 */
public class Employee {

	private String employeeId;
	private String empConIndicator;
	private String deptCode;
	private String fName;
	private String lName;
	private float salary;
	private String hireDate;
	private int vacDays;
	private int training;

	/**
	 * Constructor for Employee
	 * @param employeeId - Unique String of characters that identify an employee
	 * @param empConIndicator - A single character to identify the person as a 
	 * 							employee('E') or a contractor('C')
	 * @param deptCode - Unique string of characters that identify the employee
	 * 					 to their department
	 * @param fName - String of characters to identify the employee's first name
	 * @param lName - String of characters to identify the employee's last name
	 * @param salary - Floating point to express the salary of the employee
	 * @param hireDate - Formatted string to express the hire date of the employee
	 * @param vacDays - An integer to express the amount of vacation days
	 * @param training - An integer to express the amount of training days
	 */
	public Employee(String employeeId, String empConIndicator, String deptCode,
			String fName, String lName, float salary, String hireDate,
			int vacDays, int training) {
		super();
		this.employeeId = employeeId;
		this.empConIndicator = empConIndicator;
		this.deptCode = deptCode;
		this.fName = fName;
		this.lName = lName;
		this.salary = salary;
		this.hireDate = hireDate;
		this.vacDays = vacDays;
		this.training = training;
	}

	/**
	 * Get the employee ID
	 * @return employeeId
	 */
	public String getEmployeeId() {
		return employeeId;
	}

	/**
	 * Get the employee/contractor indicator
	 * @return empConIndicator
	 */
	public String getEmpConIndicator() {
		return empConIndicator;
	}

	/**
	 * Get the department code
	 * @return deptCode
	 */
	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * Get the first name
	 * @return fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * Get the last name
	 * @return lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * Get the salary of the employee
	 * @return salary
	 */
	public float getSalary() {
		return salary;
	}

	/**
	 * Get the date of hire
	 * @return hireDate
	 */
	public String getHireDate() {
		return hireDate;
	}

	/**
	 * Get the amount of vacation days
	 * @return vacDays
	 */
	public int getVacDays() {
		return vacDays;
	}

	/**
	 * Get the amount of training days
	 * @return training
	 */
	public int getTraining() {
		return training;
	}

}
