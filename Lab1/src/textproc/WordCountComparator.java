package textproc;

import java.util.*;
import java.util.Map.Entry;

public class WordCountComparator implements Comparator<Map.Entry<String, Integer>> {
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
		if (!o1.getValue().equals(o2.getValue())) {
			return (o2.getValue() - o1.getValue());
		}
		
		return (o1.getKey().compareTo(o2.getKey()));
	}
}