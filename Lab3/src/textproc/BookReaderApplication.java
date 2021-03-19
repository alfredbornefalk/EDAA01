package textproc;

import java.awt.Dimension;
import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import java.io.FileNotFoundException;

public class BookReaderApplication {
	public static void main (String[] args) throws FileNotFoundException {
		new BookReaderController(fromLabOne());
	}
	
	private static GeneralWordCounter fromLabOne() throws FileNotFoundException {
		Set<String> exceptions = new HashSet<String>();
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		scan.findWithinHorizon("\uFEFF", 1);
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

		while (scan.hasNext()) {
			exceptions.add(scan.next().toLowerCase());
		}
		
		scan.close();
		GeneralWordCounter gwc = new GeneralWordCounter(exceptions);
		scan = new Scanner(new File("nilsholg.txt"));
		scan.findWithinHorizon("\uFEFF", 1);
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

		while (scan.hasNext()) {
			gwc.process(scan.next().toLowerCase());
		}
		
		scan.close();
		return gwc;
	}
}