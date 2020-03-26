package pl.sda.rafal.zientara.tdd.ATM.pin;

import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;
import pl.sda.rafal.zientara.tdd.ATM.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class PinScreen extends BaseSwingScreen implements PinContract.View {
    private final JPasswordField passwordField;
    private final JLabel message;
    private final JButton confirm;

    private final PinContract.Presenter presenter = new PinPresenter(this);
    private final ScreenListener listener;
    private Person person;
    private ArrayList<Person> users;

    public PinScreen(ScreenListener listener) {
        this.listener = listener;
        passwordField = new JPasswordField();
        message = new JLabel();
        confirm = new JButton("Confirm");
        init();

    }

    private void init() {
        frame = new JFrame("Insert PIN");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));
        Label label = new Label("Pin: (wcisnij dowolny klawisz to PIN sie pokaze =)");
        frame.add(label);  //WERSJA ROBOCZA !!!!!!!
        frame.add(passwordField);

        presenter.onPinTyping(String.valueOf(passwordField.getPassword()));
        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                label.setText("Podałem Ci PIN w wersji roboczej: "+person.getPin()+ " =)");
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                presenter.onPinTyping(String.valueOf(passwordField.getPassword()));
            }
        });
        frame.add(message);
        confirm.addActionListener(e -> {
            presenter.onPinConfirmed(String.valueOf(passwordField.getPassword()));
            setPersonToDash(person);
            passwordField.setText("");
        });
        confirm.setVisible(false);
        frame.add(confirm);
    }

    @Override
    public void disableConfirmButton() {
        confirm.setVisible(false);
    }

    @Override
    public void enableConfirmButton() {
        confirm.setVisible(true);
    }

    @Override
    public void showTooShortPinError() {
        message.setText("PIN is too short!");
    }

    @Override
    public void showTooLongPinError() {
        message.setText("PIN is too long");
    }

    @Override
    public void showOnlyDigitsError() {
        message.setText("You can type only digits");
    }

    @Override
    public void hideError() {
        message.setText("");
    }

    @Override
    public void correctPin() {
        listener.onCorrectPin();
    }

    @Override
    public void wrongPin() {
        listener.onWrongPin();
    }
    public void setPerson(Person person) {
        this.person = person;
    }
    public Person getPerson() {
        return person;
    }
    public void setPersonToDash(Person person) {
        listener.setPersonToDashBoardScreen(person);
    }

    public interface ScreenListener {
        //info
        void onCorrectPin();
        void onWrongPin();
        void setPersonToDashBoardScreen(Person person);
    }

}
