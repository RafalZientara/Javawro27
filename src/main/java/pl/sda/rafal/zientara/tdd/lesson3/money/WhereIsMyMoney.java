package pl.sda.rafal.zientara.tdd.lesson3.money;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;

public class WhereIsMyMoney implements MoneyContract.View {
    private JTextField chooseShop;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField costFrom;
    private JTextField costTo;
    private JLabel sum;
    private JLabel sumLabel;
    private JList<Cost> result;
    private JButton execute;
    private static final int PADDING = 50;
    private static final int FIELD_HEIGHT = 50;
    private static final int FIELD_WIDTH = 400;
    private MoneyContract.Presenter presenter = new MoneyPresenter(this);


    public WhereIsMyMoney() {
        JFrame menuFrame = new JFrame();
        menuFrame.setSize(FIELD_WIDTH + 2 * PADDING, 700);
        menuFrame.setLayout(null);
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        addFieldsToFrame(menuFrame);

        menuFrame.setVisible(true);
        presenter.prepareData();
        presenter.initData(); //initData musi byc na koncu, bo na poczatku by wyrzucil Null !!
    }

    private void addFieldsToFrame(JFrame menuFrame) {
        chooseShop = new JTextField();
        chooseShop.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        chooseShop.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                presenter.onWordChange(chooseShop.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        dateFrom = new JTextField();
        dateFrom.setBounds(PADDING, 150, 75, FIELD_HEIGHT);
        dateFrom.addKeyListener(new DateListener(dateFrom) {
            @Override
            public void onValueUpdate(LocalDate newValue) {
                presenter.fromThisDate(newValue);
            }
        });
        dateTo = new JTextField();
        dateTo.setBounds(175, 150, 75, FIELD_HEIGHT);
        dateTo.addKeyListener(new DateListener(dateTo) {
            @Override
            public void onValueUpdate(LocalDate newValue) {
                presenter.onToThisDate(newValue);
            }
        });
        costFrom = new JTextField();
        costFrom.setBounds(PADDING, 250, 75, FIELD_HEIGHT);
        costFrom.addKeyListener(new CostListener(costFrom) {

            @Override
            public void onValueUpdate(Double newValue) {
                if (newValue != null) {
                    presenter.onPriceFromChange(newValue);
                } else {
                    presenter.onPriceFromChange(-1);
                }
            }
        });

        costTo = new JTextField();
        costTo.setBounds(175, 250, 75, FIELD_HEIGHT);
        costTo.addKeyListener(new CostListener(costTo) {
            @Override
            public void onValueUpdate(Double newValue) {
                if (newValue != null) presenter.onPriceToChange(newValue);
                else presenter.onPriceFromChange(-1);
            }
        });

        result = new JList<>();
        result.setBounds(PADDING, 350, FIELD_WIDTH, FIELD_HEIGHT * 3);

        sumLabel = new JLabel("Total price");
        sumLabel.setBounds(PADDING, 600, FIELD_WIDTH, FIELD_HEIGHT);
        menuFrame.add(sumLabel);

        sum = new JLabel();
        sum.setBounds(PADDING * 4, 600, FIELD_WIDTH, FIELD_HEIGHT);
        menuFrame.add(sum);

        execute = new JButton("Export Data");
        execute.setBounds(100, 550, 200, 50);
        execute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
presenter.saveData();
            }
        });
        menuFrame.add(execute);

        menuFrame.add(chooseShop);
        menuFrame.add(dateFrom);
        menuFrame.add(dateTo);
        menuFrame.add(costFrom);
        menuFrame.add(costTo);
        menuFrame.add(result);

        JLabel names = new JLabel("Filter by name");
        names.setBounds(PADDING, 0, FIELD_WIDTH, FIELD_HEIGHT);
        menuFrame.add(names);

        JLabel date = new JLabel("Filtare by date from to");
        date.setBounds(PADDING, 100, FIELD_WIDTH, FIELD_HEIGHT);
        menuFrame.add(date);

        JLabel fromToCost = new JLabel("Filter by price from to");
        fromToCost.setBounds(PADDING, 200, FIELD_WIDTH, FIELD_HEIGHT);
        menuFrame.add(fromToCost);
    }

    @Override
    public void refreshList(List<Cost> data) {
        DefaultListModel<Cost> list = new DefaultListModel<>();
        for (Cost cost : data) {
            list.addElement(cost);
        }
        sum.setText(presenter.getSumValue());
        result.setModel(list);
    }

    @Override
    public void refreshSum(List<Cost> data) {
        sum.setText(presenter.getSumValue());
    }
}
