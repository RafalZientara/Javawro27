package pl.sda.rafal.zientara.programowanie2.lesson1.homework;

public class MainSilnia {

    public static void main(String[] args) {
        int silnia1 = silnia(1);
        int silnia2 = silnia(2);
        int silnia3 = silnia(3);
        int silnia4 = silnia(4);
        int silnia5 = silnia(5);
        int silnia6 = silnia(6);

        if (silnia1 == 1 &&
                silnia2 == 2 &&
                silnia3 == 6 &&
                silnia4 == 24 &&
                silnia5 == 120 &&
                silnia6 == 720) {
            System.out.println("Gratulacje!");
            System.out.println("Funkcja silni dzia�a!");
        } else {
            System.out.println("Co� trzeba poprawi�:");
            System.out.println("silnia1 = " + silnia1);
            System.out.println("silnia2 = " + silnia2);
            System.out.println("silnia3 = " + silnia3);
            System.out.println("silnia4 = " + silnia4);
            System.out.println("silnia5 = " + silnia5);
            System.out.println("silnia6 = " + silnia6);
        }
    }

    private static int silnia(int liczba) {
        //TODO 1. Zaimplementuj funkcj� silnia
        return 0;
    }
}
