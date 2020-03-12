package pl.sda.rafal.zientara;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacGame {

    private boolean isX= true;
    private TicTacToe ticTacToe = new TicTacToe();
    private JLabel textField = new JLabel();
    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;
    private JButton b6;
    private JButton b7;
    private JButton b8;
    private JButton b9;
    private JButton[][] buttons;

    private JFrame frame;

    public TicTacGame() {

        buttons = new JButton[3][3];

        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(600,600);

        buttons[0][0] = b1;
        buttons[1][0] = b2;
        buttons[2][0] = b3;
        buttons[0][1] = b4;
        buttons[1][1] = b5;
        buttons[2][1] = b6;
        buttons[0][2] = b7;
        buttons[1][2] = b8;
        buttons[2][2] = b9;

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setBounds(j*200,i*200,200,200);
                frame.add(buttons[i][j]);
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
            }
        }

        JLabel label = new JLabel();
        label.setBounds(50,200,200,100);
        frame.add(label);

        frame.setLayout(null);
        frame.setVisible(true);

        addActions();
    }

    private void information(GameResult gameResult){
        if(gameResult == GameResult.PLAYER_X_WIN) frame.setTitle("Player X wins !!!");
        else if(gameResult == GameResult.PLAYER_O_WIN) frame.setTitle("Player O wins !!!");
        else if(gameResult == GameResult.DRAW) frame.setTitle("Recoil the Grail.");
        else frame.setTitle("Game turns on.");
    }

    private boolean checkAction(){
        return (ticTacToe.checkResult().equals(GameResult.PLAYER_O_WIN)
                || ticTacToe.checkResult().equals(GameResult.PLAYER_X_WIN)
                || ticTacToe.checkResult().equals(GameResult.DRAW));
    }

    private void action(JButton id , int col,int row){
        if(ticTacToe.getFieldStatus(col,row).equals(FieldStatus.EMPTY) && !checkAction()) {
            if (isX) {
                id.setText("X");
                isX = false;
                ticTacToe.add(col, row, FieldStatus.X);
                textField.setText(ticTacToe.checkResult().toString());
            } else {
                id.setText("O");
                isX = true;
                ticTacToe.add(col, row, FieldStatus.O);
                textField.setText(ticTacToe.checkResult().toString());

            }
        } else if(checkAction()) {
            ticTacToe.setAllEmpty();
            for (int i = 0; i < buttons.length; i++) {
                for (int j = 0; j < buttons[i].length; j++) {
                    buttons[i][j].setText("");
                }
            }

        }
    }

    public void addActions() {
        JButton tempButton;
        int col;
        int row;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                tempButton = buttons[i][j];
                col = i;
                row = j;
                int finalCol = col;
                JButton finalTempButton = tempButton;
                int finalRow = row;
                buttons[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                     action(finalTempButton, finalCol, finalRow);
                    }
                });
            }
        }
    }

}
