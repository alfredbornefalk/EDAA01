package textproc;

import java.util.ArrayList;
// import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class GeneralWordCounter implements TextProcessor {
	public static final Integer SIZE = 10;
	Map<String, Integer> counts = new TreeMap<String, Integer>();
	Set<String> stopwords;
	
	public GeneralWordCounter(Set<String> stopwords) {
		this.stopwords = stopwords;
	}

	public void process(String w) {
		if (!stopwords.contains(w)) {
			if (counts.containsKey(w)) {
				counts.put(w, counts.get(w) + 1);
			} else {
				counts.put(w, 1);
			}
		}
	}

	public void report() {
		Set<Map.Entry<String, Integer>> wordSet = counts.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		
		for (int i = 0; i < SIZE; i++) {
			System.out.println(wordList.get(i).toString());
		}
	}
	
	public List<Map.Entry<String, Integer>> getWordList() {
		return new ArrayList<Map.Entry<String, Integer>>(counts.entrySet());
	}
}