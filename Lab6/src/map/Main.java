package map;

import java.util.Random;

public class Main {
	private static final int ITERATIONS = 100;
	private static final String[] REGIONS = {"blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland"};
	
	public static void main(String[] args) {
		SimpleHashMap<String, Integer> map = new SimpleHashMap<String, Integer>(10);
		Random random = new Random();
		addRandomElements(map, random);
		System.out.println("Den öppna hashtabellen:\n\n" + map.show());
		System.out.println("------------------------------------\n");
		removeAllElements(map);
		System.out.println("Efter borttagning av samtliga noder:\n\n" + map.show());
	}
	
	private static void addRandomElements(SimpleHashMap<String, Integer> map, Random random) {
		for (int i = 0; i < ITERATIONS; i++) {
			map.put(REGIONS[random.nextInt(REGIONS.length)], random.nextInt(100));
		}
	}
	
	private static void removeAllElements(SimpleHashMap<String, Integer> map) {
		for (int i = 0; i < REGIONS.length; i++) {
			map.remove(REGIONS[i]);
		}
	}
}