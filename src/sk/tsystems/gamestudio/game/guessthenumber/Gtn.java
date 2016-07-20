package sk.tsystems.gamestudio.game.guessthenumber;

import sk.tsystems.gamestudio.game.guessthenumber.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.menu.GameI;

public class Gtn implements GameI {
	private ConsoleUI console;
	
	public Gtn() {
		console = new ConsoleUI();
	}

	@Override
	public void run() {
		console.run();	
	}
	
	
}
