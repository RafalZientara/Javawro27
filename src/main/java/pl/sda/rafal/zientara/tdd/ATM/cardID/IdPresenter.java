package pl.sda.rafal.zientara.tdd.ATM.cardID;

import pl.sda.rafal.zientara.tdd.ATM.PeopleWhoCanGetCash;
import pl.sda.rafal.zientara.tdd.ATM.Person;
import pl.sda.rafal.zientara.tdd.ATM.wrong.IsNumber;

import javax.swing.*;
import java.util.ArrayList;

public class IdPresenter implements IdContract.Presenter {

    private final IdContract.View view;
    private ArrayList<Person> users;
    private Person person;
    private PeopleWhoCanGetCash people = PeopleWhoCanGetCash.getInstance();



    public IdPresenter(IdContract.View view) {
        this.view = view;
        PeopleWhoCanGetCash.getInstance();
        people.addElementsFromFileToList();
        users = people.getPeopleList();
        System.out.println(users);
    }

    @Override
    public void onCardIdTyping(String text) {
        view.disableConfirmButton();
        if (!IsNumber.isNumeric(text)) view.showOnlyDigitsError();
        else {
            view.hideError();
            view.enableConfirmButton();
        }
    }

    @Override
    public void onCardIdConfirmed(String id) {
        boolean find = false;
        for (Person user : users) {
            if (Integer.parseInt(id) == (user.getId())) {
                view.correctId();
                find = true;
                person = users.get(Integer.parseInt(id) - 1);
                break;
            }
        }
        if (!find) {
            view.wrongId();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "There is no user with ID: " + id);
        }
    }


    public ArrayList<Person> getUsers() {
        return users;
    }

    public Person getPerson() {
        return person;
    }
}
