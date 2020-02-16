package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals;

public class Dog extends MammalAnimal {

    public Dog() {
        super("futerko", "Azor");
    }

    @Override
    String getSound() {
        return "Hau hau";
    }
}
