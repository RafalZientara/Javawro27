package programowanie2.lesson6.homework.pin;

public class PinPresenter implements PinContract.Presenter {
    private final PinContract.View view;

    public PinPresenter(PinContract.View view) {
        this.view = view;
    }

    @Override
    public void onPinTyping(String pin) {
            checkPin(pin);
    }

    private void checkPin(String pin) {
        String enteredPin = pin.trim();

        if (enteredPin.length() == 0) {
            view.hideError();
            view.disableConfirmButton();
        }
        else if (!enteredPin.matches("\\d+$")){
            view.showOnlyDigitsError();
            view.disableConfirmButton();
        }
        else if (enteredPin.length() < 4){
            view.showTooShortPinError();
            view.disableConfirmButton();
        }
        else if (enteredPin.length() > 4){
            view.showTooLongPinError();
            view.disableConfirmButton();
        }
        else {
            view.enableConfirmButton();
            view.hideError();
        }
    }

    @Override
    public void onPinConfirmed(String pin) {
        if (pin.equals("1234")) view.correctPin();
        else view.wrongPin();
    }

}
