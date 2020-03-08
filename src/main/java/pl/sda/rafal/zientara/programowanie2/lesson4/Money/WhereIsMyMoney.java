package pl.sda.rafal.zientara.programowanie2.lesson4.Money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.util.List;

public class WhereIsMyMoney implements MoneyContract.View{
    private static final int FIELD_WIDTH = 400;
    private static final int FIELD_HEIGHT = 50;
    private static final int PADDING = 40;

    private JFrame frame;
    private JTextField shopInput;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField costFrom;
    private JTextField costTo;
    private JList<Cost> results;
    private JLabel sumLabel;

    private MoneyContract.Presenter presenter = new MoneyPresenter(this);

    public WhereIsMyMoney(){
        frame = new JFrame("WTF");
        frame.setSize(400 + 2 * PADDING,700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel names = new JLabel("Filter by name");
        names.setBounds(PADDING,0,FIELD_WIDTH,FIELD_HEIGHT);
        frame.add(names);
        shopInput = new JTextField();
        shopInput.setBounds(PADDING,PADDING,FIELD_WIDTH,FIELD_HEIGHT);
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
        fromToDate.setBounds(PADDING,100,FIELD_WIDTH,FIELD_HEIGHT);
        frame.add(fromToDate);
        dateFrom = new JTextField();
        dateFrom.setBounds(PADDING,150,75,FIELD_HEIGHT);
        dateFrom.addKeyListener(new DateListener(dateFrom) {
            @Override
            public void onDateUpdate(LocalDate newDate) {
                presenter.onFromDateChange(newDate);
            }
        });
        frame.add(dateFrom);

        dateTo = new JTextField();
        dateTo.setBounds(175,150,75,FIELD_HEIGHT);
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
        costFrom.setBounds(PADDING,250,75,FIELD_HEIGHT);
        costFrom.addKeyListener(new PriceListener(costFrom) {
            @Override
            public void onValueUpdate(Double price) {
                if (price !=null){
                    presenter.onPriceFromChange(price);
                } else {
                    presenter.onPriceFromChange(-1);
                }
            }
        });
        frame.add(costFrom);

        costTo = new JTextField();
        costTo.setBounds(175,250,75,FIELD_HEIGHT);
        costTo.addKeyListener(new PriceListener(costTo) {
            @Override
            public void onValueUpdate(Double price) {
                if (price !=null){
                    presenter.onPriceToChange(price);
                } else {
                    presenter.onPriceToChange(-1);
                }
            }
        });
        frame.add(costTo);

        results = new JList<>();
        results.setBounds(PADDING,310,FIELD_WIDTH,230);
        frame.add(results);

        sumLabel = new JLabel();
        sumLabel.setBounds(PADDING,560,FIELD_WIDTH,50);
        frame.add(sumLabel);

        frame.setVisible(true);
        presenter.prepareData();
        presenter.initData();
    }


    @Override
    public void refreshList(List<Cost> data) {
        //todo
        DefaultListModel<Cost> list = new DefaultListModel<>();
        for(Cost cost : data){
            list.addElement(cost);
        }
        results.setModel(list);
    }

    @Override
    public void countSum(double sum) {
        sumLabel.setText(String.format("%.2f",sum));
    }
}
