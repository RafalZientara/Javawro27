package pl.sda.rafal.zientara.tdd.ATM.thanks;

import pl.sda.rafal.zientara.tdd.ATM.model.Cash;

import java.util.List;

public class ThanksContract {

    public interface View {
        void confirmMessage();
        void showMeTheMoney(List<Cash> money);
    }

    public interface Presenter {
        void init();
        void okClicked();
    }
}
