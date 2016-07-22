package sk.tsystems.gamestudio.game.guessthenumber.consoleui;

import java.util.Scanner;
import sk.tsystems.gamestudio.entity.ScoreJ;
import sk.tsystems.gamestudio.game.guessthenumber.core.GuessTheNumber;
import sk.tsystems.gamestudio.service.GameFinishedService;
import sk.tsystems.gamestudio.service.ScoreListing;
import sk.tsystems.gamestudio.service.jpasimple.ScoreJpaSimple;

public class ConsoleUI {
	ScoreListing sl = new ScoreListing("gtn");
	private GuessTheNumber gtn;
	
	private Scanner input = new Scanner(System.in);
	private int guess;
	public boolean match = false;
	private int numberOfTries = 0;
	
	public ConsoleUI() {
		 gtn = new GuessTheNumber(100);
	}
	
	public void run() {
		System.out.println("Best scores: ");
		new ScoreListing("gtn").print();
		
		while (!match) {
			System.out.println("Guess the number between 1 and " + gtn.getInterval() + ": ");
			if(!input.hasNextInt() && input.next().equals("x")) {
				break;
			}
			guess = input.nextInt();
			if(guess == gtn.getNumberToGuess()) {
				match = true;
			}
			else if(guess < gtn.getNumberToGuess()) {
				System.out.println("Your guess is too low.");
			}
			else if(guess > gtn.getNumberToGuess()) {
				System.out.println("Your guess is too high.");
			}
			numberOfTries++;
		}
		if(match) {
			System.out.println("You win!");
			System.out.println("The number was : " + gtn.getNumberToGuess());
			System.out.println("It took you " + numberOfTries + " tries.");
			
		} else {
			System.out.println("Game conceded, exit.");
		}
		int score = 5 * gtn.getInterval() - numberOfTries;
		new ScoreJpaSimple().add(new ScoreJ(score, System.getProperty("user.name"), "gtn"));
		new GameFinishedService().addRatingAndComments("gtn");
		System.out.println("Best scores: ");
		new ScoreListing("gtn").print();
	}
	
}
