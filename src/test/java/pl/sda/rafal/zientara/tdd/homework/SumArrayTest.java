package pl.sda.rafal.zientara.tdd.homework;

import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.tdd.homework.ExercisesWithValues.SumArray;

import static org.junit.jupiter.api.Assertions.*;

class SumArrayTest {

    @Test
    public void sumEmptyArrayShouldBeZero() {
        // given
        int[] input = new int[]{};

        // when
        int sumEven = SumArray.sumEven(input);

        // then
        assertEquals(0, sumEven);
    }

    @Test
    public void sumEvenElements() {
        // given
        int[] input = new int[]{1, 2, 3, 5, 1, 6, 2};

        // when
        int sumEven = SumArray.sumEven(input);

        // then
        assertEquals(10, sumEven);
    }

    @Test
    public void noEvenElements() {
        // given
        int[] input = new int[]{1, 3, 5, 9, 11};

        // when
        int sumEven = SumArray.sumEven(input);

        // then
        assertEquals(0, sumEven);
    }

}