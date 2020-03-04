package programowanie2.lesson6;

public class FootballContract {

    public interface View{
        void updatePosition(Point currentPosition);
        void updateCurrentPlayer(LineType type);
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
    }

}
