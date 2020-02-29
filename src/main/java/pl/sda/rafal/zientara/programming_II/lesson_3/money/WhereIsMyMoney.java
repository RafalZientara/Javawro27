package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import javax.swing.*;
import java.util.List;
//w swingu pisze siê za pomoca wzorca MVP, w JavaFX w MVC

public class WhereIsMyMoney implements MoneyContract.View{

    private static final int FIELD_WIDTH = 200;
    private static final int FIELD_HEIGHT = 50;
    private static final int PADDING = 50;
    private JFrame frame;
    private JTextField shopInput;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField costFrom;
    private JTextField costTo;
    private JList results;
    private MoneyContract.Presenter presenter = new MoneyPresenter(this);

    public WhereIsMyMoney() {
        frame = new JFrame("WTF");
        frame.setSize(FIELD_WIDTH + 2 * PADDING, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        shopInput = new JTextField();
        shopInput.setBounds(PADDING, PADDING, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(shopInput);

        dateFrom = new JTextField();
        dateFrom.setBounds(PADDING, 150, 75, FIELD_HEIGHT);
        frame.add(dateFrom);

        dateTo = new JTextField();
        dateTo.setBounds(175, 150, 75, FIELD_HEIGHT);
        frame.add(dateTo);

        costFrom = new JTextField();
        costFrom.setBounds(PADDING, 250, 75, FIELD_HEIGHT);
        frame.add(costFrom);

        costTo = new JTextField();
        costTo.setBounds(175, 250, 75, FIELD_HEIGHT);
        frame.add(costTo);

        results = new JList<>();
        results.setBounds(PADDING, 350, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(results);

        frame.setVisible(true);
        presenter.initData();
    }

    @Override
    public void refreshLIst(List<Cost> data) {
        DefaultListModel<Cost> list1 = new DefaultListModel<>();
        for (Cost cost: data){
            list1.addElement(cost);
        }
        results.setModel(list1);
    }


}
