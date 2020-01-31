package pl.sda.rafal.zientara.Hangman;

import java.util.LinkedHashSet;
import java.util.Set;

public class Hangman {
    private String puzzle;
    private Set<Character> guessedLetter = new LinkedHashSet<>();
    private int hp;


    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
        this.hp = 7;
        guessedLetter.clear();
    }


    public String getOutput() {

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < puzzle.length(); i++) {
            char c = puzzle.charAt(i);
            if (Character.isWhitespace(c)) {
                output.append(" ");
            } else if (guessedLetter.contains(Character.toLowerCase(c))) {
                output.append(c);
            } else
                output.append(".");
        }
        return output.toString();
    }

    public void guessLetter(char c) {
        if (hp != 0) {
            char smallChar = Character.toLowerCase(c);
            guessedLetter.add(smallChar);
            boolean isCorrect = puzzle.toLowerCase().contains(Character.toString(smallChar));
            if (!isCorrect) {
                hp--;
            }
        }
    }

    public void guessLetter(String word) {

        if (hp != 0) {
            if (word.toLowerCase().equals(puzzle.toLowerCase())) {
                for (int i = 0; i < word.length(); i++) {
                    guessedLetter.add(Character.toLowerCase(word.charAt(i)));
                }
            } else hp--;
        }

    }


    public int getHp() {
        return hp;
    }

    public boolean isPuzzleSolved() {
        return getOutput().equals(puzzle);
    }

    private boolean isAlive() {
        return getHp() > 0;
    }

    public boolean isGameOver() {

        return isPuzzleSolved() || !isAlive();

    }
}
