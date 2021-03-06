package sk.tsystems.gamestudio.service;

import java.util.List;
import java.util.Scanner;

import sk.tsystems.gamestudio.entity.CommentJ;
import sk.tsystems.gamestudio.entity.RatingJ;
import sk.tsystems.gamestudio.service.jdbc.CommentJDBC;
import sk.tsystems.gamestudio.service.jpasimple.CommentJpaSimple;
import sk.tsystems.gamestudio.service.jpasimple.RatingJpaSimple;

public class GameFinishedService {
	private Scanner scanner = new Scanner(System.in);
	
	public void addRatingAndComments(String gameName) {	
		boolean added = false;
		String line = null;
		
		System.out.println("You won the Game! Do you want to rate the game?");
		line = scanner.nextLine().toLowerCase().trim();
		switch (line) {
		case "y":
			do {
				System.out.println("Rate the game between 1 to 5: ");
				line = scanner.nextLine().toLowerCase().trim();
				switch (line) {
				case "1":
				case "2":
				case "3":
				case "4":
				case "5":
					new RatingJpaSimple().add(new RatingJ(Integer.parseInt(line), System.getProperty("user.name"), gameName));
					added = true;
					break;
				default:
					System.out.println("Bad input, try again.");
					break;
				}
			} while (!added);
		default:
			break;
		}
		System.out.println("Do you want to add any comment for the game?");
		line = scanner.nextLine().toLowerCase().trim();
		switch (line) {
		case "y":
			System.out.println("Add your comment: ");
			line = scanner.nextLine().trim();
			
			new CommentJpaSimple().add(new CommentJ(line, System.getProperty("user.name"), gameName));
			System.out.println("Comment added.");
			break;
		default:
			System.out.println("Do you want to see the comments of the game?");
			line = scanner.nextLine().toLowerCase().trim();
				if(line.equals("y")) {
					System.out.println("Listing comments..");
					List<CommentJ> list = new CommentJpaSimple().findCommentsForGame(gameName);
					if(list.size() != 0) {
						for(CommentJ comment : list) {
							System.out.println("-> " + comment.getComment());
						}
						System.out.println();
					} else {
						System.out.println("No comments found for the game.");
					}
				}
			System.out.println("Thanks for playing.");
			break;
		}
		
	}
}
