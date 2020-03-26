package pl.sda.rafal.zientara.tdd.ATM;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class Person {
    private String name;
    private int id;
    private String pin;
    private int availableCash;
    private boolean cardBlocked;

    public Person(int id, String pin) {
        this.name = RandomStringUtils.randomAlphabetic(1).charAt(0) + RandomStringUtils.randomAlphabetic(5, 10).substring(1);
        this.id = id;
        this.pin = pin;
        this.availableCash = initRandomWallet();
        this.cardBlocked = false;
    }

    public Person(String name, int id, String pin, int availableCash) {
        this.name = name;
        this.id = id;
        this.pin = pin;
        this.availableCash = availableCash;
    }


    public int getId() {
        return id;
    }

    public String getPin() {
        return pin;
    }

    public int getAvailableCash() {
        return availableCash;
    }

    private int initRandomWallet() {
        Random random = new Random();
        int randomValue = random.nextInt(500) + 100;
        return randomValue - randomValue % 10;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                "{id='" + id + '\'' +
                ", pin='" + pin + '\'' +
                ", cash='" + availableCash + '\'' +
                '}';
    }

    public void reduceYourBalance(int val) {
        availableCash = availableCash - val;
    }

    public String getName() {
        return name;
    }

    public void decreaseUsersBalance(int valueToAdd) {
        availableCash -= valueToAdd;
    }

    public Person getPerson() {
        return this;
    }


    public void setBlocked(int id) {
        if (this.id==id) setCardBlocked(true);
    }

    public boolean isCardBlocked() {
        return cardBlocked;
    }

    public void setCardBlocked(boolean cardBlocked) {
        this.cardBlocked = cardBlocked;
    }
}


