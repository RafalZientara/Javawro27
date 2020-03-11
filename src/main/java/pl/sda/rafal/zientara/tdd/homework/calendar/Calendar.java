package pl.sda.rafal.zientara.tdd.homework.calendar;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Calendar {

public static Month getActualMonth() {
    return LocalDate.now().getMonth();
}
public static Month getMonth(String date) {
    DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate myDate = LocalDate.parse(date,myFormatter);
    return myDate.getMonth();
}
}
