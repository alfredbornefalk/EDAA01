package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.*;

public class SudokuController {
	private static final Color DARK = new Color(32, 32, 32);
	private static final Color WHITE = new Color(255, 255, 255);
	private static final Color RED = new Color(255, 50, 0);
	
	public SudokuController(SudokuSolver sudoku) {
		SwingUtilities.invokeLater(() -> createWindow(sudoku, "Sudoku",
				sudoku.getDimension() * 50, sudoku.getDimension() * 35));
	}
	
	public void createWindow(SudokuSolver sudoku, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(RED);
		Container pane = frame.getContentPane();
		
		JPanel panelSouth = new JPanel();
		panelSouth.setBackground(Color.DARK_GRAY);
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");
		panelSouth.add(solve);
		panelSouth.add(clear);
		frame.getRootPane().setDefaultButton(solve);
		
		JPanel panelNorth = new JPanel();
		int dimension = sudoku.getDimension();
		int subSize = (int) Math.sqrt(dimension);
		panelNorth.setLayout(new GridLayout(dimension, dimension));
		JTextField[][] grid = new JTextField[dimension][dimension];
		
		int counter = 0;
		JTextField temp;
		
		for (int i = 0; i < dimension; i += subSize) {
			for (int j = i; j < i + subSize; j++) {
				for (int k = 0; k < dimension; k += subSize) {
					for (int l = k; l < k + subSize; l++) {
						temp = new JTextField();
						
						if (counter % 2 == 0) {
							temp.setBackground(DARK);
							temp.setForeground(WHITE);
						} else {
							temp.setBackground(RED);
						}
						
						temp.setHorizontalAlignment(JTextField.CENTER);
						grid[j][l] = temp;
						panelNorth.add(temp);
					}
					
					counter++;	
				}
				
				if (dimension % 2 == 1) {
					counter++;
				}
			}
			
			counter++;
		}
		
		solve.addActionListener(event -> {
			sudoku.clear();
			boolean badInput = false;
			
			for (int r = 0; r < dimension; r++) {
				for (int c = 0; c < dimension; c++) {
					if (!grid[r][c].getText().isEmpty()) {
						try {
							int n = Integer.parseInt(grid[r][c].getText());
							
							if (n == 0) {
								badInput = true;
							} else {
								sudoku.setNumber(r, c, n);
							}
						} catch (IllegalArgumentException e) {
							badInput = true;
						}
					}
				}
			}
			
			if (badInput) {
				JOptionPane.showMessageDialog(new JOptionPane(), "Illegal input in the sudoku box. "
						+ "Please input an integer between 1 and " + dimension + '!');
			} else {
				if (sudoku.solve()) {
					for (int r = 0; r < dimension; r++) {
						for (int c = 0; c < dimension; c++) {
							if (grid[r][c].getText().isEmpty()) {
								grid[r][c].setText(Integer.toString(sudoku.getNumber(r, c)));
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(new JOptionPane(), "No solution exists :(");
				}
			}
		});
		
		clear.addActionListener(event -> {
			for (int r = 0; r < dimension; r++) {
				for (int c = 0 ; c < dimension; c++) {
					grid[r][c].setText("");
				}
			}
			
			sudoku.clear();
		});
		
		pane.add(panelSouth, BorderLayout.SOUTH);
		pane.add(panelNorth, BorderLayout.NORTH);
		
		frame.setSize(width, height);
		frame.setVisible(true);
	}
}