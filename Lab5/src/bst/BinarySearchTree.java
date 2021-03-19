package bst;

import java.util.ArrayList;
import java.util.Comparator;

public class BinarySearchTree<E> {
	BinaryNode<E> root;
	int size;
	private Comparator<E> comparator;
    
	/**
	 * Constructs an empty binary search tree.
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchTree() {
		root = null;
		size = 0;
		comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}
	
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}
	
	public static void main(String[] args) {
		unbalanced(new BinarySearchTree<Integer>());
		balanced(new BinarySearchTree<String>((e1, e2) -> e1.compareTo(e2)));
		perfect(new BinarySearchTree<Character>());
		unbalancedToBalanced(new BinarySearchTree<Integer>());
	}
	
	private static void unbalanced(BinarySearchTree<Integer> intTree) {
		intTree.add(1);
		intTree.add(2);
		intTree.add(0);
		intTree.add(3);
		intTree.add(5);
		intTree.add(4);
		intTree.printTree();
		System.out.println("(Höjd = " + intTree.height() + ')');
		visualize("Obalanserat sökträd", intTree);
	}
	
	private static void balanced(BinarySearchTree<String> stringTree) {
		stringTree.add("Kjelle");
		stringTree.add("Nisse");
		stringTree.add("Pelle");
		stringTree.add("Bosse");
		stringTree.printTree();
		System.out.println("(Höjd = " + stringTree.height() + ')');
		visualize("Balanserat sökträd", stringTree);
	}
	
	private static void perfect(BinarySearchTree<Character> charTree) {
		charTree.add('d');
		charTree.add('b');
		charTree.add('a');
		charTree.add('c');
		charTree.add('f');
		charTree.add('e');
		charTree.add('g');
		charTree.printTree();
		System.out.println("(Höjd = " + charTree.height() + ')');
		visualize("Perfekt sökträd", charTree);
	}
	
	private static void unbalancedToBalanced(BinarySearchTree<Integer> unsorted) {
		unsorted.add(10);
		unsorted.add(12);
		unsorted.add(17);
		unsorted.add(15);
		unsorted.add(4);
		unsorted.add(14);
		unsorted.add(7);
		unsorted.add(0);
		unsorted.add(1);
		unsorted.add(27);
		unsorted.add(33);
		unsorted.add(5);
		unsorted.add(2);
		unsorted.add(9);
		unsorted.printTree();
		System.out.println("(Höjd = " + unsorted.height() + ')');
		visualize("Ej AVL-träd", unsorted);
		
		unsorted.rebuild();
		BinarySearchTree<Integer> AVLTree = unsorted;
		AVLTree.printTree();
		System.out.println("(Höjd = " + AVLTree.height() + ')');
		visualize("AVL-träd", AVLTree);
	}
	
	private static void visualize(String title, BinarySearchTree<?> bst) {
		BSTVisualizer visualizer = new BSTVisualizer(title, 200, 200);
		visualizer.drawTree(bst);
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		
		return add(root, x);
	}
	
	private boolean add(BinaryNode<E> n, E x) {
		if (comparator.compare(x, n.element) == 0) {
			return false;
		} else if (comparator.compare(x, n.element) < 0) {
			if (n.left != null) {
				add(n.left, x);
			} else {
				n.left = new BinaryNode<E>(x);
				size++;
			}
		} else {
			if (n.right != null) {
				add(n.right, x);
			} else {
				n.right = new BinaryNode<E>(x);
				size++;
			}
		}
		
		return true;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		}
		
		return 1 + Math.max(height(n.left), height(n.right));
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.print(n.element + " ");
			printTree(n.right);
		}
	}
	
	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sorted = new ArrayList<E>(size);
		toArray(root, sorted);
		root = buildTree(sorted, 0, sorted.size());
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n != null) {
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last || first == sorted.size()) {
			return null;
		}
		
		int mid = first + ((last - first) / 2);
		BinaryNode<E> n = new BinaryNode<E>(sorted.get(mid));
		n.left = buildTree(sorted, first, mid - 1);
		n.right = buildTree(sorted, mid + 1, last);
		return n;
	}
	
	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left, right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
}