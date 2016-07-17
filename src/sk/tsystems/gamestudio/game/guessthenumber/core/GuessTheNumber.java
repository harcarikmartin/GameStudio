package sk.tsystems.gamestudio.game.guessthenumber.core;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.entity.Score;
import sk.tsystems.gamestudio.service.GameFinishedService;
import sk.tsystems.gamestudio.service.jdbc.ScoreJDBC;

public class GuessTheNumber {
	private static Pattern PATTERN = Pattern.compile("X|x");
	private static final int INTERVAL = 100;
	private Random rn = new Random();
	private int numberToGuess = rn.nextInt(INTERVAL);
	private int numberOfTries = 0;
	private Scanner input = new Scanner(System.in);
	private int guess;
	public boolean match = false;
	
	public int getNumberToGuess() {
		return numberToGuess;
	}
	
	public int getNumberOfTries() {
		return numberOfTries;
	}

	public GuessTheNumber() {
		while (!match) {
			System.out.println("Guess the number between 1 and " + INTERVAL + ": ");
			if(!input.hasNextInt() && input.next().equals("x")) {
				break;
			}
			guess = input.nextInt();
			if(guess == numberToGuess) {
				match = true;
			}
			else if(guess < numberToGuess) {
				System.out.println("Your guess is too low.");
			}
			else if(guess > numberToGuess) {
				System.out.println("Your guess is too high.");
			}
			numberOfTries++;
		}
		if(match) {
			System.out.println("You win!");
			System.out.println("The number was : " + getNumberToGuess());
			System.out.println("It took you " + getNumberOfTries() + " tries.");
			int score = 5 * INTERVAL - getNumberOfTries();
			new ScoreJDBC().add(new Score(score, System.getProperty("user.name"), "gtn"));
			new GameFinishedService().addRatingAndComments("gtn");
		} else {
			System.out.println("Game conceded, exit.");
		}
	}
}
