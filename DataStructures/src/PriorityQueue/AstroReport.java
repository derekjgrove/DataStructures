package PriorityQueue;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 7
 * AstroReport.java
 * 
 *Class that creates an array of Astro objects. Identifies
 *a specified amount of nearest astro entities to the target.
 * 
 * @author  Derek J. Grove
 *
 */
public class AstroReport {
	private Scanner input = new Scanner(System.in);
	private Scanner reader;
	private PQueue<Astro> astros = new PQueue<Astro>();

	/**
	 * Constructor to load the .txt file
	 * @param fileName - file to be loaded
	 * @throws IOException - thrown if there is an error
	 * 						 while inputting the file
	 */
	public AstroReport(String fileName) throws IOException {
		reader = new Scanner(new FileInputStream(fileName));
	}

	/**
	 * Read the astro objects from the text file.
	 * @param userX - x coordinate supplied by the user
	 * @param userY - y coordinate supplied by the user
	 * @return the new Astro object
	 * @throws IOException - thrown if there is an error while
	 * 						 inputting from the file
	 */
	public Astro readAstro(int userX, int userY) throws IOException {

		if (!reader.hasNext()) {
			return null;
		}

		String starName = reader.next();
		int xStar = reader.nextInt();
		int yStar = reader.nextInt();

		double distance = eucDistance(xStar, yStar, userX, userY);
		Astro astro = new Astro(starName, xStar, yStar, distance);
		return astro;
	}

	/**
	 * Calculate the Euclidean distance from the user's coordinates
	 * with the Astro objects in the priority queue.
	 * @param starX - x coordinate of Astro object
	 * @param starY - y coordinate of Astro object
	 * @param userX - x coordinate supplied by user
	 * @param userY - y coordinate supplied by user
	 * @return the distance from the objects
	 */
	public double eucDistance(int starX, int starY, int userX, int userY) {
		return Math.sqrt(Math.pow(starX - userX, 2)
				+ Math.pow(starY - userY, 2));
	}

	/**
	 * Prompt user for coordinates and the number of neighboring astros
	 * they would like to view.
	 * Add the Astro objects to the PQueue after they have been compared
	 * via distance.
	 * @throws IOException - thrown if there is an error while inputting
	 *						 from the .txt file.
	 */
	public void run() throws IOException {
		System.out.print("X coordinate: ");
		int xCoordinate = input.nextInt();
		System.out.print("Y coordinate: ");
		int yCoordinate = input.nextInt();
		System.out.print("Number of Neighboring stars: ");
		int neighbors = input.nextInt();

		AstroReport.Astro astro;
		while ((astro = readAstro(xCoordinate, yCoordinate)) != null) {
			astros.add(astro);
		}

		System.out.printf(
				"\n\n\n%s%d%s(%d, %d)\n\n %15s%30s%20s \n %20s%25s%20s \n",
				"Found the following ", neighbors, " astros for coordinates: ",
				xCoordinate, yCoordinate, "Astro Name", "Astro Coordinates",
				"Distance", "--------------------", "-----------------",
				"--------");

		for (int i = 0; i < neighbors; i++) {
			astro = astros.remove();
			System.out.printf(" %-30s (%3d, %3d) %13s %9.2f\n",
					astro.getStarName(), astro.getxStar(), astro.getyStar(),
					" ", astro.getDistance());
		}

	}

	/**
	 * Inner class thats holds values for the object used in this program.
	 * Astro is the object being added to the priority queue.
	 * 
	 * @author Derek J. Grove
	 *
	 */
	private class Astro implements Comparable {
		private String starName;
		private int xStar;
		private int yStar;
		private double distance;

		/**
		 * Constructor used to create an Astro object
		 * @param starName - the name of the astro
		 * @param xStar - x coordinate of the astro
		 * @param yStar - y coordinate of the astro
		 * @param distance - the distance from the astro to user's coordinates
		 */
		public Astro(String starName, int xStar, int yStar, double distance) {
			super();
			this.starName = starName;
			this.xStar = xStar;
			this.yStar = yStar;
			this.distance = distance;
		}

		//Getter Methods
		
		public String getStarName() {
			return starName;
		}

		public int getxStar() {
			return xStar;
		}

		public int getyStar() {
			return yStar;
		}

		public double getDistance() {
			return distance;
		}

		/**
		 * Compare the objects, objects are compared based off of distance.
		 * Ascending order.
		 */
		@Override
		public int compareTo(Object o) {
			if (this.distance == ((Astro) o).getDistance()) {
				return 0;
			}
			if (this.distance < ((Astro) o).getDistance()) {
				return -1;
			}
			if (distance > ((Astro) o).getDistance()) {
				return 1;
			}
			return -1;
			
		}

	}

}
