package programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;

public class WhereIsMyMoney implements MoneyContract.View {
    private static final int FIELD_WIDTH = 400;
    private static final int FIELD_HEIGHT = 50;
    private static final int PADDING = 50;
    private JFrame frame;
    private JTextField shopInput;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField costFrom;
    private JTextField costTo;
    private JTextField totalSum;
    private JList<Cost> results;
    private MoneyContract.Presenter presenter = new MoneyPresenter(this);

    public WhereIsMyMoney() {
        frame = new JFrame("WTF");
        frame.setSize(FIELD_WIDTH + 2 * PADDING, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel names = new JLabel("Filter by name");
        names.setBounds(PADDING, 0, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(names);
        shopInput = new JTextField();
        shopInput.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        shopInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                presenter.onWordChange(shopInput.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        frame.add(shopInput);

        JLabel fromToDate = new JLabel("Filter by date from to");
        fromToDate.setBounds(PADDING, 100, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(fromToDate);
        dateFrom = new JTextField();
        dateFrom.setBounds(PADDING, 150, 75, FIELD_HEIGHT);
        dateFrom.addKeyListener(new DateListener(dateFrom) {
            @Override
            public void onDateUpdate(LocalDate newDate) {
                presenter.onFromDateChange(newDate);
            }
        });
        frame.add(dateFrom);

        dateTo = new JTextField();
        dateTo.setBounds(175, 150, 75, FIELD_HEIGHT);
        dateTo.addKeyListener(new DateListener(dateTo) {
            @Override
            public void onDateUpdate(LocalDate dateTo) {
                presenter.onToDateChange(dateTo);
            }
        });
        frame.add(dateTo);


        JLabel fromToCost = new JLabel("Filter by price from to");
        fromToCost.setBounds(PADDING, 200, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(fromToCost);
        costFrom = new JTextField();
        costFrom.setBounds(PADDING, 250, 75, FIELD_HEIGHT);
        costFrom.addKeyListener(new PriceListener(costFrom) {
            @Override
            public void onPriceUpdate(Double costFromA) {
                if (costFromA != null) {
                    presenter.onPriceFromChange(costFromA);
                } else {
                    presenter.onPriceFromChange(-1);
                }
            }
        });
        frame.add(costFrom);

        costTo = new JTextField();
        costTo.setBounds(175, 250, 75, FIELD_HEIGHT);
        costTo.addKeyListener(new PriceListener(costTo) {
            @Override
            public void onPriceUpdate(Double costTo) {
                if (costTo != null) {
                    presenter.onPriceToChange(costTo);
                } else {
                    presenter.onPriceToChange(-1);
                }
            }
        });
        frame.add(costTo);

        results = new JList<>();
        results.setBounds(PADDING, 350, FIELD_WIDTH, 150);
        frame.add(results);

        JLabel totalLabel = new JLabel("Total price:");
        totalLabel.setBounds(PADDING, 500, FIELD_WIDTH, 25);
        totalSum = new JTextField();
        totalSum.setBounds(PADDING, 525, FIELD_WIDTH, 25);
        frame.add(totalSum);

        frame.add(totalLabel);

        frame.setVisible(true);
        presenter.prepareData();
        presenter.initData();
    }

    @Override
    public void refreshList(List<Cost> data) {
        double total = 0.0;

        DefaultListModel<Cost> list = new DefaultListModel<>();
        for (Cost cost : data) {
            list.addElement(cost);
            total+=cost.price;
        }
        results.setModel(list);
        totalSum.setText(String.valueOf(total));
    }
}
