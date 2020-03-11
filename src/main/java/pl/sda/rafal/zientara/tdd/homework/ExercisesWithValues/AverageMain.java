package pl.sda.rafal.zientara.tdd.homework.ExercisesWithValues;

public class AverageMain {

    public static void main(String[] args) {
        int[] input = new int[]{2, 3};
        float avg = Average.count(input);

        System.out.println(String.format("%.2f", avg));
    }
}
