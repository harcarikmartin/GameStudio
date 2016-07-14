package sk.tsystems.gamestudio.game.guessthenumber.core;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {
	Random rn = new Random();
	int numberToGuess = rn.nextInt(1000);
	int numberOfTries = 0;
	Scanner input = new Scanner(System.in);
	int guess;
	boolean match = false;
	
	public GuessTheNumber() {
		while (match == false) {
			System.out.println("Guess the number between 1 and 1000: ");
			guess = input.nextInt();
			numberOfTries++;
			if(guess == numberToGuess) {
				match = true;
			}
			else if(guess < numberToGuess) {
				System.out.println("Your guess is too low.");
			}
			else if(guess > numberToGuess) {
				System.out.println("Your guess is too high.");
			}
		}
	}
}
