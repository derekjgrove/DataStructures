package BinarySearchTree;
/**
 * COSC 310-001    Assignment 6
 * MyBST.java
 * 
 * A class that provides a linked list implementation of
 * a tree that provides implementations of methods, add(E item),
 * find(E target), and a toString().
 * 
 * @author  Derek J. Grove
 *
 */
public class MyBST<E extends Comparable> {
	private Node<E> root;
	private int size;

	/**
	 * Constructor to create the Binary Search Tree
	 */
	public MyBST() {
		root = null;
	}

	/**
	 * Node is a nested class to allocate the list to
	 * handle nodes.
	 * 
	 * @author Derek J. Grove
	 *
	 * @param <E> - generics
	 */
	private static class Node<E> {
		private E data;
		private Node<E> left;
		private Node<E> right;

		/**
		 * Constructor for when a node is created with no reference
		 * to a left or right subtree, so this node will be a leaf.
		 * @param data - the data referenced by the node.
		 */
		private Node(E data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

		/**
		 * Constructor for when a node is not a leaf and has a left
		 * subtree or right subtree or both.
		 * @param data - the data referenced by the node.
		 * @param left - the left subtree
		 * @param right - the right subtree
		 */
		private Node(E data, Node<E> left, Node<E> right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

	}

	/**
	 * Adds item to the tree
	 * @param item - the item to be added
	 * @return true if add was successful
	 */
	public boolean add(E item) {

		if (root == null) {
			root = new Node<E>(item);
			return true;
		} else {
			return add(root, item);
		}

	}

	/**
	 * Recursive add method
	 * @param localRoot - the local subtree's root
	 * @param item - the current item
	 * @return where which tree to add to
	 */
	private boolean add(Node<E> localRoot, E item) {
		if (item.compareTo(localRoot.data) == 0) {
			return false;
		} else if (item.compareTo(localRoot.data) < 0) {
			if (localRoot.left == null) {
				localRoot.left = new Node<E>(item);
				return true;
			} else {
				return add(localRoot.left, item);
			}
		} else {
			if (localRoot.right == null) {
				localRoot.right = new Node<E>(item);
				return true;
			} else {
				return add(localRoot.right, item);
			}
		}
	}

	/**
	 * Perform a preorder traversal (visit, go left, go right)
	 * @param node - the local root
	 * @param depth - the level of the node
	 * @param sb - the string buffer to save the output
	 */
	private void preOrderTraverse(Node<E> node, int depth, StringBuilder sb) {
		for (int i = 1; i < depth; i++) {
			sb.append("  ");
		}
		if (node == null) {
			sb.append("null\n");
		} else {
			sb.append(node.data.toString());
			sb.append("\n");
			preOrderTraverse(node.left, depth + 1, sb);
			preOrderTraverse(node.right, depth + 1, sb);

		}
	}

	/**
	 * Displays the contents as a formatted binary tree.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		preOrderTraverse(root, 1, sb);
		return sb.toString();
	}

	/**
	 * Finds the target within the tree
	 * @param target - the item to founded
	 * @return the item found or null
	 */
	public E find(E target) {
		if (root == null) {
			return null;
		} else {
			return find(root, target);
		}

	}

	/**
	 * Recursive find method
	 * @param localRoot - the local subtree's root
	 * @param target - the object to be found
	 * @return the object if found
	 */
	private E find(Node<E> localRoot, E target) {
		if (localRoot == null) {
			return null;
		}
		if (target.compareTo(localRoot.data) == 0) {
			return localRoot.data;
		} else if (target.compareTo(localRoot.data) < 0) {
			return find(localRoot.left, target);
		} else {
			return find(localRoot.right, target);
		}
	}

	/**
	 * Main method to ensure validity of the Binary Search Class
	 * @param args - none
	 */
	public static void main(String[] args) {
		MyBST<String> myBST = new MyBST<String>();
		myBST.add("one");
		myBST.add("two");
		myBST.add("three");
		myBST.add("four");
		myBST.add("five");
		myBST.add("six");
		System.out.println(myBST);

	}

}
