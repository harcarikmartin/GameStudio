package sk.tsystems.gamestudio.game.stones.consoleui;

import java.util.Scanner;

import sk.tsystems.gamestudio.entity.ScoreJ;
import sk.tsystems.gamestudio.game.stones.core.Field;
import sk.tsystems.gamestudio.service.GameFinishedService;
import sk.tsystems.gamestudio.service.ScoreListing;
import sk.tsystems.gamestudio.service.jpasimple.ScoreJpa;

public class ConsoleUi {
	private Field field;
	private Scanner scanner;
	boolean close = false;

	public ConsoleUi() {
		field = Field.load();
		if (field == null) {
			newField();
		}
		scanner = new Scanner(System.in);
	}

	public void run() {
		System.out.println("Best scores: ");
		new ScoreListing("stones").print();
		do {
			if(!close) {
				show();
				processInput();
			} else {
				break;
			}
		} while (!field.isSolved());
		if(!close) {
			int score = field.getColumnCount() * field.getRowCount() * 1000 - field.getPlayingSeconds();
			new ScoreJpa().add(new ScoreJ(score, System.getProperty("user.name"), "stones"));
			new GameFinishedService().addRatingAndComments("stones");
			System.out.println("Best scores: ");
			new ScoreListing("stones").print();
		}
	}
	
	public void show() {
		System.out.printf("Time: %03d s\n", field.getPlayingSeconds());
		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {
				int value = field.getValueAt(row, column);
				if (value == Field.EMPTY_CELL) {
					System.out.printf("  ");
				} else {
					System.out.printf("%2d", value);
				}
				System.out.print("  ");
			}
			System.out.println();
		}
	}

	private void processInput() {
		System.out.print("Enter input: ");
		String line = scanner.nextLine().toLowerCase().trim();

		try{
			int value = Integer.parseInt(line);
			field.move(value);
			return;
		}catch(NumberFormatException e) {}
		
		switch (line) {
		case "w":
		case "up":
			field.moveUp();
			break;
		case "a":
		case "left":
			field.moveLeft();
			break;
		case "s":
		case "down":
			field.moveDown();
			break;
		case "d":
		case "right":
			field.moveRight();
			break;
		case "x":
		case "exit":
//			field.save();
			close = true;
			break;
		case "n":
		case "new":
			newField();
			break;
		default:
			System.out.println("What's wrong kidda?");
			break;
		}
	}

	private void newField() {
		field = new Field(4, 4);
	}
}
