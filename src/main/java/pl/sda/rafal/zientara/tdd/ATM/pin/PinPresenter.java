package pl.sda.rafal.zientara.tdd.ATM.pin;
import pl.sda.rafal.zientara.tdd.ATM.Person;
import pl.sda.rafal.zientara.tdd.ATM.wrong.IsNumber;

public class PinPresenter implements PinContract.Presenter {
    private final PinContract.View view;
    private String correctPin = "1234";
    private final int pinLength = 4;
    private Person person;


    public PinPresenter(PinContract.View view) {
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

    @Override
    public void onPinConfirmed(String pin) {
        person = view.getPerson();
        if (pin.equals(person.getPin())) {
            //correctPin = person.getPin();
            view.correctPin();
            view.enableConfirmButton();
        } else {
            view.wrongPin();
            view.disableConfirmButton();
        }
    }

    @Override
    public Person getPerson() {
        return person;
    }
}
