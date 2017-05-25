package SingleLinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 2
 * PartsMenu.java
 * 
 * A class that provides a menu-driven implementation.
 * Provides a menu for the user to choose from a variety
 * of functions that alter the list of parts.
 * 
 * @author  Derek J. Grove
 *
 */
public class PartsMenu {

	Scanner userInput = new Scanner(System.in);
	private BufferedReader in;
	private SLList<Part> parts = new SLList();

	/**
	 * Constructor to read the parts into the list
	 * @param fileName - the fileName to be read from
	 * @throws IOException - thrown if there is an error
	 * while reading from the file
	 */
	public PartsMenu(String fileName) throws IOException {
		in = new BufferedReader(new FileReader(fileName));
		PartsMenu.Part part;
		while ((part = readPart()) != null) {
			parts.addNode(part);
		}
		close();
	}

	/**
	 * BufferedReader that separates each line into fields
	 * so that specific variables of a part can be assigned
	 * @return a part
	 * @throws IOException - thrown when there is an error
	 * while reading from the file
	 */
	public Part readPart() throws IOException {
		String line = in.readLine();

		if (line == null) {
			return null;
		}

		String[] fields = line.split(",");
		String partNo = fields[0];
		String description = fields[1];
		
		if (description.length() > 30) {
			description = description.substring(0, 30);
		}
		
		double price = Double.parseDouble(fields[2]);
		String warehouseId = fields[3];
		int quantity = Integer.parseInt(fields[4]);
		
		Part part = new Part(partNo, description, price, warehouseId, quantity);
		return part;
	}

	/**
	 * Close the BufferedReader to avoid errors
	 * @throws IOException - thrown when there is an error
	 * while reading in the file
	 */
	public void close() throws IOException {
		in.close();
	}

	/**
	 * Common header used before displaying a queue
	 */
	private void displayHeader() {
		System.out.printf("\n%s %25s %22s %20s %16s\n", "Part Number",
				"Description", "Price", "WarehouseID", "Quantity");
		System.out.printf("%s %34s %17s %16s %18s\n", "-----------",
				"------------------------------", "--------------",
				"-----------", "------------");
	}

	/**
	 * The first choice from the menu.
	 * Traverse through the list with the iterator, displaying
	 * all the parts in the list until the last dataItem
	 */
	public void choiceDisplay() {
		displayHeader();
		SLList.ListIter iter = (SLList.ListIter) parts.iterator();

		while (iter.hasNext()) {
			Part part = (Part) iter.next();
			System.out.printf("   %-12s %-32s %s%9.2f %-11s%-10s %10d\n",
					part.getPartNo(), part.getDescription(), "   $",
					part.getPrice(), " ", part.getWarehouseId(),
					part.getQuantity());
		}
	}

	/**
	 * The second choice from the menu.
	 * Gather input from the user to fill the necessary variables
	 * of a part, and then create the new part.
	 */
	public void choiceAddPart() {
		System.out.print("Part Number: ");
		String partNo = userInput.next();
		System.out.print("Description[Maximum 30 Characters]: ");
		String line = userInput.nextLine();
		String[] fields = line.split(" ");
		String description = userInput.nextLine();

		int i = 0;
		while (!line.isEmpty()) {
			description += (fields[i]);
			i++;
		}
		if (description.length() > 30) {
			description = description.substring(0, 30);
		}

		System.out.print("\nPrice: $");
		double price = userInput.nextDouble();
		System.out.print("Warehouse ID: ");
		String warehouseId = userInput.next();
		System.out.print("Quantity: ");
		int quantity = userInput.nextInt();

		Part part = new Part(partNo, description, price, warehouseId, quantity);
		parts.addNode(part);
	}

	/**
	 * The third choice from the menu.
	 * Traverse through the list with the iterator until the part
	 * has been found. Display all parts with the corresponding
	 * part number
	 */
	public void choiceFindPart() {
		System.out.print("Part Number: ");
		String partNo = userInput.next();
		SLList.ListIter iter = (SLList.ListIter) parts.iterator();

		displayHeader();
		while (iter.hasNext()) {
			Part part = (Part) iter.next();
			if (part.getPartNo().equals(partNo)) {
				System.out.printf("   %-12s %-32s %s%9.2f %-11s%-10s %10d\n",
						part.getPartNo(), part.getDescription(), "   $",
						part.getPrice(), " ", part.getWarehouseId(),
						part.getQuantity());
			} else if (!iter.hasNext() && !part.getPartNo().equals(partNo)) {
				System.out.println(partNo + " Not Found");
			}
		}
	}

	/**
	 * The fourth choice from the menu.
	 * Traverse through the list until the dataItem is found,
	 * delete the node being referenced
	 */
	public void choiceDeletePart() {
		System.out.print("Part Number: ");
		String partNo = userInput.next();
		SLList.ListIter iter = (SLList.ListIter) parts.iterator();

		while (iter.hasNext()) {
			Part part = (Part) iter.next();
			if (part.getPartNo().equals(partNo)) {
				parts.remove(part);
				System.out.println("Done.");
				break;
			} else if (!iter.hasNext() && part.getPartNo().equals(partNo)) {
				System.out.println(partNo + " Not Found");
			}
		}
	}

	/**
	 * The fifth choice from the menu.
	 * Traverse through the list until the dataItem is found,
	 * provide the user with pertinent information so that they
	 * can set the quantity accordingly
	 */
	public void choiceEditQuantity() {
		System.out.print("Part Number: ");
		String partNo = userInput.next();
		SLList.ListIter iter = (SLList.ListIter) parts.iterator();

		while (iter.hasNext()) {
			Part part = (Part) iter.next();
			if (part.getPartNo().equals(partNo)) {
				System.out.printf("\n%20s %26s %16s\n", "Description",
						"WarehouseID", "Quantity");
				System.out.printf("%s %16s %18s\n",
						"------------------------------", "-----------",
						"------------");
				System.out.printf("%-32s %-8s%-10s %10d\n",
						part.getDescription(), " ", part.getWarehouseId(),
						part.getQuantity());
				System.out.print("What is the new quantity: ");
				part.setQuantity(userInput.nextInt());
				System.out.println("Done.");
			}
		}
	}

	/**
	 * Method to call on the appropriate functions. This will
	 * continue until the user has quit the application in which
	 * they must enter 0
	 */
	public void display() {
		int choice = displayMenu();
		while (choice != 0) {
			if (choice == 1) {
				choiceDisplay();
			} else if (choice == 2) {
				choiceAddPart();
			} else if (choice == 3) {
				choiceFindPart();
			} else if (choice == 4) {
				choiceDeletePart();
			} else if (choice == 5) {
				choiceEditQuantity();
			} else if (choice < 0 || choice > 5) {
				System.out.println("Invalid Choice: " + choice);
			}
			choice = displayMenu();
		}
	}

	/**
	 * Display the menu of whom the user can choose
	 * @return the user's choice
	 */
	public int displayMenu() {
		System.out
		.printf("\n%s\n\n %s\n %s\n %s\n %s\n %s\n %s\n",
				"Please pick a number (0 - 5)", "1. Display",
				"2. Add Part", "3. Find Part", "4. Delete Part",
				"5. Edit Quantity", "0. Exit");
		Scanner input = new Scanner(System.in);
		System.out.print("\nChoice: ");
		return input.nextInt();
	}

	/**
	 * Inner Class to store the parts
	 * 
	 * @author Derek J. Grove
	 *
	 */
	class Part implements Comparable {
		private String partNo;
		private String description;
		private double price;
		private String warehouseId;
		private int quantity;

		/**
		 * Constructor to create a part
		 * @param partNo - unique identifier of a part
		 * @param description - provides an explanation of the part
		 * @param price - the price at which the part is sold
		 * @param warehouseId - 3 character location identifier
		 * @param quantity - how many of that part are in stock
		 */
		public Part(String partNo, String description, double price,
				String warehouseId, int quantity) {
			super();
			this.partNo = partNo;
			this.description = description;
			this.price = price;
			this.warehouseId = warehouseId;
			this.quantity = quantity;
		}

		/**
		 * Getter Methods
		 */
		public String getPartNo() {
			return partNo;
		}

		public String getDescription() {
			return description;
		}

		public double getPrice() {
			return price;
		}

		public String getWarehouseId() {
			return warehouseId;
		}

		public int getQuantity() {
			return quantity;
		}

		/**
		 * Set the quantity so that it can be changed
		 * @param quantity - the new quantity
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		/**
		 * Compare the products to make sure there are
		 * no duplicates
		 */
		@Override
		public int compareTo(Object part) {
			if (partNo == ((Part) part).getPartNo()) {
				return 0;
			}
			return -1;
		}
	}

}
