package pl.sda.rafal.zientara.tdd.exercises.ex1_4;

public class Main {

    public static void main(String[] args) {

        System.out.println("suma parzystych: " + Algorithms.sumOfAllEvenNumbersInArray(new int[]{0, 2, 12, 13, 15, 17, 18, 23, 33, 12, 34, 56, 77, 89}));
        System.out.println("mediana: " + Algorithms.mediana(new double[]{0, 2, 12, 13, 15, 15, 19, 23, 33, 34, 56, 77}));
        System.out.format("Arithmetic sum: %.2f", Algorithms.arithmeticArraySum(new double[]{1, 2, 3, 4, 5}));
        //...

    }

}


