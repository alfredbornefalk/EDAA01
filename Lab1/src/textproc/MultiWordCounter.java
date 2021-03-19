package textproc;

import java.util.HashMap;
import java.util.Map;

public class MultiWordCounter implements TextProcessor {
	Map<String, Integer> frequency = new HashMap<String, Integer>();
	
	public MultiWordCounter(String[] landskap) {
		for (int i = 0; i < landskap.length; i++) {
			frequency.put(landskap[i], 0);
		}
	}
	
	public void process(String w) {
		if (frequency.containsKey(w)) {
			frequency.put(w, frequency.get(w) + 1);
		}
	}

	public void report() {
		for (String key : frequency.keySet()) {
			System.out.println(key + ": " + frequency.get(key));
		}
	}
}