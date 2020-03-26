package pl.sda.rafal.zientara.tdd.ATM.cardID;


import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;
import pl.sda.rafal.zientara.tdd.ATM.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class IdScreen extends BaseSwingScreen implements IdContract.View {

    private final JTextField cardIDField;
    private final JLabel message;
    private final JButton confirm;

    private final IdContract.Presenter presenter = new IdPresenter(this);
    private final ScreenListener listener;
    private ArrayList<Person> users;

    public IdScreen(IdScreen.ScreenListener listener) {
        this.listener = listener;
        cardIDField = new JPasswordField();
        message = new JLabel();
        confirm = new JButton("Confirm");
        users = presenter.getUsers();
        init();
    }

    private void init() {
        frame = new JFrame("Insert Card ID");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));
        frame.add(new Label("Card ID:"));
        frame.add(cardIDField);
        cardIDField.addKeyListener((new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                presenter.onCardIdTyping(cardIDField.getText());
            }
        }));
        confirm.addActionListener(e -> {
            presenter.onCardIdConfirmed(cardIDField.getText());
            if (presenter.getPerson()!=null)
            setPerson(presenter.getPerson());
        });
        frame.add(message);
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
    public void showOnlyDigitsError() {
        message.setText("You can type only digits");
    }

    @Override
    public void hideError() {
        message.setText("");
    }

    @Override
    public void correctId() {
        listener.onCorrectId();
    }

    @Override
    public void wrongId() {
        cardIDField.setText("");
    }

    public void setPerson(Person person) {
        listener.setPersonToPinScreen(person);
    }

    public int getId() {
        return Integer.parseInt(cardIDField.getText());
    }


    public interface ScreenListener {
        void onCorrectId();
        void setPersonToPinScreen(Person person);

    }
}
