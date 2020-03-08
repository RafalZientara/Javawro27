package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class PrintYear {
    public static void main(String[] args) {

        Scanner scanner= new Scanner(System.in);
        System.out.println("Który rok wyœwietliæ?");
        int year = scanner.nextInt();
        scanner.close();

        for (int i = 1; i <=12 ; i++) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate;
            if (i < 10) {
                String stringDate = "" + year + "-0" + i + "-01";
                startDate = LocalDate.parse(stringDate, formatter);
            } else {
                String stringDate = "" + year + "-" + i + "-01";
                startDate = LocalDate.parse(stringDate, formatter);
            }

            LocalDate currentDate = startDate;
            DayOfWeek first = startDate.getDayOfWeek();
            System.out.println();
            System.out.println(currentDate.getMonth());
            for (int j = 1; j < first.getValue(); j++) {
                System.out.print("   ");
            }
            while (currentDate.getMonth() == startDate.getMonth()) {
                if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY){
                    System.out.printf("[%2d] ", currentDate.getDayOfMonth());
                } else {
                    System.out.printf("%2d ", currentDate.getDayOfMonth());

                    if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        System.out.println();
                    }
                }
                    currentDate = currentDate.plusDays(1);
                }

            System.out.println();
        }
}
}
