package pl.sda.rafal.zientara.tdd.ATM.cardID;

import pl.sda.rafal.zientara.tdd.ATM.Person;

public class IdContract {
    public interface View {
        void disableConfirmButton();
        void enableConfirmButton();

        void showOnlyDigitsError();
        void hideError();

        void correctId();
        void wrongId();

    }

    public interface Presenter {
        void onCardIdTyping(String text);
        void onCardIdConfirmed(String text);
        Person getPerson();
    }
}
