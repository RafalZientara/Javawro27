package pl.sda.rafal.zientara.tdd.ATM.ChangePin;

public class ChangePinContract {
    public interface Presenter {

        void onPinTyping(String pin);
        void areNewPINsCorrect(String pin1, String pin2);
        boolean onPinConfirmed(String originalOldPin, String typedOldPin);
    }

    public interface View {
        void disableConfirmButton();
        void enableConfirmButton();
        void hideError();
        void showTooShortPinError();
        void showOnlyDigitsError();
        void showTooLongPinError();
        void showNewPinNotMatchError();

    }
}
