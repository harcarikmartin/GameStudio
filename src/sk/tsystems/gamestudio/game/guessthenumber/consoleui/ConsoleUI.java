package sk.tsystems.gamestudio.game.guessthenumber.consoleui;

import sk.tsystems.gamestudio.game.guessthenumber.core.GuessTheNumber;
import sk.tsystems.gamestudio.service.ScoreListing;

public class ConsoleUI {
	ScoreListing sl = new ScoreListing("gtn");
	
	public void run() {
		System.out.println("Best scores: ");
		new ScoreListing("gtn").print();
		new GuessTheNumber();
		System.out.println("Best scores: ");
		new ScoreListing("gtn").print();
	}
	
}
