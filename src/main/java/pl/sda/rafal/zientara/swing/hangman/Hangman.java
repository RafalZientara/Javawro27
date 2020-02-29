package pl.sda.rafal.zientara.swing.hangman;

import java.util.LinkedHashSet;
import java.util.Set;

public class Hangman {
    private String puzzle;
    private Set<Character> guessLetter = new LinkedHashSet<>();
    private int hp;

    public void setPuzzle(String puzzle) {
        this.puzzle = puzzle;
        hp = 7;
        guessLetter.clear();
    }


    public String getOutput() {
        StringBuilder output = new StringBuilder();
        for (int i=0; i<puzzle.length(); i++) {
            char c = puzzle.charAt(i);
            if (Character.isWhitespace(c)) {
                output.append(" ");
            }else {
                if (guessLetter.contains(Character.toLowerCase(c)))
                    output.append(c);
                else {
                    output.append(".");
                }
            }
        }
        return output.toString().toString();
    }

    public void guessLetter(char c) {
        if (hp != 0) {
            char smallChar = Character.toLowerCase(c);
            guessLetter.add(smallChar);
            boolean isCorrect = puzzle.toLowerCase()
                    .contains(Character.toString(smallChar));

            if (!isCorrect) {
                hp--;
            }
        }
    }

    public int getHp() {
        return hp;
    }


    public boolean isPuzzleSolved() {
       return getOutput().equals(puzzle);
    }

    public boolean isAlive(){
        return getHp() > 0;
    }

    public boolean isGameFinished(){
        return isPuzzleSolved() || !isAlive();
    }
}
