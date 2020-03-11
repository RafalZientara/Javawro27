package pl.sda.rafal.zientara.tdd.homework.ExercisesWithValues;

/*
Zad 4
 */
public class MaxValue {
    public static int count(int[] input) {
        if (input.length == 0) {
            return -1;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            int element = input[i];
            if (element > max) {
                max = element;
            }
        }
        return max;
    }
}
