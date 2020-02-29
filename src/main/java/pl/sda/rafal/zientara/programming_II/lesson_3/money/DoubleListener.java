package pl.sda.rafal.zientara.programming_II.lesson_3.money;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class DateListener implements KeyListener {
    private final JTextField field;
    private static final List<DateTimeFormatter> FORMATS = Arrays.asList(
    DateTimeFormatter.ofPattern("dd-MM-yyyy"),
    DateTimeFormatter.ofPattern("dd.MM.yyyy"),
    DateTimeFormatter.ofPattern("yyyy-MM-dd"),
    DateTimeFormatter.ofPattern("yyyy.MM.dd"));

    public DateListener(JTextField field) {
        this.field = field;

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    private LocalDate parseDate(String input) {
        for (DateTimeFormatter formatter: FORMATS){
            try{
                LocalDate.parse(input, formatter);
            }catch (Exception ignored){
            }
            }

        return null;
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        String input = field.getText();
        LocalDate date = parseDate(input);
        System.out.println(date);
        onDateUpdate(date);
    }

    public abstract void onDateUpdate(LocalDate newDate);


}
