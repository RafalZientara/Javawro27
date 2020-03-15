package programowanie2.lesson6.homework.pin;

import programowanie2.lesson6.homework.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Arrays;
import java.util.Collections;

public class PinScreen extends BaseSwingScreen implements PinContract.View {
    private final JPasswordField passwordField;
    private final JLabel message;
    private final JButton confirm;

    //DONE - zamiast null przekaz this - co powinna implementowac ta klasa?
    private final PinContract.Presenter presenter = new PinPresenter(this);
    private final ScreenListener listener;//

    public PinScreen(ScreenListener listener) {
        this.listener = listener;
        //frame ma dostêp protected
        frame = new JFrame("Insert PIN");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new Label("Pin:"));
        passwordField = new JPasswordField();
        frame.add(passwordField);

        passwordField.addKeyListener(new KeyAdapter() {
            String password = "";

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\b') {
                    if (password.length() != 0) {
                        password = password.substring(0, password.length() - 1);
                        presenter.onPinTyping(password);
                    }
                } else if (password.length() < 10){
                    password += e.getKeyChar();
                    presenter.onPinTyping(password.trim());
                }
            }
        });

        message = new JLabel();
        frame.add(message);

        confirm = new JButton("Confirm");
        confirm.setEnabled(false);
        confirm.addActionListener(e -> {
            presenter.onPinConfirmed(String.copyValueOf(passwordField.getPassword()).trim());
        });
        frame.add(confirm);
    }

    @Override
    public void disableConfirmButton() {
        confirm.setEnabled(false);
    }

    @Override
    public void enableConfirmButton() {
        confirm.setEnabled(true);
    }

    @Override
    public void showTooShortPinError() {
        message.setText("Pin is too short! Pin should have at least 4 digits!");
    }

    @Override
    public void showTooLongPinError() {
        message.setText("Pin is too long!");
    }

    @Override
    public void showOnlyDigitsError() {
        message.setText("DIGITS ONLY BRO! C'MON!");
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

    public interface ScreenListener {
        //info
        void onCorrectPin();

        void onWrongPin();
    }

}
