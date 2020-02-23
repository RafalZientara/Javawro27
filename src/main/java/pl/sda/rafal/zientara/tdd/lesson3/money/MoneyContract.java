package pl.sda.rafal.zientara.tdd.lesson3.money;

import java.util.List;

public class MoneyContract {

    public interface View{
        void refreshList(List<Cost> data);
    }
    public interface Presenter{
        void initData();
        void onWordChange(String word);
        }
    }
