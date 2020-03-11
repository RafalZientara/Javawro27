package pl.sda.rafal.zientara.tdd.homework;

import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.tdd.homework.ExercisesWithValues.Average;

import static org.junit.jupiter.api.Assertions.*;

class AverageTest {

    @Test
    public void averageOfEmptyArray() {
        // given
        int[] input = new int[]{};

        // when
        float median = Average.count(input);

        // then
        assertEquals(-1, median);
    }

    @Test
    public void averageOfOneElement() {
        // given
        int[] input = new int[]{2};

        // when
        float avg = Average.count(input);

        // then
        assertEquals(2, avg);
    }

    @Test
    public void averageOfTwoElement() {
        // given
        int[] input = new int[]{2, 3};

        // when
        float avg = Average.count(input);

        // then
        assertEquals(2.5, avg);
    }

    @Test
    public void averageOfFewElements() {
        // given
        int[] input = new int[]{2, 4, 6};

        // when
        float avg = Average.count(input);

        // then
        assertEquals(4, avg);
    }

}