package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
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
    private JTextField sumLabel;
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
        shopInput.addKeyListener(new KeyListener() {  //wyszukiwania po nazwie
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
        frame.add(dateFrom);

        dateTo = new JTextField();
        dateTo.setBounds(175, 150, 75, FIELD_HEIGHT);
        dateTo.addKeyListener(new DateListener(dateTo) {
            @Override
            public void onDateUpdate(LocalDate newDate) {
                presenter.onToDateChange(newDate);
            }
        });
        frame.add(dateTo);


        JLabel fromToCost = new JLabel("Filter by price from to");
        fromToCost.setBounds(PADDING, 200, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(fromToCost);
        costFrom = new JTextField();
        costFrom.setBounds(PADDING, 250, 75, FIELD_HEIGHT);
        dateFrom.addKeyListener(new DateListener(dateFrom) {
            @Override
            public void onDateUpdate(LocalDate newDate) {
                presenter.onFromDateChange(newDate);
            }
        });
        frame.add(costFrom);

        costTo = new JTextField();
        costTo.setBounds(175, 250, 75, FIELD_HEIGHT);
        costTo.addKeyListener(new DoubleListener(costTo) {
            @Override
            public void onValueUpdate(Double newValue) {
                if (newValue != null){
                    presenter.onPriceToChange(newValue);
                }else {
                    presenter.onPriceToChange(-1);
                }
            }
        });
        frame.add(costTo);

        results = new JList<>();
        results.setBounds(PADDING, 350, FIELD_WIDTH, 100);
        frame.add(results);


        JLabel sumCost = new JLabel("Sum shopping");
        sumCost.setBounds(PADDING, 450, FIELD_WIDTH, FIELD_HEIGHT);
        frame.add(sumCost);
        sumLabel = new JTextField();
        sumLabel.setBounds(PADDING, 500, 50, 50);
        frame.add(sumLabel);

        frame.setVisible(true);
        presenter.prepareData();
        presenter.initData(); // inicjalizacja musi byæ na koñcu
    }

    @Override
    public void refreshLIst(List<Cost> data) {
        DefaultListModel<Cost> list1 = new DefaultListModel<>();
        for (Cost cost: data){
            list1.addElement(cost);
        }
        results.setModel(list1);
    }

    @Override
    public void refreshSum(double sum) {
        sumLabel.setText(String.format("%2.f",sum));
    }


}
