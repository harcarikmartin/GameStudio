package sk.tsystems.gamestudio.menu.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import sk.tsystems.gamestudio.entity.jpa.Comment;
import sk.tsystems.gamestudio.entity.jpa.Game;
import sk.tsystems.gamestudio.entity.jpa.Player;
import sk.tsystems.gamestudio.entity.jpa.Rating;
import sk.tsystems.gamestudio.entity.jpa.Score;
import sk.tsystems.gamestudio.game.guessthenumber.Gtn;
import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.stones.Stones;
import sk.tsystems.gamestudio.menu.GameI;
import sk.tsystems.gamestudio.service.jpa.CommentJpa;
import sk.tsystems.gamestudio.service.jpa.GameJpa;
import sk.tsystems.gamestudio.service.jpa.PlayerJpa;
import sk.tsystems.gamestudio.service.jpa.RatingJpa;
import sk.tsystems.gamestudio.service.jpa.ScoreJpa;
import sk.tsystems.gamestudio.service.jpasimple.RatingJpaSimple;


public class MenuConsoleUI implements GameI {
	private BufferedReader input = new BufferedReader(new InputStreamReader(
			System.in));
	public enum Option {
		MINESWEEPER, STONES, GTN, EXIT
	}
	
	public void run() {
//		Player player = new Player(System.getProperty("user.name"));
//		if(new PlayerJpa().isPresent(player)) {
//			System.out.println("Player Je");
//		} else {
//			System.out.println("Player Neni");
//		}
//		
//		Game games = new Game("minesweeper");
//		if(new GameJpa().isPresent(games)) {
//			System.out.println("Game Je");
//		} else {
//			System.out.println("Game Neni");
//		}
		Rating rating = new Rating();
		rating.setRating(5);
		Comment comment = new Comment();
		comment.setComment("commentjpa");
		Score score = new Score();
		score.setScore(2000);
		Player player = new PlayerJpa().setPresentPlayer(System.getProperty("user.name"));
		Game gm = new GameJpa().setPresentGame("minesweeper");
		comment.setPlayer(player);
		comment.setGame(gm);
		rating.setPlayer(player);
		rating.setGame(gm);
		score.setPlayer(player);
		score.setGame(gm);
		new CommentJpa().addComment(comment);
		new RatingJpa().addRating(rating);
		new ScoreJpa().addScore(score);
		System.out.println(new ScoreJpa().findTenBestScoresForGame(gm));
		System.out.println(new RatingJpa().findRatingsCountForGame(gm));
		System.out.println(new RatingJpa().findAverageRatingForGame(gm));
		System.out.println(new CommentJpa().findCommentsForGame(gm));
		
		
		boolean run = true;
		System.out.println(System.getProperty("user.name") + ", welcome to GameCenter, choose the Game you want to play.");
		System.out.println("All games are set ultra easy for presentation purposes...");
		while (run == true) {
			GameI game = null;
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
						new RatingJpaSimple().findRatingsCountForGame(option.toString().toLowerCase()), 
						new RatingJpaSimple().findAverageRatingForGame(option.toString().toLowerCase()));
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
