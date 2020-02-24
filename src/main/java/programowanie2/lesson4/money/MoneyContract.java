package programowanie2.lesson4.money;

import java.util.List;

public class MoneyContract {

    public interface View {
        void refreshList(List<Cost> data);
    }

    public interface presenter {
        void initData();
        void onWordChange(String word);
    }


}
