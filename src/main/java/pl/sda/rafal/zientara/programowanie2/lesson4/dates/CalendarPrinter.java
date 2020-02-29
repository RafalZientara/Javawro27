package pl.sda.rafal.zientara.programowanie2.lesson4.dates;


import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class CalendarPrinter {
    Scanner scanner = new Scanner(System.in);
    private int month;
    private int year;

    public void printMonthCalendar() {
        getDataFromUser();
        printCalendar();
    }

    public void printYearCalendar() {
        getYearFromUser();
        month = 1;
        for (int i = 0; i < 12; i++) {
            printCalendar();
            month++;
        }
    }

    private void getYearFromUser() {
        try {
            System.out.println("Enter year: YYYY");
            year = scanner.nextInt();
        } catch (MissingFormatArgumentException e) {
            e.printStackTrace();
        }
    }

    private void getMonthFromUser() {
        try {
            System.out.println("Enter month: MM");
            month = scanner.nextInt();
            if (month < 1 || month > 12) {
                throw new DateTimeException("Invalid value for month: " + month);
            } else {
                System.out.println("You enter: " + Month.of(month));
            }
        } catch (MissingFormatArgumentException e) {
            e.printStackTrace();
        }
    }


    private void getDataFromUser() {
        getMonthFromUser();
        getYearFromUser();
        scanner.close();
    }

    private void printCalendar() {
        LocalDate date = LocalDate.of(year, month, 15);
        LocalDate firstDay = date.minusDays(date.getDayOfMonth() - 1);
        System.out.println(firstDay);
        LocalDate currentDate = firstDay;
        System.out.println(firstDay.getMonth());

        DayOfWeek first = firstDay.getDayOfWeek();
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 0; i < first.getValue() - 1; i++) {
            System.out.print("    ");
        }
        while (firstDay.getMonth() == currentDate.getMonth()) {
            System.out.printf("%3d ", currentDate.getDayOfMonth());
            if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println();
            }

            currentDate = currentDate.plusDays(1);
        }
        System.out.println("\n");

    }
}
