package com.example.demoguessnumber;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainController {
    @FXML
    private Label infoText;
    @FXML
    private TextField guessInput;
    @FXML
    private Button submitButton;
    @FXML
    private Label mainText;
    private boolean gameRunning = false;
    private GuessingGame game = new GuessingGame();

    @FXML
    protected void onSubmitButtonClick() {
        if (!gameRunning) {
            // This will start the game
            submitButton.setText("Submit");
            guessInput.setVisible(true);
            mainText.setText("6 Guesses Remaining");
            infoText.setText("Enter a guess between 1-100");
            game.resetGame();
            gameRunning = true;
        } else {
            makeMove();
        }
    }

    @FXML
    protected void onInputEnter(KeyEvent e) {
        // Make a move when the user presses enter
        // inside the text input box
        if (e.getCode() == KeyCode.ENTER) {
            makeMove();
        }
    }

    private void makeMove() {
        try {
            // Read the input field
            String input = guessInput.getText();
            int guess = Integer.parseInt(input);

            // Make sure the guess is valid
            if (guess < 1 || guess > 100) {
                infoText.setText(String.format("%d out of range! Enter a guess between 1-100", guess));
                guessInput.setText("");
                return;
            }

            // Clean the input and actually make the guess
            // by calling the game's makeMove method
            guessInput.setText("");
            GuessingGame.GameResponse response = game.makeMove(guess);
            int remainingGuesses = GuessingGame.MAX_GUESSES - game.getNumGuesses();

            // Check for the response message
            // This is a Java 14 "enhanced switch statement"
            switch (response) {
                case CORRECT -> {
                    mainText.setText(String.format("%d is correct!", guess));
                    infoText.setText(String.format("You guessed the correct word in %d guesses. Press \"New Game\" to play again.", game.getNumGuesses()));
                    submitButton.setText("New Game");
                    guessInput.setVisible(false);
                    gameRunning = false;
                }
                case LOWER -> {
                    mainText.setText(String.format("%d Guesses Remaining", remainingGuesses));
                    infoText.setText(String.format("%d is too high! Enter a guess between 1-100", guess));
                }
                case HIGHER -> {
                    mainText.setText(String.format("%d Guesses Remaining", remainingGuesses));
                    infoText.setText(String.format("%d is too low! Enter a guess between 1-100", guess));
                }
                case OUT_OF_MOVES -> {
                    mainText.setText(String.format("%d was the answer", game.getAnswer()));
                    infoText.setText("You ran out of moves :( Better luck next time. Press \"New Game\" to play again.");
                    submitButton.setText("New Game");
                    guessInput.setVisible(false);
                    gameRunning = false;
                }
            }
        } catch (Exception e) {
            String text = guessInput.getText();
            if (text.length() == 0) {
                text = "[Empty]";
            }
            infoText.setText(String.format("%s is not a number! Enter a guess between 1-100", text));
            guessInput.setText("");
        }
    }
}