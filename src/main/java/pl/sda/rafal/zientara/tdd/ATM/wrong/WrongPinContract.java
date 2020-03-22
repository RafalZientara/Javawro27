package pl.sda.rafal.zientara.tdd.ATM.wrong;

public class WrongPinContract {

    public interface View {
        void confirmMessage();
    }

    public interface Presenter {
        void okClicked();
    }
}
