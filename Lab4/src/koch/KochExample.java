package koch;

import java.util.ArrayList;

public class KochExample {
	public static void main(String[] args) {
		KochExample ke = new KochExample();
		ke.draw(4, 810);
	}
	
	public void draw(int order, double length) {
		ArrayList<Integer> reached = new ArrayList<Integer>();
		System.out.println("Storlek innan: " + reached.size());
		fractalLine(order, length, 0, reached);
		System.out.println("Storlek efter: " + reached.size());
	}
	
	private void fractalLine(int order, double length, double alpha, ArrayList<Integer> reached) {
		if (order == 0) {
			if (reached.size() == 0) {
				System.out.println("Längd: " + length);
			}
			
			reached.add(0);
		} else {
			fractalLine(order - 1, length / 3, alpha, reached);
			fractalLine(order - 1, length / 3, alpha - 60, reached);
			fractalLine(order - 1, length / 3, alpha + 60, reached);
			fractalLine(order - 1, length / 3, alpha, reached);
		}
	}
}