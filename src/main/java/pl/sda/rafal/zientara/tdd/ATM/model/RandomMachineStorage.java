package pl.sda.rafal.zientara.tdd.ATM.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMachineStorage implements CashMachineStorage {

    private List<Cash> money;

    public RandomMachineStorage() {
        money = new ArrayList<>();
        initRandomCash();
        System.out.println("Money in ATM: "+money);
    }

    private void initRandomCash() {
        Cash[] values = Cash.values();
        Random random = new Random();
        int bankNoteCount = random.nextInt(100);
        money.add((values[0]));
        money.add(values[1]);
        money.add(values[2]);
       // money.add(values[3]);
        money.add(values[4]);
        money.add(values[4]);
        money.add(values[4]);
        money.add(values[5]);


      /*  for (int i = 0; i <bankNoteCount; i++) {
            Cash cash = values[random.nextInt(values.length)];
            money.add(cash);
        }*/
    }

    @Override
    public List<Cash> availableMoney() {
        return money;
    }
}
