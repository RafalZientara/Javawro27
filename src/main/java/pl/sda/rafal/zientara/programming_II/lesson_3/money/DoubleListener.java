package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class DoubleListener implements KeyListener {
    private final JTextField field;

    public DoubleListener(JTextField field) {
        this.field = field;

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    private double parseDouble(String input) {
        System.out.println("Try parsing " + input);
            try{
                Double.parseDouble(input);
            }catch (Exception ignored){
            }
        return 0;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        double value = parseDouble(input);
        System.out.println(value);
        onValueUpdate(value);
    }

    public abstract void onValueUpdate(Double value);


}
