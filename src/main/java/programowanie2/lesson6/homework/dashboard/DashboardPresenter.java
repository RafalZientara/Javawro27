package programowanie2.lesson6.homework.dashboard;

import programowanie2.lesson6.homework.model.Cash;
import programowanie2.lesson6.homework.model.CashMachineStorage;

import java.util.*;

public class DashboardPresenter implements DashboardContract.Presenter {
    private final DashboardContract.View view;
    private final CashMachineStorage machineStorage;


    public DashboardPresenter(DashboardContract.View view, CashMachineStorage machineStorage) {
        this.view = view;
        this.machineStorage = machineStorage;
    }

    @Override
    public void getCash(String value) {
        String amount = value.trim();

        if (amount.length() == 0) {
            view.zeroCashError();
            view.disableConfirmButton();
        }
        else if (amount.equals("0")){
            view.zeroCashError();
            view.disableConfirmButton();
        }
        else if (!amount.matches("\\d+$")){
            view.showOnlyDigitsError();
            view.disableConfirmButton();
        }
        else {
            view.enableConfirmButton();
            view.hideError();
        }
    }

    private List<Cash> withdrawCash(int value) {
        List<Cash> machineCash = machineStorage.availableMoney();
        List<Cash> cashToWithdraw = new ArrayList<>();

        int counter = 0;

        machineCash.sort(Comparator.comparingInt(Cash::getWorth).reversed());

        for (Iterator<Cash> cash = machineCash.iterator(); cash.hasNext();){
            Cash bankNote = cash.next();
            if (bankNote.getWorth() <= value) {
                if (!(bankNote.getWorth() + counter > value)) {
                    counter += bankNote.getWorth();
                    cashToWithdraw.add(bankNote);
//                    cash.remove();
                }
            }
        }
        System.out.println(counter);
        return cashToWithdraw;
    }

    @Override
    public void insertCash(int value) {

    }

    @Override
    public boolean isCoverageOnAccount(int value) {
        List<Cash> cash = machineStorage.availableMoney();
        int totalAmount = getTotalAmount(cash);
        return totalAmount - value >= 0;
    }

    @Override
    public void onCashConfirmation(int value) {
        if (isMultiplicitalyOfTheValueConfirmed(value)) {
            if (isCoverageOnAccount(value)) {
                List<Cash> cashToWithdraw = withdrawCash(value);
                view.onWithdrawalConfirm(cashToWithdraw);
            } else {
                view.lackOfFoundsOnWithdrawalError();
            }
        } else {
            view.onCashMultiplicityError();
        }
    }

    @Override
    public void onCheckBalanceConfirmation() {
        int balance = getTotalAmount(machineStorage.availableMoney());
        view.onCheckBalanceConfirm(balance);
    }

    private boolean isMultiplicitalyOfTheValueConfirmed(int value) {
        return value % 10 == 0;
    }

    private int getTotalAmount(List<Cash> cash) {
        int count = 0;
        for (Cash cash1: cash)
            count+=cash1.getWorth();
        return count;
    }


}
