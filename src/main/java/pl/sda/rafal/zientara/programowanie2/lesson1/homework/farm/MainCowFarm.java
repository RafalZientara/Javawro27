package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm;

import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.Cow;

public class MainCowFarm {

    public static void main(String[] args) {
        //todo 6. Stworz farme Donalda gdzie bedzie mozna wprowadzac tylko krowy i dodaj dowolna ich liczbe
        CowFarm<Cow> cowFarm = new CowFarm<>();
//        mammalFarm.addAnimal(new Dog());
        cowFarm.addAnimal(new Cow());
        cowFarm.addAnimal(new Cow());
        cowFarm.addAnimal(new Cow());
        cowFarm.addAnimal(new Cow());
        cowFarm.addAnimal(new Cow());
        //todo 7. Wydrukuj zawartosc farmy przez printAllInfo()
        cowFarm.printAllInfo();
        System.out.println("Ile mleka: " + cowFarm.produceMilk());
    }
}
