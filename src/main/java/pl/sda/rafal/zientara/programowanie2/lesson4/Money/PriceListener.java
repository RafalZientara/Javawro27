package pl.sda.rafal.zientara.programowanie2.lesson4.Money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;

public abstract class PriceListener implements KeyListener {
    private final JTextField field;
    public PriceListener(JTextField field) {
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private Double parseDouble(String input) {
            try {
                return Double.parseDouble(input);
            } catch (Exception ignored){
            }
        return null;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        Double value = parseDouble(input);
        System.out.println(value);
        onValueUpdate(value);
    }
    public abstract void onValueUpdate (Double priceFrom);
}
