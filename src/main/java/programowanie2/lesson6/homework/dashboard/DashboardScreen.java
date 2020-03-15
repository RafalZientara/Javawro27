package programowanie2.lesson6.homework.dashboard;

import programowanie2.lesson6.homework.BaseSwingScreen;
import programowanie2.lesson6.homework.model.Cash;
import programowanie2.lesson6.homework.model.RandomMachineStorage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;


public class DashboardScreen extends BaseSwingScreen implements DashboardContract.View {
    private final JTextField moneyAmount;
    private final JLabel message;
    private final JButton cashConfirm;
    private final JButton balanceConfirm;
    private final DashboardContract.Presenter presenter = new DashboardPresenter(this, new RandomMachineStorage());
    private final ScreenListener listener;

    public DashboardScreen(ScreenListener listener) {
        this.listener = listener;
        //frame ma dostêp protected
        frame = new JFrame("Insert amount of cash");
        frame.setSize(600, 400);
        frame.setLayout(new GridLayout(0, 1));

        frame.add(new Label("Cash:"));
        moneyAmount = new JTextField();
        moneyAmount.addKeyListener(new KeyAdapter() {
            String amount = "";
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == '\b') {
                    if (amount.length() != 0) {
                        amount = amount.substring(0, amount.length() - 1);
                        presenter.getCash(amount);
                    }
                } else {
                    amount += e.getKeyChar();
                    presenter.getCash(amount);
                }
            }
        });
        frame.add(moneyAmount);

        message = new JLabel();
        frame.add(message);

        balanceConfirm = new JButton("Check Balance!");
        balanceConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                presenter.onCheckBalanceConfirmation();
            }
        });
        frame.add(balanceConfirm);

        cashConfirm = new JButton("Confirm");
        cashConfirm.setEnabled(false);
        cashConfirm.addActionListener(e -> {
            presenter.onCashConfirmation(Integer.parseInt(moneyAmount.getText().trim()));
        });
        frame.add(cashConfirm);
    }

    @Override
    public void onWithdrawalConfirm(List<Cash> money) {
        listener.onCashWithdrawal(money);
    }

    @Override
    public void onCheckBalanceConfirm(int value) {
        message.setText("You have: " + value + " PLN. You are rich! hueh");
    }

    @Override
    public void onDepositConfirm() {

    }

    @Override
    public void lackOfFoundsOnWithdrawalError() {
        message.setText("Sorry bro.. You don't have such amount of money on your acc...");
    }

    @Override
    public void tooMuchCashOnDepositError() {
        message.setText("Too much money you want to deposit! YOU ARE TOO RICH FOR THIS ATM...");
    }

    @Override
    public void onCashMultiplicityError() {
        message.setText("This ATM uses only Bank Notes (10, 50, 100, etc)! Arghh...");
    }

    @Override
    public void showOnlyDigitsError() {
        message.setText("DIGITS ONLY BRO! C'MON!");
    }

    @Override
    public void zeroCashError() {
        message.setText("You can withdraw at least 10 PLN, not 0.");
    }

    @Override
    public void disableConfirmButton() {
        cashConfirm.setEnabled(false);
    }

    @Override
    public void enableConfirmButton() {
        cashConfirm.setEnabled(true);
    }

    @Override
    public void hideError() {
        message.setText("");
    }

    public interface ScreenListener {
        void onCashWithdrawal(List<Cash> money);
    }

}
