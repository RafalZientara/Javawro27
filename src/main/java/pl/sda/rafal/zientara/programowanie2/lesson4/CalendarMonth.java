package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalendarMonth {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Który miesi¹c wyœwietliæ? \nPodaj liczbê z zakresu: 1-12");
        int month = scanner.nextInt();
        System.out.println("Który rok wyœwietliæ?");
        int year = scanner.nextInt();
        scanner.close();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate;
        if(month<10) {
            String stringDate = "" + year + "-0" + month + "-01";
            startDate = LocalDate.parse(stringDate, formatter);
        } else {
            String stringDate = "" + year + "-" + month + "-01";
            startDate = LocalDate.parse(stringDate, formatter);
        }

        LocalDate currentDate = startDate;
        DayOfWeek first = startDate.getDayOfWeek();
        System.out.println(currentDate.getMonth());
        for (int i = 1; i < first.getValue(); i++) {
            System.out.print("  - ");
        }
        while (currentDate.getMonth() == startDate.getMonth()) {
            System.out.printf("%2d ", currentDate.getDayOfMonth());
            if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println();
            }
            currentDate = currentDate.plusDays(1);
        }
    }
}
