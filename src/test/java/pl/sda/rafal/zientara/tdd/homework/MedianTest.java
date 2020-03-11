package pl.sda.rafal.zientara.tdd.homework;

import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.tdd.homework.ExercisesWithValues.Median;

import static org.junit.jupiter.api.Assertions.*;

class MedianTest {

    @Test
    public void emptyArrayReturnsMinusOne() {
        // given
        int[] input = new int[]{};

        // when
        float median = Median.median(input);

        // then
        assertEquals(-1, median);
    }

    @Test
    public void medianOfEvenCountOfElements() {
        // given
        int[] input = new int[]{1, 2, 3, 5};

        // when
        float median = Median.median(input);

        // then
        assertEquals(2.5f, median);
    }

    @Test
    public void medianOfEvenCountOfElements2() {
        // given
        int[] input = new int[]{1, 2, 3, 4, 5};

        // when
        float median = Median.median(input);

        // then
        assertEquals(3, median);
    }

}