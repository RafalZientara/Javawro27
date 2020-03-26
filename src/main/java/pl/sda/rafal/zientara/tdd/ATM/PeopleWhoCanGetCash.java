package pl.sda.rafal.zientara.tdd.ATM;

import java.util.ArrayList;

public class PeopleWhoCanGetCash{
    private ArrayList<Person> peopleList;
    private static final PeopleWhoCanGetCash INSTANCE  = new PeopleWhoCanGetCash();

    public PeopleWhoCanGetCash() {
    }

    public ArrayList<Person> getPeopleList() {
        return peopleList;
    }

    @Override
    public String toString() {
        return "Random-added people{" +
                peopleList +
                '}';
    }

    public void addRandomElementsToList() {
        peopleList = RandomAddPeople.addRandomNumberOfPeople();
    }
    public void addElementsFromFileToList() {
        peopleList = JsonParsing.getGson();
    }

    public static PeopleWhoCanGetCash getInstance(){
        return INSTANCE;
    }

}
