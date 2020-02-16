package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm;

import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.Cow;
import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.CowFarm;

public class MainCowFarm {

    public static void main(String[] args) {
        CowFarm<Cow> cows = new CowFarm<>();
        cows.addAnimal(new Cow());
        cows.addAnimal(new Cow());
        cows.addAnimal(new Cow());
        cows.addAnimal(new Cow());
        cows.addAnimal(new Cow());
        cows.addAnimal(new Cow());

        cows.printAllInfo();
        System.out.println(cows.produceMilk());


    }
}
