package pl.sda.rafal.zientara.tdd.ATM.cardID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.sda.rafal.zientara.tdd.ATM.PeopleWhoCanGetCash;
import pl.sda.rafal.zientara.tdd.ATM.Person;
import pl.sda.rafal.zientara.tdd.ATM.wrong.IsNumber;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IdPresenter implements IdContract.Presenter {

    private final IdContract.View view;
    private List<Person> users;
    private Person person;


    public IdPresenter(IdContract.View view) {
        this.view = view;
        PeopleWhoCanGetCash people = new PeopleWhoCanGetCash();
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
                saveToFile();
                break;
            }
        }
        if (!find) {
            view.wrongId();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "There is no user with ID: " + id);
        }
    }

    private void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("C:\\program\\person_notChanged.json")) {
            for (Person p : users)
                if (p.getId() != person.getId())
                    gson.toJson(p, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Person getPerson() {
        return person;
    }
}
