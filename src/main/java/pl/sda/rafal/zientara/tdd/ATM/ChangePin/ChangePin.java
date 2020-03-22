package pl.sda.rafal.zientara.tdd.ATM.ChangePin;

import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChangePin extends BaseSwingScreen implements ChangePinContract.View{

    private final ChangePin.ScreenListener listener;
    private final JLabel message;
    private String myNewPin;
    private final ChangePinContract.Presenter presenter = new ChangePinPresenter(this);
    private JButton confirm;
    private JTextField oldPin;
    private JTextField newPin;
    private JTextField againNewPin;

    public ChangePin(ChangePin.ScreenListener listener) {
        this.listener = listener;
        frame = new JFrame("Change Pin Menu");
        frame.setSize(400, 500);
        frame.setLayout(new GridLayout(0, 1));
        frame.add(new Label("Type old PIN number"));
        oldPin = new JTextField();
        newPin = new JTextField();
        againNewPin = new JTextField();
        confirm = new JButton("Accept");
        pinTyping(oldPin);
        frame.add(oldPin);
        frame.add(new Label("Type new PIN number"));
        pinTyping(newPin);
        frame.add(newPin);
        frame.add(new Label("Type new PIN number again"));
        pinTyping(againNewPin);
        twoNewPinsAreCorrect();
        frame.add(againNewPin);
        message = new JLabel();
        message.setText("Type old and new PIN number");
        frame.add(confirm);
        confirm.addActionListener(e-> {
            if (presenter.onPinConfirmed(getPin(), oldPin.getText())) {
                myNewPin = newPin.getText();
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your new PIN is: "+myNewPin);
                setPin(myNewPin);
                System.out.println(myNewPin);
                clearTextFields();
            }
            else {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your old PIN was different");
                clearTextFields();
            }
        });
        frame.add(message);
        frame.setVisible(true);
    }

    private void clearTextFields() {
        oldPin.setText("");
        newPin.setText("");
        againNewPin.setText("");
    }

    private void pinTyping(JTextField pin) {
        oldPin.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                presenter.onPinTyping(pin.getText());
            }
        });
    }

        private void twoNewPinsAreCorrect (){
            againNewPin.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {

                }

                @Override
                public void keyPressed(KeyEvent e) {

                }

                @Override
                public void keyReleased(KeyEvent e) {
                    presenter.areNewPINsCorrect(newPin.getText(),againNewPin.getText());
                }
            });
        }

    public void setPin(String pin) {
        listener.onChangePin(pin);
    }
    public String getPin() {
        return listener.getPin();
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
    public void hideError() {
        message.setText("");
    }

    @Override
    public void showTooShortPinError() {
        message.setText("Your PIN number is too short");
    }

    @Override
    public void showOnlyDigitsError() {
        message.setText("You can type only digits");
    }

    @Override
    public void showTooLongPinError() {
        message.setText("Your PIN number is too long");
    }


    @Override
    public void showNewPinNotMatchError() {
        message.setText("You need to type the same new PIN twice!!");
    }


    public interface ScreenListener {
        void onChangePin(String pin);
        String getPin();
    }
}
