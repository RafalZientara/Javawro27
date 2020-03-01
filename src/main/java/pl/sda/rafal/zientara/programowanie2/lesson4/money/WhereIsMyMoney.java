package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;

public class WhereIsMyMoney implements MoneyContract.View {
    private static final int FIELD_WIDTH = 330;
    private static final int FIELD_HEIGHT = 60;
    private static final int PADDING = 50;
    private JFrame frame;
    private JTextField shopInput;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField costFrom;
    private JTextField costTo;
    private JList<Cost> result;
    private JLabel sumLabel;
    private MoneyContract.Presenter presenter = new MoneyPresenter(this);

    public WhereIsMyMoney() {
        frame = new JFrame("WTF");
        frame.setSize(450, 800);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel names = new JLabel("Filter by name");
        names.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
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

        JLabel fromToDate = new JLabel("Filter by date");
        fromToDate.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        dateFrom = new JTextField();
        dateFrom.setBounds(PADDING, 150, 150, FIELD_HEIGHT);
        dateFrom.addKeyListener(new DateListener(dateFrom) {
            @Override
            protected void onDateUpdate(LocalDate newDate) {
                presenter.onFromDateChange(newDate);
            }
        });
        frame.add(dateFrom);

        dateTo = new JTextField();
        dateTo.setBounds(230, 150, 150, FIELD_HEIGHT);
        dateTo.addKeyListener(new DateListener(dateTo) {
            @Override
            protected void onDateUpdate(LocalDate newDate) {
                presenter.onToDateChange(newDate);
            }
        });
        frame.add(dateTo);

        JLabel fromToPrice = new JLabel("Filter by price");
        fromToPrice.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        costFrom = new JTextField();
        costFrom.setBounds(PADDING, 250, 150, FIELD_HEIGHT);
        costFrom.addKeyListener(new PriceListener(costFrom) {
            @Override
            protected void onValueUpdate(Double value) {
                if (value != null) {
                    presenter.onPriceFromChange(value);
                } else {
                    presenter.onPriceFromChange(-1);
                }
            }
        });
        frame.add(costFrom);

        costTo = new JTextField();
        costTo.setBounds(230, 250, 150, FIELD_HEIGHT);
        costTo.addKeyListener(new PriceListener(costTo) {
            @Override
            protected void onValueUpdate(Double value) {
                if (value != null) {
                    presenter.onPriceToChange(value);
                } else {
                    presenter.onPriceToChange(-1);
                }
            }
        });
        frame.add(costTo);

        result = new JList<>();
        result.setBounds(PADDING, 350, FIELD_WIDTH, 250);
        frame.add(result);

        sumLabel = new JLabel();
        sumLabel.setBounds(PADDING, 650, FIELD_WIDTH, 50);
        sumLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        frame.add(sumLabel);

        frame.setVisible(true);
        presenter.initData();
        presenter.prepareData();

    }

    @Override
    public void refreshList(List<Cost> data) {
        DefaultListModel<Cost> list = new DefaultListModel<>();
        for (Cost cost : data) {
            list.addElement(cost);
        }
        result.setModel(list);
    }

    @Override
    public void refreshSum(double sum) {
        System.out.println(sum);
    }
}
