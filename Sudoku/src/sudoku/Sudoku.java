package sudoku;

public class Sudoku implements SudokuSolver {
	private int[][] matrix;
	
	public Sudoku() {
		matrix = new int[getDimension()][getDimension()];
	}

	@Override
	public void setNumber(int r, int c, int nbr) {
		if (!validSquare(r, c)) {
			throw new IllegalArgumentException("Wrong square!");
		}
		
		if (!validNumber(nbr)) {
			throw new IllegalArgumentException("Wrong number!");
		}
		
		matrix[r][c] = nbr;
	}

	@Override
	public int getNumber(int r, int c) {
		if (!validSquare(r, c)) {
			throw new IllegalArgumentException("Wrong square");
		}
		
		return matrix[r][c];
	}

	@Override
	public void clearNumber(int r, int c) {
		if (!validSquare(r, c)) {
			throw new IllegalArgumentException("Wrong square!");
		}
		
		setNumber(r, c, 0);
	}

	@Override
	public boolean isValid(int r, int c, int nbr) {
		if (!validSquare(r, c)) {
			throw new IllegalArgumentException("Wrong square!");
		}
		
		if (!validNumber(nbr)) {
			throw new IllegalArgumentException("Wrong number");
		}
		
		if (nbr == 0) {
			return true;
		}
		
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[i][c] == nbr && i != r) {
				return false;
			}
		}
		
		for (int j = 0; j < matrix[r].length; j++) {
			if (matrix[r][j] == nbr && j != c) {
				return false;
			}
		}
		
		int subSize = (int) Math.sqrt(getDimension());
		int rowStart = r - (r % subSize);
		int colStart = c - (c % subSize);
		
		for (int i = rowStart; i < rowStart + subSize; i++) {
			for (int j = colStart; j < colStart + subSize; j++) {
				if (matrix[i][j] == nbr && i != r && j != c) {
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean isAllValid() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (!isValid(i, j, matrix[i][j])) {
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}
	
	private boolean solve(int r, int c) {
		if (c >= getDimension()) {
			c = 0;
			r++;
		}
		
		if (r == getDimension() - 1 && c == getDimension() - 1) {
			for (int n = 1; n <= getDimension(); n++) {
				if (isValid(r, c, n)) {
					setNumber(r, c, n);
					return true;
				}
			}
			
			return false;
		}
		
		if (getNumber(r, c) != 0) {
			if (isValid(r, c, getNumber(r, c))) {
				return solve(r, c + 1);
			}
			
			return false;
		}
		
		for (int n = 1; n <= getDimension(); n++) {
			if (isValid(r, c, n)) {
				setNumber(r, c, n);
				
				if (solve(r, c + 1)) {
					return true;
				}
				
				clearNumber(r, c);
			}
		}
		
		return false;
	}

	@Override
	public void clear() {
		matrix = new int[getDimension()][getDimension()];
	}

	@Override
	public int[][] getMatrix() {
		int[][] copyMatrix = new int[getDimension()][getDimension()];
		
		for (int i = 0; i < getDimension(); i++) {
			for (int j = 0; j < getDimension(); j++) {
				copyMatrix[i][j] = matrix[i][j];
			}
		}
		
		return copyMatrix;
	}

	@Override
	public void setMatrix(int[][] nbrs) {
		if (nbrs.length != matrix.length || nbrs[0].length != matrix[0].length) {
			throw new IllegalArgumentException("Wrong dimension!");
		}
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (!validNumber(nbrs[i][j])) {
					throw new IllegalArgumentException("Wrong number!");
				}
				
				matrix[i][j] = nbrs[i][j];
			}
		}
	}
	
	private boolean validSquare(int r, int c) {
		return (r >= 0 && r < getDimension() && c >= 0 && c < getDimension());
	}
	
	private boolean validNumber(int nbr) {
		return (nbr >= 0 && nbr <= getDimension());
	}
}