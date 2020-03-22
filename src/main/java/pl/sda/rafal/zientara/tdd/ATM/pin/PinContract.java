package pl.sda.rafal.zientara.tdd.ATM.pin;
import pl.sda.rafal.zientara.tdd.ATM.Person;

public class PinContract {

    public interface View {
        void disableConfirmButton();
        void enableConfirmButton();

        void showTooShortPinError();
        void showTooLongPinError();
        void showOnlyDigitsError();
        void hideError();

        void correctPin();
        void wrongPin();
        Person getPerson();
    }

    public interface Presenter {
        void onPinTyping(String pin);
        void onPinConfirmed(String pin);
        Person getPerson();
    }
}
