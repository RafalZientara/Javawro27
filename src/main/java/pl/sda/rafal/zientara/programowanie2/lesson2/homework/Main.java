package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static int listMenu() {
        System.out.println("*****************");
        System.out.println("1. Dodaj pracownika."
                + "\n" + "2. Wyświetl listę pracowników."
                + "\n" + "3. Zapisz."
                + "\n" + "4. Zakończ.");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static void main(String[] args) {

        Scanner inputName = new Scanner(System.in);
        System.out.println("Podaj nazwę użytkownika: ");
        User user = new User();
        user.setName(inputName.nextLine());
        System.out.println(user.getName() + " ilu mamy pracowników?");
        user.howManyEmployeesWeHave();
        Scanner scanner = new Scanner(System.in);
        boolean bError = true;
        try {
            int choice = listMenu();
            while (choice != 4) {
                switch (choice) {
                    case 1:
                        Scanner inputChoice = new Scanner(System.in);
                        System.out.println("Podaj imię: ");
                        String name = inputChoice.nextLine();
                        System.out.println("Podaj nazwisko: ");
                        String surname = inputChoice.nextLine();
                        System.out.println("Podaj wiek: ");
                        do {
                            try {
                                int age = inputChoice.nextInt();
                                user.addEmployee(new Employee(name, surname, age));
                                bError = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Błąd! Wprowadź liczbę: ");
                                inputChoice.reset();
                                inputChoice.next();
                            }
                        } while (bError);
                        break;
                    case 2:
                        user.howManyEmployeesWeHave();
                        user.getDataOfAllEmployees();
                        break;
                    case 3:
                        System.out.println("W jaki sposób chcesz posortować:\n0-imię\n1-nazwisko\n2-wiek");
                        int chooseSort = scanner.nextInt();
                        if (chooseSort >= 0 && chooseSort < 3) {
                            user.sortEmployees(chooseSort);
                            user.getDataOfAllEmployees();
                            user.writeToCSV("baza.csv");
                        } else {
                            System.out.println("Zły wybór, jaki typ sortowania wybierasz: ");
                            scanner.nextInt();
                        }
                        break;
                    default:
                        System.out.println("Niepoprawny wybór, spórbuj jeszcze raz.");
                        break;
                }
                choice = listMenu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Błąd programu!");
        }
        System.out.println("********************\nKoniec programu.");
    }

}
