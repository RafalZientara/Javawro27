package pl.sda.rafal.zientara.tdd.lesson3.money;

import javax.swing.*;
import java.util.List;

public class WhereIsMyMoney implements MoneyContract.View{
    private JTextField chooseShop;
    private JTextField dateFrom;
    private JTextField dateTo;
    private JTextField costFrom;
    private JTextField costTo;
    private JList<Cost> result;
    private static final int PADDING =50;
    private static final int FIELD_HEIGHT =50;
    private static final int FIELD_WIDTH =200;
    private MoneyContract.Presenter presenter = new MoneyPresenter(this);


    public WhereIsMyMoney() {
        JFrame menuFrame = new JFrame();
        menuFrame.setSize(300+2*PADDING,600);
        addFieldsToFrame(menuFrame);
        presenter.initData();
        menuFrame.setVisible(true);
    }

    private void addFieldsToFrame(JFrame menuFrame) {
        chooseShop = new JTextField();
        chooseShop.setBounds(PADDING,PADDING,FIELD_WIDTH,FIELD_HEIGHT);
        dateFrom = new JTextField();
        dateFrom.setBounds(PADDING,150,75,FIELD_HEIGHT);
        dateTo = new JTextField();
        dateTo.setBounds(175,150,75,FIELD_HEIGHT);
        costFrom = new JTextField();
        costFrom.setBounds(PADDING,250,75,FIELD_HEIGHT);
        costTo = new JTextField();
        costTo.setBounds(175,250,75,FIELD_HEIGHT);
        result = new JList<>();
        result.setBounds(PADDING,350,FIELD_WIDTH,FIELD_HEIGHT);
        menuFrame.add(chooseShop);
        menuFrame.add(dateFrom);
        menuFrame.add(dateTo);
        menuFrame.add(costFrom);
        menuFrame.add(costTo);
        menuFrame.add(result);

        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void refreshList(List<Cost> data) {
        //todo
        DefaultListModel<Cost> list = new DefaultListModel<>();
        for (Cost cost: data)
            list.addElement(cost);
        result.setModel(list);
    }

}
