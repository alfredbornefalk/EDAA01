package mapexample;

public class Main {
	public static void main(String[] args) {
		FrequencyCounter<String> fc = new FrequencyCounter<>();
		System.out.println(fc.number("Hej"));
		fc.register("Wilma");
		System.out.println(fc.number("Wilma"));
	}
}