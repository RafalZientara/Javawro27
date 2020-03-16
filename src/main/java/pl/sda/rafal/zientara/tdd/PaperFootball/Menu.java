package pl.sda.rafal.zientara.tdd.PaperFootball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu implements FootballContract.MainMenu {
    private FootballPresenter presenter;
    private JFrame menu;
    private JFrame FootballFrame;
    private FootballBoard board;
    private String currentPlayer = "green";
    private int playerGreenScore;
    private int playerBlueScore;
    private JLabel playerGreenLabel;
    private JLabel playerBlueLabel;
    private JTextField playerOneName;
    private JTextField playerTwoName;



    public Menu() {
        typeNameFrame();
    }

    private void typeNameFrame(){
        JFrame frame = new JFrame("Choose name");
        frame.setSize(400,250);
        playerOneName = new JTextField();
        playerOneName.setBounds(0,50,200,50);
        playerTwoName = new JTextField();
        playerTwoName.setBounds(0,150,200,50);
        JLabel firstPlayerLabel = new JLabel();
        firstPlayerLabel.setText("Player one !");
        firstPlayerLabel.setBounds(50,0,150,50);
        JLabel secondPlayerLabel = new JLabel();
        secondPlayerLabel.setText("Player two !");
        secondPlayerLabel.setBounds(50,100,150,50);
        JButton continueButton = new JButton("Continue");
        continueButton.setBounds(200,0,200,200);
        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                menuOfTheGame();
                startGame();
            }
        });
        frame.setLayout(null);
        frame.add(playerOneName);
        frame.add(playerTwoName);
        frame.add(firstPlayerLabel);
        frame.add(secondPlayerLabel);
        frame.add(continueButton);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.setResizable(false);
        frame.setVisible(true);
    }

    private ImageIcon addBallIcon(){
        ImageIcon smile = new ImageIcon("C:\\Users\\lukla\\IdeaProjects\\Javawro27-2\\ball.jpg");
        Image image = smile.getImage();
        Image newimg = image.getScaledInstance(100,100, Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }

    private void menuOfTheGame() {
        menu = new JFrame("Menu");
        menu.setSize(300,300);
        menu.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JButton button = new JButton();
        button.setIcon(addBallIcon());
        button.setBounds(100,50,100,100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFootballFrameVisible();
               menu.setVisible(false);
                JOptionPane.showMessageDialog(menu, "Player blue needs to score Top goal\n Player green needs to score Bottom goal");
            }
        });

        playerGreenLabel = new JLabel();
        playerGreenLabel.setText("Player "+playerOneName.getText()+"(green):  "+ playerGreenScore);
        playerGreenLabel.setBounds(25,150,275,50);
        playerBlueLabel = new JLabel();
        playerBlueLabel.setText("Player "+playerTwoName.getText()+"(blue):  "+playerBlueScore);
        playerBlueLabel.setBounds(25,200,275,50);
        menu.add(button);
        menu.add(playerGreenLabel);
        menu.add(playerBlueLabel);
        menu.setLayout(null);
        menu.setLocationRelativeTo(null);
        menu.setBackground(Color.pink);
        menu.setResizable(false);
        menu.setVisible(true);
    }


    @Override
    public void showMenu() {
        menu.setVisible(true);
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

    public void setFootballFrameVisible() {
        FootballFrame.setVisible(true);
    }

    public void hideWindow() {

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
    public void setPlayerGreenScore(int playerGreenScore) {
        this.playerGreenScore = playerGreenScore;
    }

    @Override
    public void setPlayerBlueScore(int playerBlueScore) {
        this.playerBlueScore = playerBlueScore;
    }

    @Override
    public void actualizeScore() {
        playerGreenLabel.setText("Player "+playerOneName.getText()+"(green):  "+ playerGreenScore);
        playerBlueLabel.setText("Player "+playerTwoName.getText()+"(blue):  "+playerBlueScore);
    }

}
