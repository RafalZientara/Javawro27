package programowanie2.lesson6;

import programowanie2.lesson6.FootballContract;
import programowanie2.lesson6.FootballView;

import javax.swing.*;

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
        board.initSides();
        presenter.init();

        showMeBoard(footballView);
    }
    private static void showMeBoard(FootballView footballView) {
        JFrame frame = new JFrame("Football");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300,400);
        frame.add(footballView);
        frame.setVisible(true);
    }
}