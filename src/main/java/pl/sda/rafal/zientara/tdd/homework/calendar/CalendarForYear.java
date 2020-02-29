package pl.sda.rafal.zientara.tdd.homework.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;


public class CalendarForYear {
    public static void printSingleMoth() {
        int actualYear = LocalDate.now().getYear();
        int minMonthValue = 1;
        int maxMonthValue = Month.values().length;

        for (int monthForCalendar = minMonthValue; monthForCalendar <= maxMonthValue; monthForCalendar++) {
            System.out.println(Month.of(monthForCalendar));
            LocalDate date = LocalDate.of(actualYear, monthForCalendar, 1);
            printSpacesBeforeFirstDayOfMonth(date);
            printMonths(monthForCalendar, date);
            System.out.println();
        }
    }

    private static void printSpacesBeforeFirstDayOfMonth(LocalDate date) {
        int freeSpace = date.getDayOfWeek().getValue();
        for (int spaces = 1; spaces < freeSpace; spaces++) {
            System.out.print("   ");
        }
    }

    private static void printMonths(int monthForCalendar, LocalDate date) {
        while (date.getMonth().equals(Month.of(monthForCalendar))) {
            printFridaysInRed(date);
            if (date.getDayOfWeek() == DayOfWeek.SUNDAY) System.out.println();
            date = date.plusDays(1);
        }
    }

    private static final String RED = "\033[0;31m";
    private static final String RESETCOLOR = "\033[0m";

    private static void printFridaysInRed(LocalDate date) {
        if (isItFriday(date)) {
            System.out.print(RED);
            System.out.printf("%2d ", date.getDayOfMonth());
            System.out.print(RESETCOLOR);
        }
        else {
            System.out.printf("%2d ", date.getDayOfMonth());
        }
    }
    private static boolean isItFriday(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.FRIDAY);
    }
}
