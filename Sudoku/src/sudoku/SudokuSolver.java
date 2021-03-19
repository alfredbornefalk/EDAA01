package sudoku;

public interface SudokuSolver {
	/**
	 * Sets the number nbr in box r, c.
	 * 
	 * @param r
	 * 			The row
	 * @param c
	 *       	The column
	 * @param nbr
	 *         	The number to insert in box r, c
	 * @throws IllegalArgumentException        
	 *        	if r or c is outside [0...getDimension()-1] or
	 *          number is outside [1...9] 
	 */
	public void setNumber(int r, int c, int nbr);
	
	/**
	 * Returns the number in box r, c. If the box is empty 0 is returned.
	 * 
	 * @param r
	 *         	The row
	 * @param c
	 *       	The column
	 * @return the number in box r, c or 0 if the box is empty
	 * @throws IllegalArgumentException
	 *         	if r or c is outside [0...getDimension()-1]
	 */
	public int getNumber(int r, int c);
	
	/**
	 * Empties the box r, c by setting its value to zero.
	 * 
	 * @param r
	 * 			The row
	 * @param c
	 * 			The column
	 * @throws IllegalArgumentException
	 * 			if r or c is outside [0...getDimension()-1]
	 */
	public void clearNumber(int r, int c);
	
	/**
	 * Checks whether the value nbr in the box r, c is OK according
	 * to the rules of sudoku or not.
	 * 
	 * @param r
	 * 			The row
	 * @param c
	 * 			The column
	 * @param nbr
	 * 			The value to be checked
	 * @return true if nbr is valid in box r, c, otherwise false
	 * @throws IllegalArgumentException
	 * 			if r or c is outside [0...getDimension()-1] or
	 *          number is outside [1...9]
	 */
	public boolean isValid(int r, int c, int nbr);

	/**
	 * Checks whether all the boxes in the grid are marked
	 * correctly or not.
	 * 
	 * @return true if all the boxes are OK, otherwise false
	 */
	public boolean isAllValid();
		
	/**
	 * Tries to solve the sudoku.
	 * 
	 * @return true if the sudoku was solved, otherwise false
	 */
	public boolean solve();
		
	/**
	 * Empties all the boxes in the sudoku.
	 */
	public void clear();
		
	/**
	 * Returns the numbers in the grid. An empty box is represented
	 * by the value 0.
	 * 
	 * @return the numbers in the grid
	 */
	public int[][] getMatrix();

	/**
	 * Fills the grid with the numbers in nbrs.
	 * 
	 * @param nbrs
	 * 			The matrix with the numbers to insert
	 * @throws IllegalArgumentException
	 *       	if nbrs have wrong dimension or containing values
	 *        	not in [0...9] 
	 */
	public void setMatrix(int[][] nbrs);
		
	/**
	 * Returns the dimension of the grid.
	 * 
	 * @return the dimension of the grid
	 */
	public default int getDimension() {
		return (int) Math.pow(3, 2);
	}
}