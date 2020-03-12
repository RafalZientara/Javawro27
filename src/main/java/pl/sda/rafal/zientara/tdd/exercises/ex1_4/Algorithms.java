package pl.sda.rafal.zientara.tdd.exercises.ex1_4;

class Algorithms {

    static int sumOfAllEvenNumbersInArray(int[] array) {
        int temp = 0;
        for (int element : array) {
            if (element % 2 == 0) temp += element;
        }
        return temp;
    }

    static double mediana(double[] array) {
        int n = array.length;
        if (n == 0) return -1;
        if (n % 2 == 0) return (array[n / 2] + array[(n / 2) - 1]) / 2;
        else return array[n / 2];
    }


    public static double arithmeticArraySum(double[] doubles) {
        double sum = 0;
        for (double element : doubles) sum += element;
        return sum;
    }

    public static int biggestNumberFromPositivesArray(int[] ints) {
        int temp = -1;
        boolean isFirstPositiveAdded = false;
        for (int element : ints) {
            if (element > 0 && !isFirstPositiveAdded) {
                temp = element;
                isFirstPositiveAdded = true;
            }
            if (element > temp) temp = element;
        }
        return temp;
    }
}

