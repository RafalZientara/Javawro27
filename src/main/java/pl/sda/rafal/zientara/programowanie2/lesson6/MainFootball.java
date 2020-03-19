package pl.sda.rafal.zientara.programowanie2.lesson6;

import pl.sda.rafal.zientara.tdd.tictactoe.FieldStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainFootball {
        public static void main(String[] args) {
            FootballContract.View view;
            FootballContract.Presenter presenter;
            FootballView footballView = new FootballView();
            FootballBoard board =
                    new FootballBoard(10,12);
            view = footballView;
            presenter = new FootballPresenter(view, board);
            footballView.setBoard(board);
            footballView.setPresenter(presenter);
            board.initBlockedArea();
            board.initSides();
            presenter.init();
            showMeBoard(footballView, presenter);

        }
        private static void showMeBoard (FootballView footballView, FootballContract.Presenter presenter) {
            JFrame frame = new JFrame("Football");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(300,400);
            frame.add(footballView);
            frame.setVisible(true);

            footballView.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyPressed(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    if(presenter.checkResult()==MatchResult.PLAYER_BOTTOM_WIN || presenter.checkResult()==MatchResult.PLAYER_TOP_WIN){
                        JFrame frame2 = new JFrame("Result");
                        frame2.setSize(300, 300);
                        JLabel textField = new JLabel();
                        textField.setBounds(10, 30, 300, 40);
                        JButton button = new JButton("Restart");
                        button.setBounds(100,200,80,40);
                        textField.setText("CONGRATULATIONS!!! "+presenter.checkResult().toString());
                        frame2.add(textField);
                        frame2.add(button);
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                System.out.println("Klik");
                                frame.setVisible(false);
                                frame2.setVisible(false);

                                FootballContract.View view2;
                                FootballContract.Presenter presenter2;
                                FootballView footballView2 = new FootballView();
                                FootballBoard board2 =
                                        new FootballBoard(10,12);
                                view2 = footballView2;
                                presenter2 = new FootballPresenter(view2, board2);
                                footballView2.setBoard(board2);
                                footballView2.setPresenter(presenter2);
                                board2.initBlockedArea();
                                board2.initSides();
                                presenter2.init();
                                showMeBoard(footballView2,presenter2);
                            }
                        });
                        frame2.setLayout(null);
                        frame2.setVisible(true);
                    }
                }});
        }

    }
