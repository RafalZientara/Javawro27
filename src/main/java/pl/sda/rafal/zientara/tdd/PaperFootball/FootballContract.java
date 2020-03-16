package pl.sda.rafal.zientara.tdd.PaperFootball;

public class FootballContract {

    public interface View {
        void updatePosition(Point currentPosition);
        void updateCurrentPlayer(LineType type);
    }

    public interface MainMenu {
        void showMenu();
        void switchPlayer();
        void setFootballFrameVisible();
        void hideWindow();
        void setPlayerGreenScore(int playerOne);
        void setPlayerBlueScore(int playerTwo);
        void actualizeScore();
    }


    public interface Presenter{
        void init();
        void moveTop();
        void moveTopRight();
        void moveRight();
        void moveBottomRight();
        void moveBottom();
        void moveBottomLeft();
        void moveLeft();
        void moveTopLeft();
        boolean endGameStatement();

    }
}
