package pl.sda.rafal.zientara.tdd.hangman;

import java.util.Scanner;

public class MainHangman {

    public static void main(String[] args) {
        Hangman game = new Hangman();
        Scanner reader = new Scanner(System.in);

        String randomPuzzle = Resources.getRandomPuzzle();
        game.setPuzzle(randomPuzzle);

        while (!game.isGameFinished()) {
            System.out.println(String.format("HP: %d/%d", game.getHp(), 7));
            System.out.println("Haslo: " + game.getOutput());
            System.out.println("Podaj litere a-z (bez polskich znakow) lub cala fraze");
            String input = reader.next();
            if (input.length() == 1) {
                char c = input.charAt(0);
                game.guessLetter(c);
            } else {
                game.guessPuzzle(input);
            }
        }

        if (game.isPuzzleSolved()) {
            System.out.println(game.getOutput());
            System.out.println("Brawo ziomek! xD");
        } else {
            System.out.println("Nope. Inne haslo! D: " + randomPuzzle);
        }

    }
}
