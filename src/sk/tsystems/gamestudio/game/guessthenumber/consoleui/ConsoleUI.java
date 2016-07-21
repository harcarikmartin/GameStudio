package sk.tsystems.gamestudio.game.guessthenumber.consoleui;

import sk.tsystems.gamestudio.entity.ScoreJ;
import sk.tsystems.gamestudio.game.guessthenumber.core.GuessTheNumber;
import sk.tsystems.gamestudio.service.GameFinishedService;
import sk.tsystems.gamestudio.service.ScoreListing;
import sk.tsystems.gamestudio.service.jpasimple.ScoreJpa;

public class ConsoleUI {
	ScoreListing sl = new ScoreListing("gtn");
	
	public void run() {
		System.out.println("Best scores: ");
		new ScoreListing("gtn").print();
		GuessTheNumber gtn = new GuessTheNumber();
		int score = 5 * gtn.getNumberToGuess() - gtn.getNumberOfTries();
		new ScoreJpa().add(new ScoreJ(score, System.getProperty("user.name"), "gtn"));
		new GameFinishedService().addRatingAndComments("gtn");
		System.out.println("Best scores: ");
		new ScoreListing("gtn").print();
	}
	
}
