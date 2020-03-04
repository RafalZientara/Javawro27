package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals;

public class Dog extends MammalAnimal {

    public Dog() {
        this("futerko", "Burek");
    }

    public Dog(String name) {
        super("futerko", name);
    }

    public Dog(String skin, String name) {
        super(skin, name);
    }

    @Override
    protected String getSound() {
        return "Hau hau!";
    }
}
