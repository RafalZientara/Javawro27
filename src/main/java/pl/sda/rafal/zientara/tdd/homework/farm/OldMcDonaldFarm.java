package pl.sda.rafal.zientara.tdd.homework.farm;

import pl.sda.rafal.zientara.tdd.homework.farm.animals.Animal;
import pl.sda.rafal.zientara.tdd.homework.farm.animals.BirdAnimal;
import pl.sda.rafal.zientara.tdd.homework.farm.animals.MammalAnimal;

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
        for (Animal temp : animals) {
            temp.printInfo();
        }
        System.out.println("Ilość zwierząt: "+animals.size());
    }

    public void printBirdsInfo() {
        System.out.println("Ptaki na farmie:");
        for (Animal temp: animals) {
            if (temp instanceof BirdAnimal)
                temp.printInfo();
        }
        //TODO 3. Wydrukuj informacje o zwierzakach do konsoli - ale tylko ptakach
        System.out.println();
    }

    public void printMammalsInfo() {
        System.out.println("Ssaki na farmie:");
        for (Animal temp: animals) {
            if (temp instanceof MammalAnimal)
                temp.printInfo();
        }
        //TODO 4. Wydrukuj informacje o zwierzakach do konsoli - ale tylko ssakach
    }

}
