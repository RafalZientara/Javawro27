package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static int menu() {
        System.out.println("*****************");
        System.out.println("1. Dodaj pracownika."
                + "\n" + "2. Wyświetl listę pracowników."
                + "\n" + "3. Zapisz."
                + "\n" + "4. Zakończ.");
        Scanner inputMenu = new Scanner(System.in);
        return inputMenu.nextInt();
    }

    public static void main(String[] args) {

        Scanner inputName = new Scanner(System.in);
        System.out.println("Podaj nazwę użytkownika: ");
        User user = new User();
        user.setName(inputName.nextLine());
        if (user.getName().equals("Marcin")) {
            user.downloadFile(user);
        }
        //todo
        /*
         * download depends of user
         */
        user.numberOfEmployees();
        boolean isNotANumber = true;
        try {
            int choice = menu();
            while (choice != 4) {
                switch (choice) {
                    case 1:
                        Scanner inputData = new Scanner(System.in);
                        System.out.println("Podaj imię: ");
                        String name = inputData.nextLine();
                        System.out.println("Podaj nazwisko: ");
                        String surname = inputData.nextLine();
                        System.out.println("Podaj wiek: ");
                        do {
                            try {
                                int age = inputData.nextInt();
                                user.addEmployee(new Employee(name, surname, age));
                                isNotANumber = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Błąd! Wprowadź liczbę: ");
                                inputData.reset();
                                inputData.next();
                            }
                        } while (isNotANumber);
                        break;
                    case 2:
                        user.numberOfEmployees();
                        user.getAllEmployees();
                        break;
                    case 3:
                        System.out.println("W jaki sposób chcesz posortować:\n0-imię\n1-nazwisko\n2-wiek");
                        do {
                            Scanner inputSortWay = new Scanner(System.in);
                            int chooseSort = inputSortWay.nextInt();
                            try {
                                if (chooseSort >= 0 && chooseSort < 3) {
                                    user.sortEmployees(chooseSort);
                                    user.getAllEmployees();
                                    Scanner fileName = new Scanner(System.in);
                                    System.out.println("Podaj nazwę pliku");
                                    user.writeToCSV(fileName.nextLine() + ".csv");
                                    isNotANumber = false;
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Zły wybór, jaki typ sortowania wybierasz: ");
                                inputSortWay.reset();
                                inputSortWay.next();
                            }
                        }
                        while (isNotANumber);
                        break;
                    default:
                        System.out.println("Niepoprawny wybór, spórbuj jeszcze raz.");
                        break;
                }
                choice = menu();
            }
        } catch (InputMismatchException e) {
            System.out.println("Błąd programu!");
        }
        //todo
        /*
         * add loop for whole program to not end if MismatchException
         */
        System.out.println("********************\nKoniec programu.");
    }


}
