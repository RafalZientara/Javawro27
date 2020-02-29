package pl.sda.rafal.zientara.tdd.lesson3.money;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public abstract class DateListener extends GenericListener<LocalDate> {

    private static final List<DateTimeFormatter> FORMATS = Arrays.asList(
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"));


    public DateListener(JTextField field) {
        super(field);
    }

    @Override
    protected LocalDate parseValue(String input) {
        for (DateTimeFormatter data : FORMATS) {
            try {
                return LocalDate.parse(input, data);
            } catch (Exception ignored) {
            }
        }
        showError();
        return null;
    }

}
