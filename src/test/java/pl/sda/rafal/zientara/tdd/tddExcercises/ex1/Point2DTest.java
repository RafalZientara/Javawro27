package pl.sda.rafal.zientara.tdd.tddExcercises.ex1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Point2DTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void distanceMeasurementFromPointZeroTest() {

        assertEquals((int) new Point2D(0, 50).getDistanceFromCenter(), 50);
        assertEquals((int) new Point2D(50, 0).getDistanceFromCenter(), 50);
        assertEquals((int) new Point2D(25, 25).getDistanceFromCenter(), 35);
        assertEquals((int) new Point2D(25, -25).getDistanceFromCenter(), 35);
        assertEquals((int) new Point2D(-25, -25).getDistanceFromCenter(), 35);
        assertEquals((int) new Point2D(0, 0).getDistanceFromCenter(), 0);
    }

    @Test
    void distanceMeasurementFromPointTest() {

        assertEquals((int) new Point2D(100, 50).getDistanceFromPoint2D(new Point2D(100, 100)), 50);
        assertEquals((int) new Point2D(51, 1).getDistanceFromPoint2D(new Point2D(1, 1)), 50);
        assertEquals((int) new Point2D(26, 26).getDistanceFromPoint2D(new Point2D(1, 1)), 35);
        assertEquals((int) new Point2D(26, -26).getDistanceFromPoint2D(new Point2D(1, 1)), 36);
        assertEquals((int) new Point2D(-26, -26).getDistanceFromPoint2D(new Point2D(1, 1)), 38);
        assertEquals((int) new Point2D(1, 1).getDistanceFromPoint2D(new Point2D(1, 1)), 0);
    }

    @Test
    void moveTest() {

        Point2D point = new Point2D(0, 0);

        point.move(50, 50);
        assertEquals(point.getX(), 50);
        assertEquals(point.getY(), 50);
        point.move(0, 0);
        assertEquals(point.getX(), 0);
        assertEquals(point.getY(), 0);
        point.move(356, 40);
        assertEquals(point.getX(), 356);
        assertEquals(point.getY(), 40);
        point.move(50, 50);
        assertEquals(point.getX(), 50);
        assertEquals(point.getY(), 50);
        point.move(0, 0);
        assertEquals(point.getX(), 0);
        assertEquals(point.getY(), 0);
    }

    @Test
    void mixedFunctionsTest() {
        Point2D point = new Point2D(0, 0);
        point.move(0, 50);
        assertEquals(point.getX(), 0);
        assertEquals(point.getY(), 50);
        assertEquals(point.getDistanceFromCenter(), 50);
        assertEquals((int) point.getDistanceFromPoint2D(new Point2D(1, 1)), 49);

    }


}