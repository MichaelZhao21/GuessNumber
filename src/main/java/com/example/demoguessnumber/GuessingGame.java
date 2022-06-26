package com.example.demoguessnumber;

import java.util.Random;

public class GuessingGame {
    public enum GameResponse { CORRECT, LOWER, HIGHER, OUT_OF_MOVES }

    public static final int MAX_GUESSES = 6;
    private int answer;
    private int numGuesses;

    public GuessingGame() {
        resetGame();
    }

    public int getNumGuesses() { return numGuesses; }

    public int getAnswer() { return answer; }

    /**
     * Resets the game by generating a new number
     * and resetting the number of guesses
     */
    public void resetGame() {
        // Generate a number between [1, 100] inclusive
        Random rand = new Random();
        answer = rand.nextInt(1, 101);
        numGuesses = 0;
    }

    /**
     * Run when user guesses a number
     * @param guess The number that the user guessed
     * @return The response to the guess (See enum GameResponse)
     */
    public GameResponse makeMove(int guess) {
        // Increment the number of guesses
        numGuesses++;

        // First check if the guess is correct
        // This would send CORRECT before OUT_OF_MOVES
        // on the last guess that the user makes
        if (guess == answer) {
            return GameResponse.CORRECT;
        }

        // Check to see if the user is out of guesses
        if (numGuesses == MAX_GUESSES) {
            return GameResponse.OUT_OF_MOVES;
        }

        // Compare the guess to the answer and return
        // whether the answer is higher/lower than the guess
        if (guess > answer) {
            return GameResponse.LOWER;
        } else {
            return GameResponse.HIGHER;
        }
    }
}
