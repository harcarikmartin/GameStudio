package sk.tsystems.gamestudio.game.guessthenumber.consoleui;

import sk.tsystems.gamestudio.game.guessthenumber.core.GuessTheNumber;
import sk.tsystems.gamestudio.service.GameFinishedService;

public class ConsoleUI {
	private static GuessTheNumber gtn = new GuessTheNumber();
	
	public void run() {
		System.out.println("You win!");
		System.out.println("The number was : " + gtn.getNumberToGuess());
		System.out.println("It took you " + gtn.getNumberOfTries() + " tries.");
		new GameFinishedService().addRatingAndComments("gtn");
	}
	
}
