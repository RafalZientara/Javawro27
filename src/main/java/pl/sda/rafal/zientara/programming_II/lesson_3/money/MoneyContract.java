package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import java.time.LocalDate;
import java.util.List;

public class MoneyContract {

    public interface View {
        void refreshLIst(List<Cost> data);
        void refreshSum(double sum);
    }

    public interface Presenter{
        void prepareData();
        void initData();
        void onWordChange(String word);
        void onPriceFromChange(double fromPrice);
        void onPriceToChange(double toPrice);

        List<Cost> getLastResult();

        void onFromDateChange(LocalDate fromDate);

        void onToDateChange(LocalDate toDate);
    }
}
