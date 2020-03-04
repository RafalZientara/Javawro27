package programowanie2.lesson6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FootballBoardTest {
    private FootballBoard board;

    @BeforeEach
    public void setup(){
        board = new FootballBoard(6,8);
    }

    @Test
    public void insertedLineCanBeChecked() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExist(line.start, line.end);
        assertTrue(exist);
    }

    @Test
    public void insertedLineCanBeCheckedNewInstance() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExist((new Point(1, 1)), new Point(2, 2));
        assertTrue(exist);
    }

    @Test
    public void insertedLineCanBeCheckedNewInstanceReverted() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExist((new Point(2, 2)), new Point(1, 1));
        assertTrue(exist);
    }

    @Test
    public void canAddTheSameLine() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExist((new Point(2, 2)), new Point(1, 1));
        assertThrows(IllegalArgumentException.class,() -> board.addLine(line));
    }

    @Test
    public void lineDoesntExist() {
        Line line = new Line(new Point(1, 2), new Point(2, 2), LineType.SIDE);

        boolean exist = board.lineExist(line);
        assertFalse(exist);
    }

}