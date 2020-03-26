package pl.sda.rafal.zientara.tdd.ATM.thanks;

import pl.sda.rafal.zientara.tdd.ATM.BaseSwingScreen;
import pl.sda.rafal.zientara.tdd.ATM.model.Cash;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static java.util.Objects.isNull;


public class ThanksScreen extends BaseSwingScreen implements ThanksContract.View {
    private final JLabel message;
    private final JLabel moneyInfo;
    private final ScreenListener listener;
    private final ThanksContract.Presenter presenter;


    public ThanksScreen(ScreenListener listener, List<Cash> money) {
        presenter = new ThanksPresenter(this, money);
        this.listener = listener;

        frame = new JFrame("Money money money!");
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(0, 1));

        message = new JLabel("<html>There is Your cash! <br> Don't spend it too fast! Bzzzt!</html>");
        frame.add(message);

        moneyInfo = new JLabel();
        frame.add(moneyInfo);

        JButton confirm = new JButton("Ok Thanks! :D");
        confirm.addActionListener(e -> {
            presenter.okClicked();
        });
        frame.add(confirm);
        presenter.init();

    }

    @Override
    public void confirmMessage() {
        listener.onWithdrawalConfirm();
    }

    @Override
    public void showMeTheMoney(List<Cash> withdrawal) {
        StringBuilder builder = new StringBuilder();
        if (isNull(withdrawal)) {
            moneyInfo.setText("You haven't withdrawal any money");
            message.setText("");
        } else {
            int lastIndexInList = withdrawal.size() - 1;
            builder.append(withdrawal.get(0));
            int iterator = 1;
            for (int i = 1; i < withdrawal.size(); i++) {
                if (!withdrawal.get(i).equals(withdrawal.get(i - 1))) {
                    if (i != lastIndexInList) {
                        addIteratorAndNewLine(builder, iterator)
                                .append(withdrawal.get(i));
                        iterator = 1;
                    } else {
                        addIteratorAndNewLine(builder, iterator)
                                .append(withdrawal.get(i))
                                .append(" x")
                                .append(iterator);
                    }
                } else {
                    if (i != lastIndexInList) iterator++;
                    else {
                        iterator++;
                        addIteratorAndNewLine(builder, iterator);
                    }
                }
            }
            setTextToMoneyInfoLabel(builder);
        }
    }

    private void setTextToMoneyInfoLabel(StringBuilder builder) {
        moneyInfo.setText("<html>" + builder.toString() + "</html>");
    }

    private StringBuilder addIteratorAndNewLine(StringBuilder builder, int iterator) {
        return builder.append(" x")
                .append(iterator)
                .append("<br>");
    }



    public interface ScreenListener {
        void onWithdrawalConfirm();

    }

}
