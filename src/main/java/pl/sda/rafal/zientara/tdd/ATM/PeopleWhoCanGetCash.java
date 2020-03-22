package pl.sda.rafal.zientara.tdd.ATM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PeopleWhoCanGetCash{
    private List<Person> peopleList = new ArrayList<>();

    public PeopleWhoCanGetCash() {
        addRandomNumberOfPeople();
    }


    private void addRandomNumberOfPeople() {
        Random random = new Random();
        int number = random.nextInt(10)+1;
        for (int i = 0; i < number; i++) {
            peopleList.add(new Person(0,"1"));
        }
        setPersonId();
        setPersonPin();
    }

    private void setPersonId() {
        int id=1;
        for (Person p : peopleList) {
            p.setId(id);
            id++;
        }
    }

    private void setPersonPin() {
        Random rand = new Random();
            for (Person p:peopleList){
                int resRandom = rand.nextInt((9999 - 100) + 1) + 10;
                if (resRandom<10) p.setPin("000"+resRandom);
                else if (resRandom<100) p.setPin("00"+resRandom);
                else if (resRandom<1000) p.setPin("0"+resRandom);
                else p.setPin(""+resRandom);
            }
    }

    public List<Person> getPeopleList() {
        return peopleList;
    }

    @Override
    public String toString() {
        return "Random-added people{" +
                peopleList +
                '}';
    }
}
