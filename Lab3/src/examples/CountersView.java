package examples;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CountersView {

	public CountersView() {
		SwingUtilities.invokeLater(() -> createWindow());
	}

	private void createWindow() {
		JFrame frame = new JFrame("Counter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.add(new CounterPanel("Yes"));
		pane.add(new CounterPanel("No"));
		pane.add(new CounterPanel("Don't know"));
		
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new CountersView();
	}	
}