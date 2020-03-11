package pl.sda.rafal.zientara.tdd.PaperFootball;

import javax.swing.*;

public class FootballPresenter implements FootballContract.Presenter {
    private final FootballContract.View view;
    private final FootballBoard board;
    private Point currentPosition;
    private boolean playerTopTurn = true;
    private static int playerOneScore;
    private static int playerTwoScore;
    private FootballViewMenu menu;

    public FootballPresenter(FootballContract.View view, FootballBoard board) {
        this.view = view;
        this.board = board;
    }

    @Override
    public void init() {
        currentPosition = new Point(board.width / 2, board.height / 2);
        view.updatePosition(currentPosition);
    }

    @Override
    public void moveTop() {
        move(0, -1);
    }

    @Override
    public void moveTopRight() {
        move(1, -1);
    }

    @Override
    public void moveRight() {
        move(1, 0);
    }

    @Override
    public void moveBottomRight() {
        move(1, 1);
    }

    @Override
    public void moveBottom() {
        move(0, 1);
    }

    @Override
    public void moveBottomLeft() {
        move(-1, 1);
    }

    @Override
    public void moveLeft() {
        move(-1, 0);
    }

    @Override
    public void moveTopLeft() {
        move(-1, -1);
    }

    public boolean endGameStatement() {
        return winnerStatement() || checkLoseStatement();
    }

    public String setText() {
        if (playerTopTurn) return "Green";
        else return "Blue";
    }

    private void move(int x, int y) {
    //menu.setTitleText(playerTopTurn);
        Point newPosition = new Point(currentPosition.x + x, currentPosition.y + y);
        if (!board.lineExists(currentPosition, newPosition)) {
            LineType type = getTypeByTurn();
            if (!board.hasAnyConnection(newPosition)) {
                playerTopTurn = !playerTopTurn;
            }
            board.addLine(new Line(currentPosition, newPosition, type));
            currentPosition = newPosition;
            view.updatePosition(currentPosition);
            view.updateCurrentPlayer(getTypeByTurn());
            endOfAGame();
        }
    }

    public String getPlayerOneScore() {
        return String.valueOf(playerOneScore);
    }

    public String getPlayerTwoScore() {
        return String.valueOf(playerTwoScore);
    }

    public void endOfAGame() {
        if (endGameStatement()) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Player   " + printWinner() + "    won");
            menu.closeFrame();
            new Menu();
        }
    }

    private boolean winnerStatement() {
        return youScoredBottomGoal() || youScoredTopGoal();
    }

    private String printWinner() {
        if ((checkLoseStatement() && !playerTopTurn) || youScoredBottomGoal()){
            playerOneScore++;
            return "Green";
        }
        else if ((checkLoseStatement() && playerTopTurn) || youScoredTopGoal()){
            playerTwoScore++;
            return "Blue";
        }
        else return null;
    }

    private boolean checkLoseStatement() {
        if (currentPosition.equals(new Point(0, 1))) return true;
        if (currentPosition.equals(new Point(board.width, 1))) return true;
        if (currentPosition.equals(new Point(0, board.height - 1))) return true;
        return currentPosition.equals(new Point(board.width, board.height - 1));
    }

    private boolean youScoredBottomGoal() {
        for (int i = board.width / 2 - 1; i <= board.width / 2 + 1; i++) {
            Point bottomPoint = new Point(i, board.height);
            if (currentPosition.equals(bottomPoint)) return true;
        }
        return false;
    }

    private boolean youScoredTopGoal() {
        for (int i = board.width / 2 - 1; i <= board.width / 2 + 1; i++) {
            Point topPoint = new Point(i, 0);
            if (currentPosition.equals(topPoint)) return true;
        }
        return false;
    }


    private LineType getTypeByTurn() {
        return playerTopTurn ? LineType.PLAYER_TOP : LineType.PLAYER_BOTTOM;
    }

}
