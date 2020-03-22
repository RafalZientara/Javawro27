package pl.sda.rafal.zientara.tdd.ATM.dashboard;

import pl.sda.rafal.zientara.tdd.ATM.Person;
import pl.sda.rafal.zientara.tdd.ATM.model.Cash;

import java.util.List;

public class DashboardContract {

    public interface View {
        void onWithdrawalConfirm(List<Cash> money);
        void printZeroError();
        void disableConfirmButton();
        void enableConfirmButton();
        void onlyDigitsError();
        void hideErrors();
        void onlyBanknotesError();
        void tooMuchMoneyError();
        void noMoneyError();
        Person getPerson();
    }

    public interface Presenter {
        void getCashPossibility(String value);
        void onCashConfirmed(int value);
        Person getPerson();
    }
}
