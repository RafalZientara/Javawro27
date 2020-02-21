package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        User user = new User();
//        Employee employee1 = new Employee("Karolina", "Balicka", 39);
//        Employee employee2 = new Employee("Marcin", "Kowalski", 27);
//        Employee employee3 = new Employee("Tadeusz", "Balicka", 20);
//        Employee employee4 = new Employee("Antoni", "Malinowski", 49);
//        user.addEmployee(employee1);
//        user.addEmployee(employee2);
//        user.addEmployee(employee3);
//        user.addEmployee(employee4);
////        user.sortEmployeesBySurname();
//        Scanner scanner = new Scanner(System.in);;
//        int choice = scanner.nextInt();
//        if (choice >= 0 && choice < 3) {
//            user.sortWhichData(choice);
//        }
//        else {
//            System.out.println("Zły wybór, jaki typ sortowania wybierasz: ");
//            scanner.nextInt();
//        }
//        user.getDataOfAllEmployees();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę użytkownika: ");
        User user = new User();
        user.setName(scanner.nextLine());
        System.out.println(user.getName() + " ilu mamy pracowników?");
        user.howManyEmployeesWeHave();
        String menuList = "\n" + "1. Dodaj pracownika."
                + "\n" + "2. Wyświetl listę pracowników."
                + "\n" + "3. Zakończ.";
        System.out.println(user.getName() + menuList);
//        StringBuilder employees = new StringBuilder();
        while (scanner.nextInt() == 1) {
            Scanner scanner1 = new Scanner(System.in);
            System.out.println("Podaj imię: ");
            String name = scanner1.nextLine();
            System.out.println("Podaj nazwisko: ");
            String surname = scanner1.nextLine();
            System.out.println("Podaj wiek: ");
            int age = scanner1.nextInt();
            user.addEmployee(new Employee(name, surname, age));
            System.out.println(user.getName() + menuList);
        }
//        if (scanner.nextInt() == 2) {
//            user.howManyEmployeesWeHave();
//            user.getDataOfAllEmployees();
//        }
        user.sortEmployees(2);
        user.getDataOfAllEmployees();
        user.writeToCSV("baza.txt", user.getEmployeeList());
//        employees
//                .append(user.getEmployeeList())
//                .append("\n");
//        System.out.println(employees.toString());


    }

}
