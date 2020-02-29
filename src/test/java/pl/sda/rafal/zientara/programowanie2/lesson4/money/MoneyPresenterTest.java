package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MoneyPresenterTest {
    private MoneyContract.Presenter presenter;
    private MoneyContract.View view;

    @BeforeEach
    public void setup() {
        view = Mockito.mock(MoneyContract.View.class);
        presenter = new MoneyPresenter(view);
    }

    @Test
    public void test() {

    }

}