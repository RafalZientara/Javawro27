package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm;

import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.*;

public class MainFarm {

    public static void main(String[] args) {
        OldMcDonaldFarm<Animal> donaldFarm = new OldMcDonaldFarm<>();
        //Dodaj zwierzeta do zoo - po 1 z kazdego
        donaldFarm.addAnimal(new Cow());
        donaldFarm.addAnimal(new Dog());
        donaldFarm.addAnimal(new Chicken());
        donaldFarm.addAnimal(new Sparrow());
//        donaldFarm.addAnimal("Lol");

        donaldFarm.printAllInfo();
        donaldFarm.printBirdsInfo();
        donaldFarm.printMammalsInfo();
    }
}
