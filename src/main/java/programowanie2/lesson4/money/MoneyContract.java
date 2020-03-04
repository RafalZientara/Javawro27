package programowanie2.lesson4.money;

import java.time.LocalDate;
import java.util.List;

public class MoneyContract {
    public interface View {

        void refreshList(List<Cost> data);
        //todo napisac metode to liczenia sumy
    }


    public interface Presenter {
        void prepareData();
        void initData();
        void onWordChange(String word);
        void onPriceFromChange(double fromPrice);
        void onPriceToChange(double fromPrice);
        void onFromDateChange(LocalDate of);
        void onToDateChange(LocalDate toDate);
        List<Cost> getLastResult();
    }


}
