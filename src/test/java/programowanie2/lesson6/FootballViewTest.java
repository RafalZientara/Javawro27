package programowanie2.lesson6;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class FootballViewTest {
    private FootballContract.View view;
    private FootballContract.Presenter presenter;
    private FootballView footballView = new FootballView();
    private FootballBoard board = new FootballBoard(10,12);

    @BeforeEach
    public void setup(){
        view = footballView;
        presenter = new FootballPresenter(view,board);
        footballView.setBoard(board);
        footballView.setPresenter(presenter);
        board.initSides();
        presenter.init();
    }

    @Test
    public void yolo(){
        presenter.moveTop(); //t
        presenter.moveRight(); //b
        presenter.moveBottomLeft(); //t
        presenter.moveBottom(); //t
        System.out.println("run");
    }

    @AfterEach
    public void test(){
        view = Mockito.mock(FootballContract.View.class);
        presenter = new FootballPresenter(view, board);
        showMeBoard();
        waitForUser();
    }


    private void waitForUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press ENTER");
        scanner.next();
    }

    private void showMeBoard(){
        JFrame frame = new JFrame("Football");
        frame.setSize(300,400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        footballView.setBounds(50,5,200,300);
        frame.add(footballView);
        frame.setVisible(true);
    }

    @Test
    public void prepareSides(){
        board.initSides();
        List<Line> lines = board.getLines();
        assertFalse(lines.isEmpty());
    }

}