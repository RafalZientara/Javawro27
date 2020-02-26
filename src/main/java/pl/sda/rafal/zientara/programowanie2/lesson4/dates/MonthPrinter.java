package pl.sda.rafal.zientara.programowanie2.lesson4.dates;


import java.time.DateTimeException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.MissingFormatArgumentException;
import java.util.Scanner;

public class MonthPrinter {

    private int month;
    private int year;

    public void print() {
        getMonthAndYearFromUser();
        printCalendar();
    }

    private void getMonthAndYearFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter month: MM");
        try {
            month = scanner.nextShort();
            if (month < 1 || month > 12) {
                throw new DateTimeException("Invalid value for MonthOfYear: " + month);
            } else {
                System.out.println("You enter: " + Month.of(month));
                System.out.println("=============================");
                System.out.println("Enter year");
                year = scanner.nextInt();
                scanner.close();
            }
        } catch (MissingFormatArgumentException e) {
            e.printStackTrace();
        }
    }

    private void printCalendar() {
        LocalDate date = LocalDate.of(year, month, 15);
        LocalDate firstDay = date.minusDays(date.getDayOfMonth() - 1);
        System.out.println(firstDay);
        LocalDate currentDate = firstDay;
        System.out.println(firstDay.getMonth());

        DayOfWeek first = firstDay.getDayOfWeek();
        System.out.println("Mon Tue Wed Thu Fri Sat  Sun");
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

    }
}
