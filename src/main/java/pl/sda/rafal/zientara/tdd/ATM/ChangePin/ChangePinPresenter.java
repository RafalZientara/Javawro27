package pl.sda.rafal.zientara.tdd.ATM.ChangePin;

import pl.sda.rafal.zientara.tdd.ATM.wrong.IsNumber;

public class ChangePinPresenter implements ChangePinContract.Presenter{

    private final ChangePinContract.View view;
    private int pinLength =4;


    public ChangePinPresenter(ChangePinContract.View view) {
        this.view = view;
    }

        @Override
        public void onPinTyping(String pin) {
            view.disableConfirmButton();
            if (!IsNumber.isNumeric(pin)) view.showOnlyDigitsError();
            else if (pin.trim().length() < pinLength) view.showTooShortPinError();
            else if (pin.trim().length() > pinLength) view.showTooLongPinError();
            else {
                view.enableConfirmButton();
                view.hideError();
            }
        }

        public void areNewPINsCorrect(String newPin, String newPinAgain){
            view.disableConfirmButton();
            if (!newPinMatches(newPin,newPinAgain))view.showNewPinNotMatchError();
            else {
                view.enableConfirmButton();
                view.hideError();
            }
        }

    @Override
    public boolean onPinConfirmed(String typed, String oldPin) {

        return typedOldPinMatchesOldPin(typed, oldPin);
    }

    private boolean typedOldPinMatchesOldPin(String originalPin, String oldPin) {
        return originalPin.equals(oldPin);
    }

    private boolean newPinMatches(String newPin, String againNewPin) {
        return newPin.equals(againNewPin);
    }
}
