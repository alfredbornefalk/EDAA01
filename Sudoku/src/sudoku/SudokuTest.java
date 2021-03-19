package sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SudokuTest {
	private Sudoku sudoku;
	private int[][] board;
	private static final int SIZE = 9;
	
	@BeforeEach
	public void setUp() throws Exception {
		sudoku = new Sudoku();
		board = new int[SIZE][SIZE];
	}
	
	@AfterEach
	public void tearDown() throws Exception {
		sudoku = null;
		board = null;
	}
	
	@Test
	public final void testSetNumberAndGetNumber() {
		board = new int[][] {{2, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sudoku.setMatrix(board);
		assertEquals(sudoku.getNumber(0, 0), 2, "Problems with the get number method");
		sudoku.setNumber(0, 0, 5);
		assertEquals(sudoku.getNumber(0, 0), 5, "Problems with the set number method!");
		
		try {
			sudoku.setNumber(0, 0, -1);
			fail("Wrong number; should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			
		}
		
		try {
			sudoku.setNumber(-1, 11, 5);
			fail("Wrong square; should raise an IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public final void testClearNumber() {
		sudoku.setNumber(0, 0, 2);
		sudoku.clearNumber(0, 0);
		assertEquals(sudoku.getNumber(0, 0), 0, "Problems with the clear number method");
	}
	
	@Test
	public final void testIsValid() {
		sudoku.setNumber(0, 0, 2);
		assertTrue(sudoku.isValid(4, 6, 3));
		assertFalse(sudoku.isValid(0, 5, 2));
		assertFalse(sudoku.isValid(5, 0, 2));
		assertFalse(sudoku.isValid(1, 2, 2));
	}
	
	@Test
	public final void testIsAllValid() {
		assertTrue(sudoku.isAllValid());
		sudoku.setNumber(0, 0, 2);
		sudoku.setNumber(0, 5, 2);
		assertFalse(sudoku.isAllValid());
	}
	
	@Test
	public final void testSolveEmptySudoku() {
		board = new int[][] {{0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0},
							 {0, 0, 0, 0, 0, 0, 0, 0, 0}};
		sudoku.setMatrix(board);
		assertTrue(sudoku.solve());
	}
	
	@Test
	public final void testSolveCourseSudoku() {
		board = new int[][] {{0, 0, 8, 0, 0, 9, 0, 6, 2},
							 {0, 0, 0, 0, 0, 0, 0, 0, 5},
							 {1, 0, 2, 5, 0, 0, 0, 0, 0},
							 {0, 0, 0, 2, 1, 0, 0, 9, 0},
							 {0, 5, 0, 0, 0, 0, 6, 0, 0},
							 {6, 0, 0, 0, 0, 0, 0, 2, 8},
							 {4, 1, 0, 6, 0, 8, 0, 0, 0},
							 {8, 6, 0, 0, 3, 0, 1, 0, 0},
							 {0, 0, 0, 0, 0, 0, 4, 0, 0}};
		sudoku.setMatrix(board);
		assertTrue(sudoku.solve());
	}
	
	@Test
	public final void testSolveImpossibleRowSudoku() {
		board = new int[][] {{0, 0, 8, 0, 0, 9, 8, 6, 2},
							 {0, 0, 0, 0, 0, 0, 0, 0, 5},
							 {1, 0, 2, 5, 0, 0, 0, 0, 0},
							 {0, 0, 0, 2, 1, 0, 0, 9, 0},
							 {0, 5, 0, 0, 0, 0, 6, 0, 0},
							 {6, 0, 0, 0, 0, 0, 0, 2, 8},
							 {4, 1, 0, 6, 0, 8, 0, 0, 0},
							 {8, 6, 0, 0, 3, 0, 1, 0, 0},
							 {0, 0, 0, 0, 0, 0, 4, 0, 0}};
		sudoku.setMatrix(board);
		assertFalse(sudoku.solve());
	}
	
	@Test
	public final void testSolveImpossibleColSudoku() {
		board = new int[][] {{0, 0, 8, 0, 0, 9, 0, 6, 2},
							 {0, 0, 0, 0, 0, 0, 0, 0, 5},
							 {1, 0, 2, 5, 0, 0, 0, 0, 0},
							 {1, 0, 0, 2, 1, 0, 0, 9, 0},
							 {0, 5, 0, 0, 0, 0, 6, 0, 0},
							 {6, 0, 0, 0, 0, 0, 0, 2, 8},
							 {4, 1, 0, 6, 0, 8, 0, 0, 0},
							 {8, 6, 0, 0, 3, 0, 1, 0, 0},
							 {0, 0, 0, 0, 0, 0, 4, 0, 0}};
		sudoku.setMatrix(board);
		assertFalse(sudoku.solve());
	}
	
	@Test
	public final void testSolveImpossibleSubMatrixSudoku() {
		board = new int[][] {{0, 0, 8, 0, 0, 9, 0, 6, 2},
							 {0, 2, 0, 0, 0, 0, 0, 0, 5},
							 {1, 0, 2, 5, 0, 0, 0, 0, 0},
							 {0, 0, 0, 2, 1, 0, 0, 9, 0},
							 {0, 5, 0, 0, 0, 0, 6, 0, 0},
							 {6, 0, 0, 0, 0, 0, 0, 2, 8},
							 {4, 1, 0, 6, 0, 8, 0, 0, 0},
							 {8, 6, 0, 0, 3, 0, 1, 0, 0},
							 {0, 0, 0, 0, 0, 0, 4, 0, 0}};
		sudoku.setMatrix(board);
		assertFalse(sudoku.solve());
	}
	
	@Test
	public final void testClear() {
		sudoku.setNumber(0, 0, 5);
		sudoku.setNumber(3, 2, 7);
		sudoku.clear();
		assertEquals(sudoku.getNumber(0, 0), 0, "Should equal zero");
		assertNotEquals(sudoku.getNumber(3, 2), 7, "Should not equal seven");
	}
	
	@Test
	public final void testGetMatrix() {
		sudoku.setNumber(0, 0, 5);
		int[][] copyMatrix = sudoku.getMatrix();
		assertEquals(copyMatrix[0][0], 5, "Should equal five");
	}
	
	@Test
	public final void testSetMatrix() {
		board = new int[][] {{0, 0, 8, 0, 0, 9, 0, 6, 2},
							 {0, 2, 0, 0, 0, 0, 0, 0, 5},
							 {1, 0, 2, 5, 0, 0, 0, 0, 0},
							 {0, 0, 0, 2, 1, 0, 0, 9, 0},
							 {0, 5, 0, 0, 0, 0, 6, 0, 0},
							 {6, 0, 0, 0, 0, 0, 0, 2, 8},
							 {4, 1, 0, 6, 0, 8, 0, 0, 0},
							 {8, 6, 0, 0, 3, 0, 1, 0, 0},
							 {0, 0, 0, 0, 0, 0, 4, 0, 0}};
		sudoku.setMatrix(board);
		assertEquals(sudoku.getNumber(2, 0), 1, "Should equal one");
	}
	
	@Test
	public final void testGetDimension() {
		assertEquals(sudoku.getDimension(), 9, "Wrong dimension");
	}
}