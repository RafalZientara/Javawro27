package pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm;

import pl.sda.rafal.zientara.programowanie2.lesson1.homework.farm.animals.Cow;

public class CowFarm<C extends Cow> extends OldMcDonaldFarm<C> {

    public int produceMilk() {
        int milk = 0;
        for (C cow : animals) {
            milk += cow.getMilk();
        }
        return milk;
    }

}
