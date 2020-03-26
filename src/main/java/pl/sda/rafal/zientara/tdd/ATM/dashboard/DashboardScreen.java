package pl.sda.rafal.zientara.tdd.ATM.dashboard;

import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;
import pl.sda.rafal.zientara.tdd.ATM.Person;
import pl.sda.rafal.zientara.tdd.ATM.model.Cash;
import pl.sda.rafal.zientara.tdd.ATM.model.RandomMachineStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;


public class DashboardScreen extends BaseSwingScreen implements DashboardContract.View {
    private final JTextField moneyAmount;
    private final JLabel message;
    private final JButton confirm;
    private Person person;
    private final DashboardContract.Presenter presenter = new DashboardPresenter(this, new RandomMachineStorage());
    private final ScreenListener listener;

    public DashboardScreen(ScreenListener listener) {
        person = getPerson();
        this.listener = listener;
        frame = new JFrame("Insert amount of cash");
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 1));
        frame.add(new Label("Cash:"));
        Label label = new Label(clearLabel());
        frame.add(label);
        moneyAmount = new JTextField();
        moneyAmount.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                presenter.getCashPossibility(moneyAmount.getText());
                label.setText("Your balance: "+person.getAvailableCash());
            }
        });
        frame.add(moneyAmount);
        message = new JLabel();
        frame.add(message);
        confirm = new JButton("Confirm");
        JButton back = new JButton("Back to menu");
        confirm.addActionListener(e -> {
presenter.onCashConfirmed(Integer.parseInt(moneyAmount.getText().trim()));
label.setText(clearLabel());
            moneyAmount.setText("");
        });
        frame.add(confirm);
        back.addActionListener(e-> listener.backFromDashToMenu());
        frame.add(back);
    }

    private String clearLabel() {
        return "Enter value to see you account balance";
    }

    @Override
    public void onWithdrawalConfirm(java.util.List<Cash> money) {
        listener.onCashWithdrawal(money);
    }

    @Override
    public void printZeroError() {
        message.setText("You have to type more than 0");
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
    public void onlyDigitsError() {
        message.setText("You can type only digits");
    }

    @Override
    public void hideErrors() {
        message.setText("");
    }

    @Override
    public void onlyBanknotesError() {
        message.setText("You can take only Banknotes");
    }

    @Override
    public void tooMuchMoneyError() {
        message.setText("Money you want is too much");
    }

    @Override
    public void noMoneyError() {
        message.setText("You have not enough money in your account!");
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public interface ScreenListener {
        void onCashWithdrawal(List<Cash> money);
        void backFromDashToMenu();
    }

}
