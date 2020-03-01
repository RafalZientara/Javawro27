package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class PriceListener implements KeyListener {
    private final JTextField value;

    public PriceListener(JTextField value) {
        this.value = value;
    }


    private double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
//                e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = value.getText();
        Double value = parseDouble(input);
        System.out.println(value);
        onValueUpdate(value);
    }

    protected abstract void onValueUpdate(Double value);

}
