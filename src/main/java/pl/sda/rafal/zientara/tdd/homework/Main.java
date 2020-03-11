package pl.sda.rafal.zientara.tdd.homework;

import pl.sda.rafal.zientara.tdd.homework.UserDetails.FileOpener;
import pl.sda.rafal.zientara.tdd.homework.calendar.Calendar;
import pl.sda.rafal.zientara.tdd.homework.calendar.CalendarForYear;

import java.time.Month;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(Calendar.getActualMonth());
//        typeDate();
        CalendarForYear.printSingleMoth();
        new FileOpener();
    }

    private static void typeDate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type date in format dd-MM-yyyy");
        String date = scanner.nextLine();
        System.out.println(Calendar.getMonth(date));
    }
}
