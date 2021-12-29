import java.util.Scanner;

public class Game {
	private static Scanner scanner = new Scanner(System.in); 
	
	private static int numOfExercises = 20;
	
	public void loop() {
		for (int i = 0; i < numOfExercises; i++) {
			Exercise exercise = Exercise.getRandomExercise();
			System.out.print(exercise);
			int guess = Integer.parseInt(scanner.nextLine());
			if (exercise.compare(guess)) {
				System.out.println("Right");
			} else {
				System.out.println("Wrong");
				break;
			}
		}
	}
}
