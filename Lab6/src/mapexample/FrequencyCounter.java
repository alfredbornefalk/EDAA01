package mapexample;

import java.util.HashMap;
import java.util.Map;
// import java.util.TreeMap;

public class FrequencyCounter<E> {
	private Map<E, Integer> map;
	
	/** Skapar en frekvensräknare */
	public FrequencyCounter() {
		map = new HashMap<E, Integer>();
	}
	
	/** Noterar en ny förekomst av e */
	public void register(E e) {
		map.put(e, number(e) + 1);
	}
	
	/** Noterar en ny förekomst av alla element i elements */
	public void registerAll(E[] elements) {
		for (int i = 0; i < elements.length; i++) {
			register(elements[i]);
		}
	}
	
	/** Tar reda på antalet förekomster av e */
	public int number(E e) {
		if (map.containsKey(e)) {
			return map.get(e);
		}
		
		return 0;
	}
}