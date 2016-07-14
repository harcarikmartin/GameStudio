package sk.tsystems.gamestudio.game.minesweeper.consoleui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sk.tsystems.gamestudio.game.minesweeper.Minesweeper;
import sk.tsystems.gamestudio.game.minesweeper.UserInterface;
import sk.tsystems.gamestudio.game.minesweeper.core.Clue;
import sk.tsystems.gamestudio.game.minesweeper.core.Field;
import sk.tsystems.gamestudio.game.minesweeper.core.GameState;
import sk.tsystems.gamestudio.game.minesweeper.core.Mine;
import sk.tsystems.gamestudio.game.minesweeper.core.Tile.State;
import sk.tsystems.gamestudio.menu.Game;
import sk.tsystems.gamestudio.menu.consoleui.MenuConsoleUI;

/**
 * Console user interface.
 */
public class ConsoleUI implements UserInterface {
	private static Pattern PATTERN = Pattern.compile("(X|x)|(([MO|mo])([A-I|a-i])([0-8]))");

	/** Playing field. */
	private Field field;

	/** Input reader. */
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#newGameStarted(minesweeper.core.
	 * Field)
	 */
	@Override
	public void newGameStarted(Field field) {
		this.field = field;

		do {
			update();
			if (field.getState().equals(GameState.SOLVED)) {
				System.out.println("You WON");
				System.exit(0);
			}
			if (field.getState().equals(GameState.FAILED)) {
				System.out.println("You LOST");
				System.exit(0);
			}
			processInput();
		} while (true);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see minesweeper.consoleui.UserInterface#update()
	 */
	@Override
	public void update() {
		char row = 'A';
		for (int z = -1; z < field.getColumnCount(); z++) {
			if (z == -1) {
				System.out.print(" ");
			} else {
				System.out.print(" " + z);
			}
		}
		System.out.println();
		for (int x = 0; x < field.getRowCount(); x++) {
			System.out.print(row);
			for (int y = 0; y < field.getColumnCount(); y++) {
				if (field.getTile(x, y) instanceof Mine && field.getTile(x, y).getState().equals(State.OPEN)) {
					System.out.print(" X");
				} else if (field.getTile(x, y) instanceof Clue && field.getTile(x, y).getState().equals(State.OPEN)) {
					System.out.print(" " + ((Clue) field.getTile(x, y)).getValue());
				} else if (field.getTile(x, y).getState().equals(State.MARKED)) {
					System.out.print(" M");
				} else if (field.getTile(x, y).getState().equals(State.CLOSED)) {
					System.out.print(" -");
				}
			}
			System.out.println();
			row++;
		}
		System.out.println("Time playing: " + Minesweeper.getInstance().getPlayingSeconds());
		System.out.println("Mines remaining: " + field.getRemainingMineCount());
	}

	/**
	 * Processes user input. Reads line from console and does the action on a
	 * playing field according to input string.
	 */

	private void processInput() {
		System.out.printf("Please enter your selection <X> EXIT, <MA1> MARK, <OB4> OPEN : ");
		String input1 = readLine();
		
		try {
			handleInput(input1);
		} catch (WrongFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Reads line of text from the reader.
	 * 
	 * @return line as a string
	 */
	private String readLine() {
		try {
			return input.readLine();
		} catch (IOException e) {
			return null;
		}
	}
	
	void handleInput(String input) throws WrongFormatException {
		Matcher matcher = PATTERN.matcher(input);
		
		if(matcher.matches()) {
			String exit = matcher.group(1);
			String commandTyp = matcher.group(3);
			String rowString = matcher.group(4);
			String columnString = matcher.group(5);

			if (exit == null) {
				exit = "";
			}
			if (exit.toLowerCase().equals("x")) {
				System.out.println();
				Game game = new MenuConsoleUI();
				game.run();

			} else if (commandTyp.toLowerCase().equals("o") || commandTyp.toLowerCase().equals("m")) {
				char rowChar = rowString.charAt(0);
				int row = 0;
				int column = Integer.parseInt(columnString);

				for (char i = 'a'; i < rowChar; i++) {
					row++;
				}
				if (commandTyp.toLowerCase().equals("o")) {
					field.openTile(row, column);
				} else if (commandTyp.toLowerCase().equals("m")) {
					field.markTile(row, column);
				}
			}
		} 
		else {
			throw new WrongFormatException("Wrong format of input, try again.");
		}
	}
}
