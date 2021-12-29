import java.util.concurrent.ThreadLocalRandom;

public class Exercise {
	// lower and upper range for first and second number for: summation, subtraction, multiplication, division
	private static int[][] numRange = {{5, 30, 5, 30}, {5, 30, 5, 30}, {5, 20, 5, 20}, {10, 100, 2, 15}};
	
	private int num1;
	private int num2;
	private char operationSymbol;
	private int solution;
	
	private Exercise(int num1, int num2, char operationSymbol, int solution) {
		this.num1 = num1;
		this.num2 = num2;
		this.operationSymbol = operationSymbol;
		this.solution = solution;
	}
	
	public static Exercise getRandomExercise() {
		int numOfTypes = 4;
		int choice = ThreadLocalRandom.current().nextInt(numOfTypes);
		
		int num1 = ThreadLocalRandom.current().nextInt(numRange[choice][0], numRange[choice][1] + 1);
		int num2 = ThreadLocalRandom.current().nextInt(numRange[choice][2], numRange[choice][3] + 1);
		char operationSymbol;
		int solution;
		
		if (choice == 0) {
			operationSymbol = '+';
			solution = num1 + num2;
		} else if (choice == 1) {
			operationSymbol = '-';
			solution = num1 - num2;
		} else if (choice == 2) {
			operationSymbol = 'x';
			solution = num1 * num2;
		} else {
			operationSymbol = '/';
			solution = num1 / num2;
		}
		
		return new Exercise(num1, num2, operationSymbol, solution);
	}
	
	@Override
	public String toString() {
		return String.format("%d %c %d = ", num1, operationSymbol, num2);
	}
	
	public boolean compare(int num) {
		return num == solution;
	}
}
