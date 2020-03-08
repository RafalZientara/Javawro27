package pl.sda.rafal.zientara.programowanie2.lesson2.homework;

import sun.reflect.generics.tree.Tree;

import javax.sound.midi.Soundbank;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Workers workers=new Workers();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ilu masz pracownikow w firmie?");
        int workersNumber = scanner.nextInt();
        workers.addWorkers(workersNumber);
   //     scanner.close();
        System.out.println(workers.sortWorkers());

//        workers.saveToFile();
        scanner.close();



    }
}
