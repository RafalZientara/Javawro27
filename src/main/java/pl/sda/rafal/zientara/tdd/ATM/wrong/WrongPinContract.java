package pl.sda.rafal.zientara.tdd.ATM.wrong;

public class WrongPinContract {

    public interface View {
        void confirmMessage();
        void blockCard();
        void blockError();
    }

    public interface Presenter {
        void okClicked();

        int getPossibleNumbersOfErrorsLeft();
    }
}
