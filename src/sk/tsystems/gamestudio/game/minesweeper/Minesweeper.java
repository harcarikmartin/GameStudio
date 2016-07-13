package sk.tsystems.gamestudio.game.minesweeper;

import sk.tsystems.gamestudio.game.minesweeper.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;

/**
 * Main application class.
 */
public class Minesweeper {
	private long startMillis = System.currentTimeMillis();
	private BestTimes bestTimes = new BestTimes();
	private static Minesweeper instance;
	private Settings setting;
	
	/**
	 * Constructor.
	 */
	private Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();
		setSetting(Settings.BEGINNER);
		setting = getSetting();

		Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
		userInterface.newGameStarted(field);
	}
	
	/** User interface. */
	private UserInterface userInterface;
	
	/**
	 * Main method.
	 * 
	 * @param args
	 *            arguments
	 */
	
	public long getPlayingSeconds() {
		return (System.currentTimeMillis() - startMillis) / 1000;
	}
	
	public static void main(String[] args) {
		new Minesweeper();
	}

	public BestTimes getBestTimes() {
		return bestTimes;
	}

	public static Minesweeper getInstance() {
		return instance;
	}

	public Settings getSetting() {
		return setting;
	}

	public void setSetting(Settings setting) {
		this.setting = setting;
		this.setting.save();
	}
}