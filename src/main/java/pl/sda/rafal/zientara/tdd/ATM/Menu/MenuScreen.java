package pl.sda.rafal.zientara.tdd.ATM.Menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;
import pl.sda.rafal.zientara.tdd.ATM.Person;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

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
            saveToFile();
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
                    listener.onSetBalance(availableCash-5);
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Your PIN number is: "+PIN+" Thanks for 5 PLN");
                    listener.onSetPIN(PIN);
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

    private void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter("C:\\program\\person.json")) {
            ArrayList<Person> users = listener.getUserList();
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setBalance(int availableCash) {
        this.availableCash=availableCash;
    }

    public interface ScreenListener {
        void onCheckNameButton();
        void onCheckActualBalance();
        void onWithdrawalMoney();
        void setNewPinFrame();
        String getPINOption();
        void onSetPIN(String pin);
        ArrayList<Person> getUserList();
        void onSetBalance(int balance);
    }
}
