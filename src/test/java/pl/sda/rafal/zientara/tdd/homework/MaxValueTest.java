package pl.sda.rafal.zientara.tdd.homework;

import org.junit.jupiter.api.Test;
import pl.sda.rafal.zientara.tdd.homework.ExercisesWithValues.MaxValue;

import static org.junit.jupiter.api.Assertions.*;

class MaxValueTest {

    @Test
    public void emptyArrayReturnsMinusOne() {
        // given
        int[] input = new int[0];

        // when
        int out = MaxValue.count(input);

        // then
        assertEquals(-1, out);
    }

    @Test
    public void maxOfPlusElements() {
        // given
        int[] input = new int[]{100, 3, 5, 2};

        // when
        int out = MaxValue.count(input);

        // then
        assertEquals(100, out);
    }

    @Test
    public void maxOfPlusElements2() {
        // given
        int[] input = new int[]{100, 3, 5, 200};

        // when
        int out = MaxValue.count(input);

        // then
        assertEquals(200, out);
    }

    @Test
    public void maxOfMinusElements() {
        // given
        int[] input = new int[]{-59, -30, -100, -3920};

        // when
        int out = MaxValue.count(input);

        // then
        assertEquals(-30, out);
    }
}