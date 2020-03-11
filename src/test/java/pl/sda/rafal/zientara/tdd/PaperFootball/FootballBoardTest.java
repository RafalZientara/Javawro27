package pl.sda.rafal.zientara.tdd.PaperFootball;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FootballBoardTest {

    private FootballBoard board;

    @BeforeEach
    public void setup() {
        board = new FootballBoard(6, 8);
    }

    @Test
    public void insertedLineCanBeChecked() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExists(line.startPoint, line.endPoint);
        assertTrue(exist);
    }

    @Test
    public void insertedLineCanBeCheckedNewInstance() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExists(new Point(1, 1), new Point(2, 2));
        assertTrue(exist);
    }

    @Test
    public void insertedLineCanBeCheckedNewInstanceReverted() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExists(new Point(2, 2), new Point(1, 1));
        assertTrue(exist);
    }

    @Test
    public void cannotAddSameLine() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        assertThrows(IllegalArgumentException.class, () -> board.addLine(new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE)));
    }

    @Test
    public void lineDoesNotExist() {
        Line line = new Line(new Point(1, 1), new Point(2, 2), LineType.SIDE);
        board.addLine(line);
        boolean exist = board.lineExists(new Point(1, 2), new Point(2, 2));
        assertFalse(exist);
    }

}