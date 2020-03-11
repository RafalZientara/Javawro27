package pl.sda.rafal.zientara.tdd.homework.ExercisesWithValues;

/*
Zad 2
 */
public class Median {
    public static float median(int[] input) {
        if (input.length == 0) {
            return -1;
        } else if (input.length % 2 == 0) {
            int one = input[input.length / 2 - 1];
            int two = input[input.length / 2];
            return Average.count(one, two);
        } else {
            return input[input.length / 2];
        }
    }
}
