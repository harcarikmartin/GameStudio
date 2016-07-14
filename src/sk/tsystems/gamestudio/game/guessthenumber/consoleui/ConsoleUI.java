package sk.tsystems.gamestudio.game.guessthenumber.consoleui;

import sk.tsystems.gamestudio.game.guessthenumber.core.GuessTheNumber;

public class ConsoleUI {
	private static GuessTheNumber gtn = new GuessTheNumber();
	
	public void run() {
		System.out.println("You win!");
		System.out.println("The number was : " + gtn);
		System.out.println("It took you " + gtn + " tries.");
	}
	
}
