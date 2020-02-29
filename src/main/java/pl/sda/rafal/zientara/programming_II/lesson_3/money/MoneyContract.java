package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import java.util.List;

public class MoneyContract {

    public interface View {
        void refreshLIst(List<Cost> data);

    }

    public interface Presenter{
        void initData();
        void onWordChange(String word);

    }
}
