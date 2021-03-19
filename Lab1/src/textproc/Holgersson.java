package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {
	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		long t0 = System.nanoTime();
		
		Set<String> exceptions = new HashSet<String>();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		scan.findWithinHorizon("\uFEFF", 1);
		scan.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		while (scan.hasNext()) {
			exceptions.add(scan.next().toLowerCase());
		}
		
		scan.close();
		
		SingleWordCounter swc = new SingleWordCounter("norge");
		MultiWordCounter mwc = new MultiWordCounter(REGIONS);
		GeneralWordCounter gwc = new GeneralWordCounter(exceptions);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.findWithinHorizon("\uFEFF", 1);
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		
		while (s.hasNext()) {
			String currentWord = s.next().toLowerCase();
			swc.process(currentWord);
			mwc.process(currentWord);
			gwc.process(currentWord);
		}
		
		s.close();
		
		swc.report();
		System.out.println("\n------------------\n");
		mwc.report();
		System.out.println("\n------------------\n");
		gwc.report();
		System.out.println("\n------------------\n");
		
		long t1 = System.nanoTime();
		System.out.println("Exekveringstid: " + ((t1 - t0) / 1000000.0) + " ms");
	}
}