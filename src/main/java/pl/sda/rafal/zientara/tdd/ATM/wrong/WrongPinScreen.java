package pl.sda.rafal.zientara.tdd.ATM.wrong;

import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;
import pl.sda.rafal.zientara.tdd.ATM.PeopleWhoCanGetCash;
import pl.sda.rafal.zientara.tdd.ATM.Person;

import javax.swing.*;
import java.awt.*;

public class WrongPinScreen extends BaseSwingScreen implements WrongPinContract.View {
    private final JButton confirm;
    private int tries;
    private PeopleWhoCanGetCash people;

    private final WrongPinContract.Presenter presenter = new WrongPinPresenter(this);
    private final ScreenListener listener;//

    public WrongPinScreen(ScreenListener listener) {
        this.listener = listener;
        people = PeopleWhoCanGetCash.getInstance();
        frame = new JFrame("ERROR!");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));
        confirm = new JButton("Ok, Ok! Sry ;(");
        JLabel label = new JLabel();
        label.setText(setTextToLabel(presenter.getPossibleNumbersOfErrorsLeft()));
        confirm.addActionListener(e -> {
            presenter.okClicked();
            tries = presenter.getPossibleNumbersOfErrorsLeft();
            label.setText(setTextToLabel(tries));
        });
        frame.add(label);
        frame.add(confirm);
    }

    private String setTextToLabel(int tries) {
        return "Wrong PIN!" + System.lineSeparator() +
                "Is is RLY your card?!" + System.lineSeparator() +
                "=_=" + System.lineSeparator() +
                "You have "+tries+" tries more";
    }

    @Override
    public void confirmMessage() {
        listener.onWrongPinConfirm();
    }


    @Override
    public void blockCard() {
        Person person = people.getPeopleList().get(listener.getId());
        person.setBlocked(person.getId());
        System.out.println("is card blocked? "+person.isCardBlocked());
        printBlockedOrNot();
    }

    private void printBlockedOrNot() {
        for (Person p: people.getPeopleList()) {
            System.out.println(p.getId()+" "+p.isCardBlocked());
        }
    }

    @Override
    public void blockError(){
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "I'm sorry it was your 3rd error\n Your card has been blocked");
        System.exit(0);
    }

    public interface ScreenListener {
        //info
        void onWrongPinConfirm();
        int getId();
    }

}
