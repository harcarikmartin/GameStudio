package sk.tsystems.gamestudio.game.minesweeper;

import sk.tsystems.gamestudio.game.minesweeper.consoleui.ConsoleUI;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.menu.GameI;

/**
 * Main application class.
 */
public class Minesweeper implements GameI {
	private long startMillis = System.currentTimeMillis();
	private BestTimes bestTimes = new BestTimes();
	private static Minesweeper instance;
	private Settings setting;
	/** User interface. */
	private UserInterface userInterface;
	
	/**
	 * Constructor.
	 */
	public Minesweeper() {
		instance = this;
		userInterface = new ConsoleUI();
		setSetting(Settings.PRESENTATION);
		setting = getSetting();
	}
	
	public long getPlayingSeconds() {
		return (System.currentTimeMillis() - startMillis) / 1000;
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

	@Override
	public void run() {
		Field field = new Field(setting.getRowCount(), setting.getColumnCount(), setting.getMineCount());
		userInterface.newGameStarted(field);
		
	}
}