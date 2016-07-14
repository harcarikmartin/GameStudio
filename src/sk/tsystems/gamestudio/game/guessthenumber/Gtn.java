package sk.tsystems.gamestudio.game.guessthenumber;

import sk.tsystems.gamestudio.game.guessthenumber.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.menu.Game;

public class Gtn implements Game {
	private ConsoleUI console;
	
	public Gtn() {
		console = new ConsoleUI();
	}

	@Override
	public void run() {
		console.run();	
	}
	
	
}
