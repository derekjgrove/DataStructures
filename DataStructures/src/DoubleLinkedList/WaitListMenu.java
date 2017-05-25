package DoubleLinkedList;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 3
 * WaitListMenu.java
 * 
 * A class that provides a menu-driven implementation.
 * Provides a menu for the user to choose from a variety
 * of functions that alter the list of parts.
 * 
 * @author  Derek J. Grove
 *
 */
public class WaitListMenu {

	private DLList<Student> list = new DLList();

	/**
	 * Constructor for WaitListMenu().
	 * Create new students to load a list.
	 */
	public WaitListMenu() {
		Student student = new Student("12345", "Terrance", "Fries",
				"Computer Science");
		list.add(student);
		student = new Student("13579", "Andrea", "Morman", "Computer Science");
		list.add(student);
		student = new Student("02468", "David", "Smith", "Computer Science");
		list.add(student);
		student = new Student("67890", "William", "Oblitey", "Computer Science");
		list.add(student);
		student = new Student("42642", "Sanwar", "Ali", "Un-Decided");
		list.add(student);
		student = new Student("42634", "Dan", "Frederick", "Computer Science");
		list.add(student);
	}
	
	/**
	 * Header used without the queries requested by the user.
	 */
	public void displayHeader() {
		System.out.printf("%s%22s%25s\n%s%25s%25s\n", "Student ID",
				"Student Name", "Student Major", "----------",
				"-------------------", "------------------");
	}

	/**
	 * Prompt the user a set of options to choose from.
	 * @return the choice given by the user.
	 */
	public int displayMenu() {
		System.out.print("\nPlease select a number (0 - 4) \n\n"
				+ "1. List students in order \n"
				+ "2. List students in reverse order \n"
				+ "3. Add student \n4. Remove student\n" + "0. Quit");
		Scanner input = new Scanner(System.in);
		System.out.print("\nChoice: ");
		return input.nextInt();
	}

	/**
	 * Test the user input and call the method to perform the function.
	 */
	public void display() {
		System.out.println();
		int choice = displayMenu();

		while (choice != 0) {

			if (choice == 1) {
				choiceInOrder();
			} else if (choice == 2) {
				choiceReverseOrder();
			} else if (choice == 3) {
				choiceAddStudent();
			} else if (choice == 4) {
				choiceRemoveStudent();
			} else if (choice < 0 || choice > 4) {
				System.out.println("Invalid Choice: " + choice);
			}
			System.out.println();
			choice = displayMenu();
		}
	}

	/**
	 * Traverse through the list using the iterator.
	 * Call on the normal iterator so that the list is displayed
	 * in order.
	 */
	public void choiceInOrder() {
		displayHeader();
		DLList.ListIter iter = (DLList.ListIter) list.iterator();

		while (iter.hasNext()) {
			Student student = (Student) iter.next();
			System.out.printf("  %-13s %-25s %s\n", student.getStudentID(),
					student.getfName() + " " + student.getlName(),
					student.getMajor());
		}
	}

	/**
	 * Traverse through the list using the iterator.
	 * Call on the reverse iterator so that the list is displayed
	 * in reverse order.
	 */
	public void choiceReverseOrder() {
		displayHeader();
		DLList.ListIter iter = (DLList.ListIter) list.revIterator();

		while (iter.hasPrevious()) {
			Student student = (Student) iter.previous();
			System.out.printf("  %-13s %-25s %s\n", student.getStudentID(),
					student.getfName() + " " + student.getlName(),
					student.getMajor());
		}
	}

	/**
	 * Prompt the user for the attributes needed to create a 
	 * new student, and then add the student to the list.
	 */
	public void choiceAddStudent() {
		Scanner input = new Scanner(System.in);
		System.out.print("Student ID: ");
		String studentID = input.next();
		System.out.print("Student First Name: ");
		String fName = input.next();
		System.out.print("Student Last Name: ");
		String lName = input.next();
		System.out.print("Student Major: ");
		String line = input.nextLine();
		String[] fields = line.split(" ");
		String major = input.nextLine();
		int i = 0;
		while (!line.isEmpty()) {
			major += (fields[i]);
			i++;
		}
		
		if (major.length() > 30) {
			major = major.substring(0, 30);
		}

		Student student = new Student(studentID, fName, lName, major);
		list.add(student);
		System.out.println("Added " + fName);
	}

	/**
	 * Traverse through the list using the iterator.
	 * Once the student is found, display the student being deleted and
	 * then delete the student from the list.
	 */
	public void choiceRemoveStudent() {
		Scanner input = new Scanner(System.in);
		System.out.print("Student ID: ");
		String studentID = input.next();
		displayHeader();

		DLList.ListIter iter = (DLList.ListIter) list.iterator();
		while (iter.hasNext()) {
			Student student = (Student) iter.next();
			if (student.getStudentID().equals(studentID)) {
				System.out.printf("  %-13s %-25s %s\n", student.getStudentID(),
						student.getfName() + " " + student.getlName(),
						student.getMajor());
				list.remove(student);
				System.out.println("Deleted.");
				break;
			} else if (!iter.hasNext()
					&& student.getStudentID().equals(studentID)) {
				System.out.println(studentID + " Not Found");
			}
		}
	}

	/**
	 * Inner class to store the students.
	 * 
	 * @author Derek J. Grove
	 *
	 */
	class Student implements Comparable {
		private String studentID;
		private String fName;
		private String lName;
		private String major;

		/**
		 * Constructor to create a new student object.
		 */
		public Student(String studentID, String fName, String lName,
				String major) {
			super();
			this.studentID = studentID;
			this.fName = fName;
			this.lName = lName;
			this.major = major;
		}

		//Getter Methods
		public String getStudentID() {
			return studentID;
		}

		public String getfName() {
			return fName;
		}

		public String getlName() {
			return lName;
		}

		public String getMajor() {
			return major;
		}

		/**
		 * Compare the student object by its unique identifier.
		 * In this case it is the student's ID.
		 */
		@Override
		public int compareTo(Object student) {
			if (studentID == ((Student) student).getStudentID()) {
				return 0;
			}
			return -1;
		}
	}

}
