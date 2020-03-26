package pl.sda.rafal.zientara.tdd.ATM.wrong;

import java.util.concurrent.atomic.AtomicInteger;

public class WrongPinPresenter implements WrongPinContract.Presenter {
    private final WrongPinContract.View view;
    private final int NUMBER_OF_ERRORS = 3;
    private AtomicInteger currentNumberOfErrors = new AtomicInteger(0);

    public WrongPinPresenter(WrongPinContract.View view) {
        this.view = view;
    }

    public void ifExploded() {
        currentNumberOfErrors.addAndGet(1);
        if (currentNumberOfErrors.get()>=NUMBER_OF_ERRORS) {
            view.blockCard();
            view.blockError();
        }
        else {
            view.confirmMessage();
        }
    }

    public int getPossibleNumbersOfErrorsLeft() {
        return NUMBER_OF_ERRORS - currentNumberOfErrors.get()-1;
    }

    @Override
    public void okClicked() {
        ifExploded();
    }
}
