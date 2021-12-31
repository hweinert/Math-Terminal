import java.util.Scanner;

import java.io.File;
import java.io.FileOutputStream; 
import java.io.ObjectOutputStream;
import java.io.FileInputStream; 
import java.io.ObjectInputStream;
import java.io.IOException;

import littlelib.StopWatch;
import littlelib.Tools;
import littlelib.score.HighScoreList;

public class Game {
	private static final int NUM_OF_EXERCISES = 20;
	private static final String SAVE_PATH = "Mathematician.data";
	private static Scanner scanner = new Scanner(System.in); 
	
	private HighScoreList scoreList;
	
	public Game() {
		File save = new File(SAVE_PATH);
		if (save.exists()) {
			load();
		} else {
			scoreList = new HighScoreList("Mathematician Ranking", 5, "Seconds", true);
		}
	}
	
	public void mainLoop() {
		while (true) {
			System.out.println("[1] Start Game");
			System.out.println("[2] View Highscore List");
			System.out.println("[3] Exit");
			System.out.print("Input: ");
			String choice = scanner.nextLine().trim();
			System.out.println();
			
			if (choice.equals("1")) {
				play();
			} else if (choice.equals("2")) {
				showHighScore();
			} else if (choice.equals("3")) {
				System.out.println("Okay.");
				break;
			} else {
				System.out.println("Your input is invalid.");
			}
			System.out.println();
		}
	}
	
	private void play() {
		System.out.printf("Solve %d exercises as fast as you can!\n", NUM_OF_EXERCISES);
		System.out.println("Division exercises have to be solved without rest.\n");
		
		// measure time for high score placement
		StopWatch watch = new StopWatch();
		watch.start();
				
		for (int i = 0; i < NUM_OF_EXERCISES; i++) {
			Exercise exercise = Exercise.getRandomExercise();
			int guess = Tools.saveIntInput(exercise.toString());
			if (exercise.compare(guess)) {
				System.out.println("Right");
			} else {
				System.out.println("Wrong. Game over.");
				return;
			}
		}
		
		watch.stop();
		
		checkForHighScore(watch);
	}
	
	private void showHighScore() {
		System.out.print(scoreList);
	}
	
	private void checkForHighScore(StopWatch watch) {
		if (scoreList.checkIfWorthy(watch.getSeconds())) {
			System.out.println("\nCongratulations! You reached a new high score!");
			String name = Tools.saveStringInput("Enter your name: ", 3, 20);
			scoreList.add(name, watch.getSeconds());
			save();
		}
	}
	
	public void save() {
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH))) {
			oos.writeObject(scoreList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_PATH))) {
			scoreList = (HighScoreList)ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
