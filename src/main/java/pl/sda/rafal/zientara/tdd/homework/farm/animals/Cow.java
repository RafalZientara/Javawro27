package pl.sda.rafal.zientara.tdd.homework.farm.animals;

public class Cow extends MammalAnimal {

    public Cow(String skin, String name) {
        super(skin, name);
    }

    public Cow(String name) {
        super("fur", name);
    }

    public Cow() {
        super("fur", "mucka");
    }

    @Override
    String getSound() {
        return "MUUU";
    }

    public int getMilk() {
        return 4;
    }
}
