package pl.sda.rafal.zientara.programowanie2.lesson1;

import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.shape.Line;
import pl.sda.rafal.zientara.tdd.hangman.Hangman;
import pl.sda.rafal.zientara.tdd.hangman.Resources;

public class HangmanController {

    @FXML
    public TextField input;
    @FXML
    public Button submitButton;
    @FXML
    public Label passwordLabel;
    @FXML
    public Line line1;
    @FXML
    public Line line2;
    @FXML
    public Line line3;
    @FXML
    public Line line4;
    @FXML
    public Line line5;
    @FXML
    public Line line6;
    @FXML
    public Group human;

    private Hangman game;

    public void initialize() {
        resetGame();
    }

    @FXML
    public void resetGame() {
        game = new Hangman();
        String randomPuzzle = Resources.getRandomPuzzle();
        game.setPuzzle(randomPuzzle);
        refreshUI();
    }

    @FXML
    public void handleConfirm() {
        if (game.isGameFinished()) {
            resetGame();
        } else {
            submitGuess();
        }
        refreshUI();
    }

    private void submitGuess() {
        String text = input.getText();
        if (text.length() > 1) {
            game.guessPuzzle(text);
        } else if (text.length() == 1) {
            char c = text.charAt(0);
            game.guessLetter(c);
        }
        input.clear();
    }

    private void refreshUI() {
        refreshPassword();
        refreshHp();
        refreshSubmitButton();
    }

    private void refreshSubmitButton() {
        if (game.isGameFinished()) {
            submitButton.setText("RESET");
        } else {
            submitButton.setText("OK");
        }
    }

    private void refreshHp() {
        line1.setVisible(false);
        line2.setVisible(false);
        line3.setVisible(false);
        line4.setVisible(false);
        line5.setVisible(false);
        line6.setVisible(false);
        human.setVisible(false);
        switch (game.getHp()) {
            case 0:
                human.setVisible(true);
            case 1:
                line6.setVisible(true);
            case 2:
                line5.setVisible(true);
            case 3:
                line4.setVisible(true);
            case 4:
                line3.setVisible(true);
            case 5:
                line2.setVisible(true);
            case 6:
                line1.setVisible(true);
        }
    }

    private void refreshPassword() {
        passwordLabel.setText(game.getOutput());
    }

}
