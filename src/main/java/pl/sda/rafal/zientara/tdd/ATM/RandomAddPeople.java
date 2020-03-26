package pl.sda.rafal.zientara.tdd.ATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomAddPeople {

    public static ArrayList<Person> addRandomNumberOfPeople() {
        ArrayList<Person> peopleList = new ArrayList<>();
        Random random = new Random();
        int number = random.nextInt(30) + 1;
        for (int i = 0; i < number; i++) {
            peopleList.add(new Person(0, "1"));
        }
        setPersonId(peopleList);
        setPersonPin(peopleList);
        return peopleList;
    }

    private static void setPersonId(List<Person> peopleList) {
        int id = 1;
        for (Person p : peopleList) {
            p.setId(id);
            id++;
        }
    }

    private static void setPersonPin(List<Person> peopleList) {
        Random rand = new Random();
        for (Person p : peopleList) {
            int resRandom = rand.nextInt((9999 - 100) + 1) + 10;
            if (resRandom < 10) p.setPin("000" + resRandom);
            else if (resRandom < 100) p.setPin("00" + resRandom);
            else if (resRandom < 1000) p.setPin("0" + resRandom);
            else p.setPin("" + resRandom);
        }
    }
}
