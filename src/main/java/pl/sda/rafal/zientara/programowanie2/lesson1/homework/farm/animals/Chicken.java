package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals;

public class Chicken extends BirdAnimal {

    public Chicken(String skin, String name) {
        super(skin, name);
    }

    public Chicken(String name) {
        super("pióra", name);
    }

    public Chicken() {
        this("KFC");
    }

    @Override
    public boolean canFly() {
        return false;
    }

    @Override
    String getSound() {
        return "Ko ko ko";
    }
}
