package pl.sda.rafal.zientara.tdd.ATM;

import java.util.List;

public class PeopleWhoCanGetCash{
    private List<Person> peopleList;

    public PeopleWhoCanGetCash() {
        peopleList = RandomAddPeople.addRandomNumberOfPeople();
        //peopleList=JsonParsing.getGson();
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
