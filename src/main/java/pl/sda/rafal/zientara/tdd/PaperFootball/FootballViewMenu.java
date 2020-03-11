package pl.sda.rafal.zientara.tdd.PaperFootball;

import javax.swing.*;

public class FootballViewMenu extends JFrame {
    private JFrame frame;
    private FootballBoard board;

    public FootballViewMenu() {
        FootballView footballView = new FootballView();
        board = new FootballBoard(10, 12);
        FootballContract.Presenter presenter = new FootballPresenter(footballView, board);
        footballView.setBoard(board);
        footballView.setPresenter(presenter);
        board.initSides();
        presenter.init();
        showMeBoard(footballView);
    }

    public void closeFrame() {
        frame.dispose();
    }

    public void setTitleText(boolean bool) {
        if (bool) frame.setTitle("Player's Blue turn");
        else frame.setTitle("Player's Green turn");
    }

    private void showMeBoard(FootballView footballView) {
        frame = new JFrame("Football");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width - getSize().width) / 3, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 6);
        frame.setSize(600, 800);
        frame.add(footballView);
        //frame.setResizable(false);
        frame.setVisible(true);
    }

}
