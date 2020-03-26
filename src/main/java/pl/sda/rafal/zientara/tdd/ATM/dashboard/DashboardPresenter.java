package pl.sda.rafal.zientara.tdd.ATM.dashboard;

import pl.sda.rafal.zientara.tdd.ATM.Person;
import pl.sda.rafal.zientara.tdd.ATM.model.Cash;
import pl.sda.rafal.zientara.tdd.ATM.model.CashMachineStorage;

import java.util.LinkedList;
import java.util.List;

public class DashboardPresenter implements DashboardContract.Presenter {
    private final DashboardContract.View view;
    private final CashMachineStorage machineStorage;
    private List<Cash> cashToWithdraw;
    private List<Cash> ATMCash;
    private List<Cash> tempCash;
    private int userBalance;
    private int lastValue;
    private Person person;

    public DashboardPresenter(DashboardContract.View view, CashMachineStorage machineStorage) {
        this.view = view;
        this.machineStorage = machineStorage;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    @Override
    public void getCashPossibility(String value) {

        person = view.getPerson();
        userBalance = person.getAvailableCash();
        setUserBalance(userBalance);
        String enter = value.trim();
        view.disableConfirmButton();
        if (enter.equals("0")) {
            view.printZeroError();
        } else if (enter.length() == 0) {
            view.printZeroError();
        } else if (!enter.matches("\\d+$")) {
            view.onlyDigitsError();
        } else if (onlyBanknotes(enter)) {
            view.onlyBanknotesError();
        } else if (!isValueToGetIsLessThanUsersBankroll(enter)) {
            view.noMoneyError();
        } else {
            view.enableConfirmButton();
            view.hideErrors();
        }
    }

    @Override
    public void onCashConfirmed(int value) {
        ATMCash = machineStorage.availableMoney();
        if (isMyAmountInATMMoreThanValueIWant(value)) {
            List<Cash> myCashToWithDraw = withdrawCash(value);
            view.onWithdrawalConfirm(myCashToWithDraw);
        } else {
            view.tooMuchMoneyError();
        }
    }

    private boolean onlyBanknotes(String enter) {
        return Integer.parseInt(enter) % 10 != 0;
    }

    private List<Cash> withdrawCash(int value) {
        int temp = value;
        cashToWithdraw = new LinkedList<>();
        Cash[] values = getCash();
        value = tryToGetValue(value, values);
        if (value != 0) {
            lastValue--;
            cashToWithdraw.clear();
            value = temp;
            values[5] = null;
            tryToGetValue(value, values);
        }
        if (value == 0) {
            person.reduceYourBalance(temp);
            return cashToWithdraw;
        } else {
            return null;
        }
    }

    private Cash[] getCash() {
        Cash[] values = Cash.values();
        lastValue = values.length - 1;
        return values;
    }


    private int tryToGetValue(int value, Cash[] values) {
        tempCash = new LinkedList<>();
        ATMCash = machineStorage.availableMoney();
        tempCash.addAll(ATMCash);
        for (int nominalIndex = lastValue; nominalIndex >= 0; nominalIndex--) {
            while (value >= values[nominalIndex].getWorth()) {
                if (isCashInCashMachine(values[nominalIndex])) {
                    cashToWithdraw.add(values[nominalIndex]);
                    tempCash.remove(values[nominalIndex]);
                    value -= values[nominalIndex].getWorth();
                } else break;
            }
        }
        if (value == 0) {
            ATMCash.clear();
            ATMCash.addAll(tempCash);
        }
        return value;
    }

    private boolean isValueToGetIsLessThanUsersBankroll(String value) {
        return Integer.parseInt(value) <= userBalance;
    }


    private boolean isCashInCashMachine(Cash cash) {
        return tempCash.contains(cash);
    }

    private boolean isMyAmountInATMMoreThanValueIWant(int valueTyped) {
        int sum = getSum(ATMCash);
        return sum >= valueTyped;
    }

    private int getSum(List<Cash> cash) {
        int sum = 0;
        for (Cash c : cash) {
            sum += c.getWorth();
        }
        return sum;
    }

    @Override
    public Person getPerson() {
        return person;
    }

}
