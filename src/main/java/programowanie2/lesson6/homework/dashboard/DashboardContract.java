package programowanie2.lesson6.homework.dashboard;

import programowanie2.lesson6.homework.model.Cash;

import java.util.List;

public class DashboardContract {

    public interface View {
        //confirmations
        void onWithdrawalConfirm(List<Cash> money);
        void onCheckBalanceConfirm(int value);
        void onDepositConfirm();

        //errors
        void lackOfFoundsOnWithdrawalError();
        void tooMuchCashOnDepositError();
        void onCashMultiplicityError();
        void showOnlyDigitsError();
        void zeroCashError();
        void hideError();

        //button
        void disableConfirmButton();
        void enableConfirmButton();
    }

    public interface Presenter {
        void getCash(String value);
        void insertCash(int value);
        boolean isCoverageOnAccount(int value);
        void onCashConfirmation(int value);
        void onCheckBalanceConfirmation();
    }
}
