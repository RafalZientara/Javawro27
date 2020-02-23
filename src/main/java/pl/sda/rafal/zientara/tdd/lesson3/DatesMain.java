package pl.sda.rafal.zientara.tdd.lesson3;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class DatesMain {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        Set<String> allZones = ZoneId.getAvailableZoneIds();
        System.out.println(now);
        System.out.println(localDateTime);
        System.out.println("aktualna data: " + zonedDateTime);
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("America/Cuiaba")));
        String date = "07-06-2017";
        String myDateFormat = "02,10,2030";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFromString = LocalDate.parse(date, formatter);
        System.out.println(dateFromString.toString());
        dateFromString=  dateFromString.minusDays(4);
        System.out.println(dateFromString.toString());
        DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        LocalDate myDate = LocalDate.parse(myDateFormat,myFormatter);
        System.out.println(myDate.toString());
        myDate= myDate.minusDays(5);
        System.out.println(myDate.toString());
        System.out.println(LocalDate.now().format(myFormatter));

        printFridays();
        calendar();
    }

    private static void printFridays() {
        LocalDate currentDate = LocalDate.now();
        int fridaysCount= 0;
        StringBuilder result = new StringBuilder();
        while (fridaysCount<5) {
            if (currentDate.getDayOfWeek()== DayOfWeek.FRIDAY) {
                result.append(currentDate).append("\n");
                fridaysCount++;
            }
            currentDate = currentDate.plusDays(1);
        }
        System.out.println(result);
    }

    private static void calendar() {
        LocalDate date = LocalDate.now();
        LocalDate currentDay = date.minusDays(date.getDayOfMonth()-1);
        System.out.println(currentDay);
        System.out.println(currentDay.getMonth());
        int a = currentDay.getDayOfWeek().getValue();
        for (int i = 1; i <a; i++) {
            System.out.print("   ");
        }
        while (date.getMonth()==currentDay.getMonth()) {
            System.out.printf("%2d ",currentDay.getDayOfMonth());
            if (currentDay.getDayOfWeek()==DayOfWeek.SUNDAY) System.out.println();
                currentDay = currentDay.plusDays(1);
        }
    }

}
