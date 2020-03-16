package pl.sda.rafal.zientara.tdd.PaperFootball;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements FootballContract.MainMenu {
    private FootballPresenter presenter;
    private JFrame frame;
    private JFrame FootballFrame;
    private FootballBoard board;
    private String currentPlayer = "green";
    private int playerOneScore;
    private int playerTwoScore;
    private JLabel firstPlayerScore;
    private JLabel secondPlayerScore;


    public Menu() {

        menuOfTheGame();
        startGame();
    }

    private void menuOfTheGame() {
        frame = new JFrame("Menu");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton button = new JButton("New Game");
        button.setBounds(50,50,100,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible();
               frame.setVisible(false);
                JOptionPane.showMessageDialog(frame, "Player blue needs to score Top goal\n Player green needs to score Bottom goal");
            }
        });
        firstPlayerScore = new JLabel("Player one score");
        firstPlayerScore.setText(String.valueOf(playerOneScore));
        firstPlayerScore.setBounds(100,200,100,50);
        secondPlayerScore = new JLabel("Player two score");
        secondPlayerScore.setText(String.valueOf(playerTwoScore));
        secondPlayerScore.setBounds(250,200,100,50);
        frame.add(button);
        frame.add(firstPlayerScore);
        frame.add(secondPlayerScore);
        frame.setVisible(true);
    }



    @Override
    public void showMenu() {
        frame.setVisible(true);
    }

    private void startGame() {
        FootballView footballView = new FootballView();
        board = new FootballBoard(6, 8);
        board.initSides();
        FootballContract.Presenter presenter = new FootballPresenter(this, footballView, board);
        footballView.setBoard(board);
        footballView.setPresenter(presenter);
        presenter.init();
        showMeBoard(footballView);
    }

    public void setVisible() {
        FootballFrame.setVisible(true);
    }

    public void hideWindow() {
        System.out.println(playerOneScore);
        System.out.println(playerTwoScore);
        FootballFrame.setVisible(false);
    }

    private void showMeBoard(FootballView footballView) {
        FootballFrame = new JFrame("Football");
        FootballFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        FootballFrame.setSize(600, 800);
        FootballFrame.add(footballView);
    }


    @Override
    public void switchPlayer() {
        this.currentPlayer = this.currentPlayer.equals("green") ? "blue" : "green";
        FootballFrame.setTitle("Player's "+currentPlayer+" turn");

    }

    @Override
    public void setPlayerOneScore(int playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    @Override
    public void setPlayerTwoScore(int playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    @Override
    public void actualizeScore() {
        firstPlayerScore.setText(String.valueOf(playerOneScore));
        secondPlayerScore.setText(String.valueOf(playerTwoScore));
    }

}
