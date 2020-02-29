package pl.sda.rafal.zientara.tdd.lesson3.money;

import javax.swing.*;

public abstract class CostListener extends GenericListener<Double> {

    public CostListener(JTextField field) {
        super(field);
    }

    @Override
    public Double parseValue(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception ignored) {
        }
        showError();
        return null;
    }

}

