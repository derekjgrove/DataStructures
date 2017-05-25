package DijkstraAlgorithm;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * COSC 310-001    Assignment 8
 * Dijkstra.java
 * 
 * A class that provides an AdjacencyMatrix implementation of Dijkstra's algorithm.
 * It processes the weights between two vertices and determines if that is the
 * quickest path to the final destination.
 * 
 * @author  Derek J. Grove
 *
 */
public class Dijkstra {

	private Map<String, Vertex> vertices = new HashMap<String, Vertex>();
	public PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(200, new Vertex());

	/**
	 * Constructor to load the adjacency matrix
	 */
	public Dijkstra() {
		addVertex("a", "b", 2);
		addVertex("a", "d", 6);
		addVertex("b", "d", 9);
		addVertex("b", "f", 3);
		addVertex("c", "d", 3);
		addVertex("c", "e", 4);
		addVertex("d", "e", 10);
		addVertex("d", "f", 3);
		addVertex("d", "g", 5);
		addVertex("f", "g", 1);
	}

	/**
	 * 
	 * @param source - the parent vertex
	 * @param dest - the child vertex
	 * @param weight - the weight it took to get there
	 */
	public void addVertex(String source, String dest, int weight) {
		Vertex v = getVertex(source);
		Vertex w = getVertex(dest);
		v.names.add(new Edge(w, weight));
		w.names.add(new Edge(v, weight));
	}

	/**
	 * Get the name of the verticy and associate it as an index
	 * @param name - the name of the verticy
	 * @return the name of the verticy
	 */
	public Vertex getVertex(String name) {
		Vertex v = (Vertex) vertices.get(name);
		if (v == null) {
			v = new Vertex(name);
			vertices.put(name, v);
		}
		return v;
	}
	
	/**
	 * Print a visual of the path that is currently
	 * being processed.
	 * @param v - the verticy being addressed
	 */
	public void printPath(Vertex v) {
		Vertex current = v;
		if (current.previous != null) {
			printPath(current.previous);
			System.out.print(" --> ");
			System.out.print(current.name);
		}
		if (current.previous == null)
			System.out.print(current.name);
	}

	/**
	 * Get the source and destination, print the current path as
	 * the total distance is calculated after a new path has been checked.
	 */
	public void run() {
		Scanner input = new Scanner(System.in);
		Vertex current;
		System.out.print("Starting point/vertex: ");
		Vertex predecessor = (Vertex) vertices.get(input.next());
		System.out.print("Destination point/vertex: ");
		Vertex destination = (Vertex) vertices.get(input.next());
		predecessor.setDistance(0);
		pq.add(predecessor);
		int checked = 0;
		System.out.printf("\n\n%s%15s%15s\n", "Next Destination", "Weight", "Path");
		System.out.printf("%s%15s%15s\n", "----------------", "------", "----");
		while (checked < vertices.size()) {
			current = pq.poll();
			int vertWeight = current.getDistance();
			if (current.known == false) {
				checked++;
				current.known = true;
				compAdjEdges(current, vertWeight);
				System.out.format("%8s", current.name);
				System.out.format("%20d%14s", (int) current.distance, " ");
				printPath(current);
				System.out.println();
			}
			if (current == destination) {
				break;
			}
		}
	}


	/**
	 * Compare the weights of the edges within the priority queue to find
	 * out the quickest way to the final destination.
	 * 
	 * @param v - the parent vertex
	 * @param u - the weight of the edge from that vertex to destination
	 */
	public void compAdjEdges(Vertex v, int u) {
		Vertex source = v;
		int vertWeight = u;
		int currDist;
		int origDist;
	
		for (Edge e : source.names) {
			Edge curEdge = e;
			Vertex curVer = e.getDestination();
			origDist = curVer.getDistance();
			if (curVer.known == false) {
				currDist = curEdge.getWeight();
				currDist = currDist + vertWeight;
				if (currDist < origDist) {
					curVer.setDistance(currDist);
					curVer.previous = source;
					pq.add(curVer);
				}
			}
		}
	}
	

	/**
	 * Inner class that specifies the vertex object. Creates the first
	 * dimension of the adjacency matrix
	 * 
	 * @author Derek J. Grove
	 *
	 */
	public class Vertex implements Comparator<Vertex> {
		public String name;
		public LinkedList<Edge> names;
		public int distance;
		public Vertex previous;
		public boolean known;
		public int defaultDis = Integer.MAX_VALUE;

		/**
		 * Default constructor when there needs a space
		 * to be filled
		 */
		public Vertex() {
			name = null;
			distance = defaultDis;
			previous = null;
			known = false;
		}

		/**
		 * Constructor for Vertex when a name of the vertex
		 * is specified
		 * @param name - the specified name
		 */
		public Vertex(String name) {
			this.name = name;
			names = new LinkedList<Edge>();
			distance = defaultDis;
			previous = null;
			known = false;
		}

		/**
		 * Get the distance of the path
		 * @return the distance
		 */
		public int getDistance() {
			return distance;
		}

		/**
		 * Set the distance of the path
		 * @param w - the weight of the distance
		 */
		public void setDistance(int w) {
			distance = w;
		}

		/**
		 * If there are different paths that can be taken,
		 * find out which way is the quickest.
		 */
		public int compare(Vertex v1, Vertex v2) {
			int w1 = v1.getDistance();
			int w2 = v2.getDistance();
			if (w1 > w2)
				return 1;
			else if (w1 < w2)
				return -1;
			else
				return 0;
		}
	}

	/**
	 * Edge is an inner class that provides a link between
	 * two vertices.
	 * 
	 * @author Derek J. Grove
	 *
	 */
	public class Edge {
		public Vertex destination;
		public int weight;

		/**
		 *  Default constructor
		 */
		public Edge() {
			destination = null;
			weight = 1;
		}

		/**
		 * 
		 * @param v - the destination vertex
		 * @param w - the weight that it took to get there
		 */
		public Edge(Vertex v, int w) {
			destination = v;
			weight = w;
		}

		//Getter methods
		
		public int getWeight() {
			return weight;
		}

		public Vertex getDestination() {
			return destination;
		}
	}
}