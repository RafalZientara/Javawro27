package pl.sda.rafal.zientara.homeWork.zad_7;

import pl.sda.rafal.zientara.homeWork.write_list_to_file.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        Person person = new Person();

        File file = new File("base.csv");
        file.createNewFile(); // tworzenie pliku w folderze, gdzie jest projekt

        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj liczbę pracowników: ");
        int howManyWorkers = scanner.nextInt();
        scanner.nextLine(); // dodajemy zawsze po zmiennej typu liczbowego jak int, czy line ponieważ jest za liczbą ukryte przejście
                            //do nowego wiersza
            while (true) {
                System.out.println("Podaj imię:");
                String firstName = scanner.nextLine();

                System.out.println("Podaj nazwisko: ");
                String lastName = scanner.nextLine();

                System.out.println("Podaj wiek: ");
                int age = scanner.nextInt();
                scanner.nextLine();

                List<Person> list = new ArrayList<>(howManyWorkers);
                list.add(new Person(firstName, lastName, age));

                System.out.println("Czy wyświtlić listę pracowników: ");
                String printList = scanner.nextLine();
                if (printList.equals("tak")) {
                    for (Person per : list)
                    System.out.println(per);
                }



            }

    }
}
