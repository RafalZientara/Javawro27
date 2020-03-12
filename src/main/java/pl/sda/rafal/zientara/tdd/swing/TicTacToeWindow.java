package pl.sda.rafal.zientara.tdd.swing;

import pl.sda.rafal.zientara.tdd.tictactoe.FieldStatus;
import pl.sda.rafal.zientara.tdd.tictactoe.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeWindow {
    private TicTacToe game = new TicTacToe();

    public TicTacToeWindow() {
        JFrame frame = new JFrame("Siema");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(300, 350);

        GridLayout gridLayout = new GridLayout(3, 3);

        for (int i = 0; i < 9; i++) {
            int col = i % TicTacToe.BOARD_SIZE;
            int row = i / TicTacToe.BOARD_SIZE;
//            String text = Integer.toString(i);
            JButton button = new JButton();
            button.setBackground(Color.MAGENTA);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent event) {
                    try {
                        System.out.println("col " + col);
                        System.out.println("row " + row);
                        System.out.println();
                        game.action(col, row);
                        FieldStatus status = game.getFieldStatus(col, row);
                        button.setText(status.getText());
                        button.setBackground(Color.GRAY);
                        frame.setTitle(game.checkResult().toString());
                    } catch (Exception error) {
                        frame.setTitle(error.getMessage());
                    }
                }
            });
            frame.add(button);
        }

        frame.setLayout(gridLayout);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new TicTacToeWindow();
    }

}
