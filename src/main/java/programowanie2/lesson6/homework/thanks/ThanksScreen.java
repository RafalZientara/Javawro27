package programowanie2.lesson6.homework.thanks;

import programowanie2.lesson6.homework.BaseSwingScreen;
import programowanie2.lesson6.homework.model.Cash;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


public class ThanksScreen extends BaseSwingScreen implements ThanksContract.View {
    private final JLabel message;
    private final JLabel moneyInfo;
    private final JButton confirm;
    private final ScreenListener listener;
    private final ThanksContract.Presenter presenter;

    public ThanksScreen(ScreenListener listener, List<Cash> money) {
        presenter = new ThanksPresenter(this, money);
        this.listener = listener;
        //frame ma dostep protected
        frame = new JFrame("Money money money!");
        frame.setSize(400, 400);
        frame.setLayout(new GridLayout(0, 1));

        message = new JLabel("There is Your cash!" + System.lineSeparator() +
                " Don't spend it too fast! Bzzzt!");
        frame.add(message);

        moneyInfo = new JLabel();
        frame.add(moneyInfo);

        confirm = new JButton("Ok Thanks! :D");
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
        Map<Cash, Long> withdrawResult = sumCash(withdrawal);

        if (!withdrawResult.isEmpty()) {
            for (Cash key : withdrawResult.keySet()) {
                builder.append("<html>").append(key.getWorth()).append("x").append(withdrawResult.get(key)).append("<br>");
            }
            builder.append("</html>");
            moneyInfo.setText(builder.toString());

        } else {
            moneyInfo.setText(builder.append("BZZZzzt, ERR0r, No MON3Y!! Save yourself!").toString());
        }


    }

    private Map<Cash, Long> sumCash(List<Cash> withdrawal) {
        return withdrawal.stream().collect(Collectors.groupingBy(cash -> cash, Collectors.counting()));
    }

    public interface ScreenListener {
        void onWithdrawalConfirm();
    }

}
