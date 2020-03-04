package programowanie2.lesson4.money;

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

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        double price = Double.parseDouble(input);
        System.out.println(price);
        onPriceUpdate(price);
    }

    public abstract void onPriceUpdate(Double price);
}
