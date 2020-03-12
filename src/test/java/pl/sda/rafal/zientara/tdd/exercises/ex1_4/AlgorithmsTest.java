package pl.sda.rafal.zientara.tdd.exercises.ex1_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlgorithmsTest {

    @Test
    void sumOfAllEvenNumbersInArray1() {
        // given
        // when
        int sum = Algorithms.sumOfAllEvenNumbersInArray(new int[]{0, 2, 12, 13, 15, 17, 18, 23, 33, 12, 34, 56, 77, 89});
        // then
        assertEquals(sum, 134);
    }

    @Test
    void sumOfAllEvenNumbersInArray2() {
        // given
        // when
        int sum = Algorithms.sumOfAllEvenNumbersInArray(new int[]{-50, -20, 0, 20, 50});
        // then
        assertEquals(sum, 0);
    }

    @Test
    void sumOfAllEvenNumbersInArray3() {
        // given
        // when
        int sum = Algorithms.sumOfAllEvenNumbersInArray(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});
        // then
        assertEquals(sum, 0);
    }

    @Test
    void sumOfAllEvenNumbersInArray4() {
        // given
        // when
        int sum = Algorithms.sumOfAllEvenNumbersInArray(new int[]{-20, -15, -1, 13, 15, 23, 149});
        // then
        assertEquals(sum, -20);
    }

    @Test
    void medianaTest1() {
        // given
        // when
        double mediana = Algorithms.mediana(new double[]{1, 2, 3, 4, 5});
        // then
        assertEquals(mediana, 3);
    }

    @Test
    void medianaTest2() {
        // given
        // when
        double mediana = Algorithms.mediana(new double[]{1, 2, 3, 5});
        // then
        assertEquals(mediana, 2.5);
    }

    @Test
    void medianaTest3() {
        // given
        // when
        double mediana = Algorithms.mediana(new double[]{});
        // then
        assertEquals(mediana, -1);
    }

    @Test
    void medianaTest4() {
        // given
        // when
        double mediana = Algorithms.mediana(new double[]{-1, 0, 1, 2});
        // then
        assertEquals(mediana, 0.5);
    }
    @Test
    void arithmeticArraySum() {
        // given
        // when
        double arithmeticArraySum = Algorithms.arithmeticArraySum(new double[]{1, 2, 3, 4});
        // then
        assertEquals(arithmeticArraySum, 10);
    }

    @Test
    void biggestNumberFromPositivesArray1() {
        // given
        // when
        int biggestNumberFromPositivesArray = Algorithms.biggestNumberFromPositivesArray(new int[]{});
        // then
        assertEquals(biggestNumberFromPositivesArray, -1);
    }

    @Test
    void biggestNumberFromPositivesArray2() {
        // given
        // when
        int biggestNumberFromPositivesArray = Algorithms.biggestNumberFromPositivesArray(new int[]{100, 3, 5, 2});
        // then
        assertEquals(biggestNumberFromPositivesArray, 100);
    }

    @Test
    void biggestNumberFromPositivesArray3() {
        // given
        // when
        int biggestNumberFromPositivesArray = Algorithms.biggestNumberFromPositivesArray(new int[]{100, 3, 5, 200});
        // then
        assertEquals(biggestNumberFromPositivesArray, 200);
    }

    @Test
    void biggestNumberFromPositivesArray4() {
        // given
        // when
        int biggestNumberFromPositivesArray = Algorithms.biggestNumberFromPositivesArray(new int[]{-59, -30, -100, -3920});
        // then
        assertEquals(biggestNumberFromPositivesArray, -1);
    }





}