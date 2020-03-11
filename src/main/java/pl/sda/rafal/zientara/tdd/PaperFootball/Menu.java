package pl.sda.rafal.zientara.tdd.PaperFootball;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    public FootballPresenter presenter;

    public Menu() {
        menuOfTheGame();
    }

    private void menuOfTheGame() {
        JFrame frame = new JFrame("Menu");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton button = new JButton("New Game");
        button.setBounds(50,50,100,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               new FootballViewMenu();
                frame.dispose();
                JOptionPane.showMessageDialog(frame, "Player blue needs to score Top goal\n Player green needs to score Bottom goal");
            }
        });
        JLabel firstPlayerScore = new JLabel("Player one score");
        firstPlayerScore.setText(getPlayerOneScore());
        firstPlayerScore.setBounds(100,200,100,50);
        JLabel secondPlayerScore = new JLabel("Player two score");
        secondPlayerScore.setText(getPlayerTwoScore());
        secondPlayerScore.setBounds(250,200,100,50);
        frame.add(button);
        frame.add(firstPlayerScore);
        frame.add(secondPlayerScore);
        frame.setVisible(true);
    }

    private String getPlayerTwoScore() {
        if (presenter!=null)
        return presenter.getPlayerTwoScore();
        return "0";
    }

    private String getPlayerOneScore() {
        if (presenter!=null)
        return presenter.getPlayerOneScore();
        return "0";
    }
}
