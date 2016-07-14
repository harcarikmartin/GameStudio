package sk.tsystems.gamestudio.menu.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sk.tsystems.gamestudio.game.guessthenumber.Gtn;
import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.stones.Stones;
import sk.tsystems.gamestudio.menu.Game;

public class MenuConsoleUI implements Game {
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));
	
	private enum Option {
		MINES, STONES, GUESS_THE_NUMBER, EXIT
	}

	public MenuConsoleUI() {
		super();
	};
	
	public void run() {
		System.out.println("Welcome to GameCenter, choose the Game you want to play.");
		while (true) {
			Game game = null;
			switch (showMenu()) {
			case MINES:
				game = new Minesweeper();
				break;
			case STONES:
				game = new Stones();
				break;
			case GUESS_THE_NUMBER:
				game = new Gtn();
				break;
			case EXIT:
				System.out.println("Thanks for visiting Game Studio.");
				System.out.println("Game Studio closed.");
				System.exit(1);
			}
			game.run();
		}
	}

	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	private Option showMenu() {
		System.out.println();
		System.out.printf("%s %-20s %-15s %-15s%n","No.", "Game", "Avg Rating", "No of Ratings");
		for (Option option : Option.values()) {
			System.out.printf("%2d. %-20s%n", option.ordinal() + 1, option);
		}
		System.out.println("------------------------------------------------------------------------------");

		int selection = -1;
		do {
			System.out.println("Option: ");
			try {
				selection = Integer.parseInt(readLine());
			} catch (Exception e) {
				System.err.println("Wrong format of input: " + e.getMessage());
			}
		} while (selection <= 0 || selection > Option.values().length);

		return Option.values()[selection - 1];
	}
}
