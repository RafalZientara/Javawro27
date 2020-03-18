package programowanie2.lesson6.homework.dashboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import programowanie2.lesson6.homework.model.Cash;
import programowanie2.lesson6.homework.model.CashMachineStorage;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class DashboardPresenterTest {

    private DashboardContract.View view;
    private DashboardContract.Presenter presenter;
    private CashMachineStorage machineStorage;

    @BeforeEach
    public void setup() {
        view = mock(DashboardContract.View.class);
        machineStorage = mock(CashMachineStorage.class);
        presenter = new DashboardPresenter(view, machineStorage);
    }

    @Test
    public void getLastCash() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));

        presenter.onCashConfirmation(70);

        verify(view).onWithdrawalConfirm(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));
    }

    @Test
    public void isMultipicityWorks() {
        presenter.onCashConfirmation(11);

        verify(view).onCashMultiplicityError();
    }

    @Test
    public void getDigitsOnlyInCash() {
        presenter.getCash("51a");

        verify(view).disableConfirmButton();
        verify(view).showOnlyDigitsError();
    }

    @Test
    public void tooMuchCashToWithdraw() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(Cash.BANK_NOTE_50, Cash.BANK_NOTE_20));

        presenter.onCashConfirmation(80);

        view.lackOfFoundsOnWithdrawalError();
    }

    @Test
    public void isCoverageOnTheAccount() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(Cash.BANK_NOTE_200, Cash.BANK_NOTE_200, Cash.BANK_NOTE_100));

        assertTrue(presenter.isCoverageOnAccount(500));
    }

    @Test
    public void amountShouldBeBiggerThanZero() {
        presenter.getCash("0");

        verify(view).zeroCashError();
        verify(view).disableConfirmButton();
    }

    @Test
    public void lenghtOfTheAMountShouldBeBiggerThanZero() {
        presenter.getCash("");

        verify(view).zeroCashError();
        verify(view).disableConfirmButton();
    }

    @Test
    public void amountEnteredCorrectly() {
        presenter.getCash("500");

        verify(view).enableConfirmButton();
        verify(view).hideError();
    }

    @Test
    public void atmCalculatesCashCorrectly() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_20,
                Cash.BANK_NOTE_10));

        presenter.onCashConfirmation(1120);

        verify(view).onWithdrawalConfirm(Arrays.asList(
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_20));
    }

    @Test
    public void atmCalculatesCorrectlyBigAmountOfMoney() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_20,
                Cash.BANK_NOTE_10,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_20,
                Cash.BANK_NOTE_10));

        presenter.onCashConfirmation(2240);

        verify(view).onWithdrawalConfirm(Arrays.asList(
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_20,
                Cash.BANK_NOTE_20));
    }

    @Test
    public void atmCalculatesCorrectly600PLN() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_200,
                Cash.BANK_NOTE_200,
                Cash.BANK_NOTE_200));

        presenter.onCashConfirmation(600);

        verify(view).onWithdrawalConfirm(Arrays.asList(
                Cash.BANK_NOTE_200,
                Cash.BANK_NOTE_200,
                Cash.BANK_NOTE_200));
    }


    @Test
    public void onCheckBalanceIsCorrect() {
        when(machineStorage.availableMoney()).thenReturn(Arrays.asList(
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_20,
                Cash.BANK_NOTE_10,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_500,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_50,
                Cash.BANK_NOTE_20,
                Cash.BANK_NOTE_10));

        presenter.onCheckBalanceConfirmation();

        verify(view).onCheckBalanceConfirm(2260);
    }
}
