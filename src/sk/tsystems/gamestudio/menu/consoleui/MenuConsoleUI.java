package sk.tsystems.gamestudio.menu.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import sk.testJpa.jpa.JpaHelper;
import sk.tsystems.gamestudio.game.guessthenumber.Gtn;
import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.stones.Stones;
import sk.tsystems.gamestudio.menu.Game;
import sk.tsystems.gamestudio.service.jpa.RatingJpa;

public class MenuConsoleUI implements Game {
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));
	public enum Option {
		MINESWEEPER, STONES, GTN, EXIT
	}
	
	public void run() {
		boolean run = true;
		System.out.println(System.getProperty("user.name") + ", welcome to GameCenter, choose the Game you want to play.");
		System.out.println("All games are set ultra easy for presentation purposes...");
		while (run == true) {
			Game game = null;
			switch (showMenu()) {
			case MINESWEEPER:
				game = new Minesweeper();
				break;
			case STONES:
				game = new Stones();
				break;
			case GTN:
				game = new Gtn();
				break;
			case EXIT:
				System.out.println("Thanks for visiting Game Studio.");
				run = false;
				System.out.println("Game Studio closed.");
				JpaHelper.closeAll();
			}
			if(run == true) {
				game.run();
			}
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
		System.out.printf("%s %-20s %-15s %-15s%n","No.", "Game", "No of Ratings", "Avg Rating");
		for (Option option : Option.values()) {
			if(option == Option.EXIT) {
				System.out.printf("%2d. %-20s%n", option.ordinal() + 1, option);
			} else {
//				System.out.println("Game: " + option.toString().toLowerCase());
				System.out.printf("%2d. %-20s %-15d %-15f%n", option.ordinal() + 1, option, 
						new RatingJpa().findRatingsCountForGame(option.toString().toLowerCase()), 
						new RatingJpa().findAverageRatingForGame(option.toString().toLowerCase()));
//						new RatingJDBC().findRatingsCountForGame(option.toString().toLowerCase()), 
//						new RatingJDBC().findAverageRatingForGame(option.toString().toLowerCase()));
			}
		}
		System.out.println("---------------------------------------------------");

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
