package pl.sda.rafal.zientara.programowanie2.lesson4.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class MyMonth {
    private Month month;

    public MyMonth(Month month) {
        this.month = month;
    }

    public void print() {
        LocalDate date = getFirstDayOfMonth();
        LocalDate firstDay = date.minusDays(date.getDayOfMonth() - 1);
        LocalDate currentDate = firstDay;
        System.out.println(currentDate.getMonth());
        DayOfWeek first = firstDay.getDayOfWeek();
        for (int i = 0; i < first.getValue() - 1; i++) {
            System.out.print("   ");
        }
        while (currentDate.getMonth() == firstDay.getMonth()) {
            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.printf("%2d", currentDate.getDayOfMonth()).append("-%o ");
            } else {
                System.out.printf("%2d ", currentDate.getDayOfMonth());
            }
            if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println();
            }
            currentDate = currentDate.plusDays(1);
        }
        System.out.println("\n");
    }

    private LocalDate getFirstDayOfMonth() {
        LocalDate date = getFirstDayOfYear();
        switch (month) {
            case JANUARY:
                return date.plusMonths(0);
            case FEBRUARY:
                return date.plusMonths(1);
            case MARCH:
                return date.plusMonths(2);
            case APRIL:
                return date.plusMonths(3);
            case MAY:
                return date.plusMonths(4);
            case JUNE:
                return date.plusMonths(5);
            case JULY:
                return date.plusMonths(6);
            case AUGUST:
                return date.plusMonths(7);
            case SEPTEMBER:
                return date.plusMonths(8);
            case OCTOBER:
                return date.plusMonths(9);
            case NOVEMBER:
                return date.plusMonths(10);
            case DECEMBER:
                return date.plusMonths(11);
        }
        return date;
    }

    private LocalDate getFirstDayOfYear() {
        LocalDate date = LocalDate.now();
        return date.withDayOfYear(1);
    }

}
