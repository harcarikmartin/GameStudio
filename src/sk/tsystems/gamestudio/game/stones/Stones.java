package sk.tsystems.gamestudio.game.stones;

import sk.tsystems.gamestudio.game.stones.consoleui.ConsoleUi;
import sk.tsystems.gamestudio.menu.Game;

public class Stones implements Game {
	private ConsoleUi console;
	
	public Stones() {
		console = new ConsoleUi();
	}

	@Override
	public void run() {
		console.run();
	}	
}
