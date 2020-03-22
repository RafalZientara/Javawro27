package pl.sda.rafal.zientara.tdd.ATM.Menu;

import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;

import javax.swing.*;
import java.awt.*;

public class MenuScreen extends BaseSwingScreen {

    private String PIN;
    private String name;
    private int availableCash;
    private final ScreenListener listener;

    public MenuScreen(ScreenListener listener) {
        this.listener = listener;
        frame = new JFrame("Menu");
        frame.add(new Label("Welcome to my LucasBank Super ATM !!"));
        frame.setSize(400, 500);
        frame.setLayout(new GridLayout(0, 1));
        JButton checkName = new JButton("name");
        JButton withdraw = new JButton("Withdraw money");
        JButton changePin = new JButton("Change pin");
        JButton checkBalance = new JButton("Check Balance");
        JButton checkPIN = new JButton("Check your PIN");
        JButton exit = new JButton("Exit");
        changePin.addActionListener(e -> {
            listener.setNewPinFrame();
        });
        checkName.addActionListener(e -> {
            listener.onCheckNameButton();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your name is\n" + name);
        });
        exit.addActionListener(e -> {
            System.exit(0);
        });
        withdraw.addActionListener(e -> {
            listener.onWithdrawalMoney();
        });
        checkBalance.addActionListener(e -> {
            listener.onCheckActualBalance();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your balance is\n" + availableCash + " PLN");
        });
        checkPIN.addActionListener(e-> {
            PIN = listener.getPINOption();
            String[] options = new String[] {"Ok, i have Forgotten PIN", "No, I don't want to play!:("};
            int response = JOptionPane.showOptionDialog(null, "You Need To Pay 5 PLN to check Your PIN number", "WARNING, AVAILABLE PAYMENT!!",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                    null, options, options[0]);
            if (response==0) {
                listener.onCheckActualBalance();
                if (availableCash>=5) {
                    setBalance(availableCash-5);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your PIN number is: "+PIN+" Thanks for 5 PLN");
                }
                else JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "You can't check your PIN number, because u have no money:(");
            }
        });
        frame.add(checkName);
        frame.add(checkBalance);
        frame.add(withdraw);
        frame.add(changePin);
        frame.add(checkPIN);
        frame.add(exit);
        frame.setVisible(true);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalance(int availableCash) {
        this.availableCash = availableCash;
    }

    public interface ScreenListener {
        void onCheckNameButton();
        void onCheckActualBalance();
        void onWithdrawalMoney();
        void setNewPinFrame();
        String getPINOption();
    }
}
