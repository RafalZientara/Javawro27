package pl.sda.rafal.zientara.programowanie2.lesson4.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public abstract class DoubleListener implements KeyListener {
    private final JTextField field;

    public DoubleListener(JTextField field) {
        this.field = field;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    private Double parseDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (Exception e) {
            //  e.printStackTrace();
        }
        return null;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        Double price = parseDouble(input);
        System.out.println(price);
        onPriceUpdate(price);
    }

    protected abstract void onPriceUpdate(Double price);
}
