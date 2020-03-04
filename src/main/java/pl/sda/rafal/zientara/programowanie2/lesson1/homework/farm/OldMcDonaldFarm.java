package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm;

import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.Animal;
import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.BirdAnimal;
import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.MammalAnimal;

import java.util.ArrayList;
import java.util.List;

public class OldMcDonaldFarm<T extends Animal> {
    protected List<T> animals;

    public OldMcDonaldFarm() {
        this.animals = new ArrayList<>();
    }

    public void addAnimal(T newAnimal) {
        animals.add(newAnimal);
    }

    public T getAnimal(int i) {
        return animals.get(i);
    }

    public void printAllInfo() {
        System.out.println("Wszystkie zwierzeta");
        for (T temp : animals) {
            temp.printInfo();
        }
        System.out.println();
    }

    public void printBirdsInfo() {
        System.out.println("Ptaki na farmie:");
        //Wydrukuj informacje o zwierzakach do konsoli - ale tylko ptakach
        for (T temp : animals) {
            if (temp instanceof BirdAnimal)
                temp.printInfo();
        }
        System.out.println();
    }

    public void printMammalsInfo() {
        System.out.println("Ssaki na farmie:");
        //Wydrukuj informacje o zwierzakach do konsoli - ale tylko ssakach
        for (T temp : animals) {
            if (temp instanceof MammalAnimal)
                temp.printInfo();
        }
        System.out.println();
    }

}
