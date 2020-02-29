package pl.sda.rafal.zientara.swing.hangman;

import java.util.Random;
import java.util.Scanner;

public class MainHangman {

    public static void main(String[] args) {
        Hangman game = new Hangman();
        Scanner reader = new Scanner(System.in);
        String randomPuzzle = Resources.getRandomPuzzle();
        game.setPuzzle(randomPuzzle);

        while(!game.isGameFinished()) {
            System.out.println(String.format("HP: %d/%d", game.getHp(), 7));
            System.out.println("Podaj literę a-z (bez polskich znakow)");
            String input = reader.nextLine();
            char c = input.charAt('0'); // odgadnięcie
            game.guessLetter(c);
        }if (game.isPuzzleSolved()){
            System.out.println("Brawo ziomek");
        } else {
            System.out.println("Nope, inne hasło :D" + randomPuzzle);
        }
    }
}
