package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Workers{
    private List<Person> workers = new LinkedList<>();

    public void addWorkers (int numberOfWorkers){
        if(numberOfWorkers==0){
            System.out.println("Nie masz pracownikow");
        } else {
            Scanner scanner = new Scanner(System.in);
            for (int i = 0; i < numberOfWorkers; i++) {
                System.out.println("Podaj imie pracownika nr " + (i + 1) + ":");
                String name = scanner.next();
                System.out.println("Podaj nazwisko pracownika nr " + (i + 1) + ":");
                String lastName = scanner.next();
                System.out.println("Podaj wiek pracownika nr " + (i + 1) + ":");
                int age = scanner.nextInt();
                workers.add(new Person(name, lastName, age));
            }
  //          scanner.close();
            System.out.println(workers);
        }
    }


    public List<Person> sortWorkers(){
        List<Person> sortedList = null;
        Scanner scanner = new Scanner(System.in);
        boolean petla = true;
        while (petla) {
            System.out.println("Po jakich danych posortowaæ:\n" +
                    "0-imiê\n" +
                    "1-nazwisko\n" +
                    "2-wiek");
            int option = scanner.nextInt();
            if (option == 0){
                sortedList =  workers.stream()
                        .sorted(new Comparator<Person>() {
                            @Override
                            public int compare(Person o1, Person o2) {
                                return o1.getName().compareTo(o2.getName());
                            }
                        }).collect(Collectors.toList());
                return sortedList;
            } else if (option == 1){
                sortedList =  workers.stream()
                        .sorted(new Comparator<Person>() {
                            @Override
                            public int compare(Person o1, Person o2) {
                                return o1.getLastName().compareTo(o2.getLastName());
                            }
                        }).collect(Collectors.toList());
                return sortedList;
            } else if (option ==2){
                sortedList =  workers.stream()
                        .sorted(new Comparator<Person>() {
                            @Override
                            public int compare(Person o1, Person o2) {
                                if(o1.getAge()>o2.getAge()){
                                    return 1;
                                } else if (o1.getAge()==o2.getAge()){
                                    return 0;
                                } else {
                                    return -1;
                                }
                            }
                        }).collect(Collectors.toList());
                return sortedList;
            }
        }
  //      scanner.close();
        return sortedList;
    }

    @Override
    public String toString() {
        return "Workers{" +
                "workers=" + workers +
                '}';
    }

    public void saveToFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("Lista_pracownikow.txt");
        for (int i = 0; i < workers.size(); i++) {
            printWriter.println(workers.get(i).getName()+";"+workers.get(i).getLastName()+";"+workers.get(i).getAge());
        }
        printWriter.close();
        System.out.println("Dane zosta³y zapisane w pliku.");
    }

    public static void main(String[] args) {
        Workers workers = new Workers();
        workers.addWorkers(2);
        System.out.println(workers.sortWorkers().toString());
    }
}
