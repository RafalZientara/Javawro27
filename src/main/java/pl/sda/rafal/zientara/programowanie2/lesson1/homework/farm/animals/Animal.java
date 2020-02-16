package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals;

public abstract class Animal {
    private String skin;
    private String name;

    public Animal(String skin, String name) {
        this.skin = skin;
        this.name = name;
    }

    public void printInfo() {
        System.out.println(getClass().getSimpleName() + " - " + name);
        System.out.println("Tak jem:");
        eat();
        System.out.println("Tak mowie:");
        makeSound();
        System.out.println();
    }

    public void eat() {
        System.out.println("Om nom nom");
    }

    public void makeSound() {
        System.out.println(getSound());
    }

    abstract String getSound();

    public String getName() {
        return name;
    }

    public String getSkin() {
        return skin;
    }

}
