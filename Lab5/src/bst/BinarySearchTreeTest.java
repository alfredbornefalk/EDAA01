package bst;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bst.BinarySearchTree;
import java.util.Comparator;


class BinarySearchTreeTest {
	BinarySearchTree<Integer> myIntBinarySearchTree;
	BinarySearchTree<String> myStringBinarySearchTree;
	private Comparator<String> comparator;

	@BeforeEach
	public void setUp() throws Exception {
		myIntBinarySearchTree = new BinarySearchTree<Integer>();
		comparator = (e1, e2) -> e1.compareTo(e2);
		myStringBinarySearchTree = new BinarySearchTree<String>(comparator);
	}

	@Test
	void testHeightInt() {
		assertTrue(myIntBinarySearchTree.add(3));
		assertTrue(myIntBinarySearchTree.add(20));
		assertTrue(myIntBinarySearchTree.add(34));
		assertTrue(myIntBinarySearchTree.add(15));
		assertTrue(myIntBinarySearchTree.height() == 3);
	}
	
	@Test
	void testHeightString() {
		assertTrue(myStringBinarySearchTree.add("Kjelle"));
		assertTrue(myStringBinarySearchTree.add("Nisse"));
		assertTrue(myStringBinarySearchTree.add("Bosse"));
		assertTrue(myStringBinarySearchTree.height() == 2);
	}

	@Test
	void testHeightEmptyInt() {
		assertTrue(myIntBinarySearchTree.height() == 0);
	}
	
	@Test
	void testHeightEmptyString() {
		assertTrue(myStringBinarySearchTree.height() == 0);
	}
	
	@Test
	void testSizeEmptyInt() {
		assertTrue(myIntBinarySearchTree.size() == 0);
	}
	
	@Test
	void testSizeEmptyString() {
		assertTrue(myStringBinarySearchTree.size() == 0);
	}
	
	@Test
	void testAddInt() {
		assertTrue(myIntBinarySearchTree.add(3));
		assertTrue(myIntBinarySearchTree.add(21));
		assertTrue(myIntBinarySearchTree.add(45));
		assertTrue(myIntBinarySearchTree.add(20));
		assertTrue(myIntBinarySearchTree.root.element.equals(3));
		assertTrue(myIntBinarySearchTree.root.right.element.equals(21));
		assertTrue(myIntBinarySearchTree.root.right.right.element.equals(45));
		assertTrue(myIntBinarySearchTree.root.right.left.element.equals(20));
		System.out.print("Inorder-utskrift av heltalen: ");
		myIntBinarySearchTree.printTree();
		System.out.println();
	}
	
	@Test
	void testAddString() {
		assertTrue(myStringBinarySearchTree.add("Kjelle"));
		assertTrue(myStringBinarySearchTree.add("Nisse"));
		assertTrue(myStringBinarySearchTree.add("Bosse"));
		assertTrue(myStringBinarySearchTree.root.element.equals("Kjelle"));
		assertTrue(myStringBinarySearchTree.root.right.element.equals("Nisse"));
		assertTrue(myStringBinarySearchTree.root.left.element.equals("Bosse"));
		System.out.print("Inorder-utskrift av strängarna: ");
		myStringBinarySearchTree.printTree();
		System.out.println();
	}
	
	@Test
	void testAddDuplicatesInt() {
		assertTrue(myIntBinarySearchTree.add(3));
		assertFalse(myIntBinarySearchTree.add(3));
	}
	
	@Test
	void testAddDuplicatesString() {
		assertTrue(myStringBinarySearchTree.add("Kjelle"));
		assertFalse(myStringBinarySearchTree.add("Kjelle"));
	}
	
	@Test
	void testSizeInt() {
		assertTrue(myIntBinarySearchTree.add(3));
		assertTrue(myIntBinarySearchTree.add(21));
		assertTrue(myIntBinarySearchTree.add(45));
		assertTrue(myIntBinarySearchTree.add(20));
		assertTrue(myIntBinarySearchTree.size() == 4);
	}
	
	@Test
	void testSizeString() {
		assertTrue(myStringBinarySearchTree.add("Kjelle"));
		assertTrue(myStringBinarySearchTree.add("Nisse"));
		assertTrue(myStringBinarySearchTree.add("Bosse"));
		assertTrue(myStringBinarySearchTree.size() == 3);
	}
	
	@Test
	void testClearInt() {
		assertTrue(myIntBinarySearchTree.add(3));
		assertTrue(myIntBinarySearchTree.add(21));
		assertTrue(myIntBinarySearchTree.add(45));
		assertTrue(myIntBinarySearchTree.add(20));
		myIntBinarySearchTree.clear();
		assertTrue(myIntBinarySearchTree.root == null);
		assertTrue(myIntBinarySearchTree.size() == 0);
	}
	
	@Test
	void testClearString() {
		assertTrue(myStringBinarySearchTree.add("Kjelle"));
		assertTrue(myStringBinarySearchTree.add("Nisse"));
		assertTrue(myStringBinarySearchTree.add("Bosse"));
		myStringBinarySearchTree.clear();
		assertTrue(myStringBinarySearchTree.root == null);
		assertTrue(myStringBinarySearchTree.size() == 0);
	}
}