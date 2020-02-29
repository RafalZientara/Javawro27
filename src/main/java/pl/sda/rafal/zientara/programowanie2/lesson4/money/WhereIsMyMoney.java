package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
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
    private MoneyContract.Presenter presenter = new MoneyPresenter(this);

    public WhereIsMyMoney() {
        frame = new JFrame("WTF");
        frame.setSize(450, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        shopInput = new JTextField();
        shopInput.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(shopInput);

        dateFrom = new JTextField();
        dateFrom.setBounds(PADDING, 150, 150, FIELD_HEIGHT);
        frame.add(dateFrom);

        dateTo = new JTextField();
        dateTo.setBounds(230, 150, 150, FIELD_HEIGHT);
        frame.add(dateTo);

        costFrom = new JTextField();
        costFrom.setBounds(PADDING, 250, 150, FIELD_HEIGHT);
        frame.add(costFrom);

        costTo = new JTextField();
        costTo.setBounds(230, 250, 150, FIELD_HEIGHT);
        frame.add(costTo);

        result = new JList<>();
        result.setBounds(PADDING, 350, FIELD_WIDTH,100);
        frame.add(result);

        frame.setVisible(true);
        presenter.initData();

    }

    @Override
    public void refreshList(List<Cost> data) {
        DefaultListModel<Cost> list = new DefaultListModel<>();
        for (Cost cost : data) {
            list.addElement(cost);
        }
        result.setModel(list);
    }
}
