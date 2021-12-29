import java.util.Scanner;

public class Main {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		while (true) {
			System.out.println("[1] Start Game");
			System.out.println("[2] View Highscore List");
			System.out.println("[3] Exit");
			System.out.print("Input: ");
			String choice = scanner.nextLine().trim();
			System.out.println();
			
			if (choice.equals("1")) {
				Game game = new Game();
				game.loop();
			} else if (choice.equals("2")) {
				System.out.println("This feature is not implemented yet.");
			} else if (choice.equals("3")) {
				System.out.println("Okay.");
				break;
			} else {
				System.out.println("Your input is invalid.");
			}
			System.out.println();
		}
	}
}
