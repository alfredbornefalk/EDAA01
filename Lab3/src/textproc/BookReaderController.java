package textproc;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BookReaderController {
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 100, 300));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		List<Map.Entry<String, Integer>> listPairs = counter.getWordList();
		SortedListModel<Map.Entry<String, Integer>> listModel = new SortedListModel<>(listPairs);
		JList<Map.Entry<String, Integer>> listView = new JList<>(listModel);
		listView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(listView);
		scrollPane.setPreferredSize(new Dimension(200, 100));
		scrollPane.setBorder(new EmptyBorder(5, 10, 5, 10));
		pane.add(scrollPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 100));
		
		JRadioButton alphabetic = new JRadioButton("Alphabetic", true);
		JRadioButton frequency = new JRadioButton("Frequency", false);
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(alphabetic);
		buttonGroup.add(frequency);
		
		alphabetic.addActionListener(event -> {
			listModel.sort((e1, e2) -> ((Entry<String, Integer>) e1).getKey().compareTo(((Entry<String, Integer>) e2).getKey()));
			listView.ensureIndexIsVisible(0);
		});
		
		frequency.addActionListener(event -> {
			listModel.sort((e1, e2) -> ((Entry<String, Integer>) e2).getValue() - ((Entry<String, Integer>) e1).getValue());
			listView.ensureIndexIsVisible(0);
		});
		
		JTextArea textInput = new JTextArea("Search");
		textInput.setBackground(Color.WHITE);
		textInput.setForeground(Color.BLACK);
		
		JButton find = new JButton("Find");
		
		find.addActionListener(event -> {
			String word = textInput.getText().trim();
			String wordMod = word.toLowerCase();
			int count = 0;
			
			for (int i = 0; i < listModel.getSize(); i++) {
				if (listModel.getElementAt(i).getKey().equals(wordMod)) {
					listView.setSelectedIndex(i);
					listView.ensureIndexIsVisible(i);
					count++;
					break;
				}
			}
			
			if (count == 0) {
			    String message = word + " cannot be found in this list.";
			    JOptionPane.showMessageDialog(new JFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		panel.add(alphabetic);
		panel.add(frequency);
		panel.add(find);
		panel.add(textInput);
		
		pane.add(panel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
	}
}