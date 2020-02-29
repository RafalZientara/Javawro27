package pl.sda.rafal.zientara.tdd.lesson3.money;

import java.time.LocalDate;
import java.util.List;

public class MoneyContract {

    public interface View{
        void refreshList(List<Cost> data);
        void refreshSum(List<Cost> data);
    }
    public interface Presenter{
        void prepareData();
        void initData();
        void onWordChange(String word);
        void onPriceToChange(double value);
        void onPriceFromChange(double value);
        List<Cost> getLastResult();
        void onDateChange(LocalDate date);
        void fromThisDate(LocalDate date);
        void onToThisDate(LocalDate date);
        void saveData();

        //Moja propozycja implementacji
        String getSumValue();
    }
    }
