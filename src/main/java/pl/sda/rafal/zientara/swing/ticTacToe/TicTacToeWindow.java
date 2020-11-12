package pl.sda.rafal.zientara.swing.ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeWindow {

    private TicTacToe game = new TicTacToe();

    public static void main(String[] args) {
            new TicTacToeWindow();

    }

    public TicTacToeWindow() {
        JFrame frame = new JFrame("Tic-tac-toe");
        frame.isResizable();
        frame.setSize(300, 350);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout gridLayout = new GridLayout(3 ,3); // służy do symetrycznego ustawienia przycisków
        frame.setLayout(gridLayout);

        for (int i=0; i<9; i++) {
            int col = i % TicTacToe.BOARD_SIZE;
            int row = i / TicTacToe.BOARD_SIZE;
            String text = Integer.toString(i + 1);
            JButton button = new JButton();
            button.addActionListener(new ActionListener() { // pokazywanie kliknięć
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("col " + col);
                    System.out.println("row " + row);
                    game.action(col, row);
                    FieldStatus status = game.getFieldStatus(col, row);
                    button.setText(status.getText());
                    button.setBackground(Color.GREEN);
                    frame.setTitle(game.checkResult().toString());
                }
            });

            frame.add(button);
        }
        frame.setVisible(true);

           }
}
