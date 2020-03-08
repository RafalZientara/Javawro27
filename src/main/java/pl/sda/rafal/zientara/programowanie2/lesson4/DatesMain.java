package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class DatesMain {
    public static void main(String[] args) {
        LocalDate now1 = LocalDate.now(); //dzieñ
        LocalDateTime dateTime = LocalDateTime.now(); //dzien godzina
        ZonedDateTime zonedDateTime = ZonedDateTime.now(); //dzien godzina strefa

        System.out.println(now1);
        System.out.println(dateTime);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("US/Pacific")));
        System.out.println();

//        Set<String> allZones = ZoneId.getAvailableZoneIds();
//        for(String zone: allZones){
//            System.out.println(zone);
//        }
        String stringDate = "23-02-2020";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(stringDate,formatter);
        System.out.println(date);
        System.out.println(date.format(formatter));
        System.out.println(date.minusDays(25));

        String stringDate2 = "23.02.2020";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date2 = LocalDate.parse(stringDate2,formatter2);
        System.out.println(date2);
        System.out.println(date2.format(formatter2));
        System.out.println(date2.minusDays(25));

        //todo 5 najbli¿szych piatkow
        LocalDate currentDay = LocalDate.now();
        System.out.println(currentDay.getDayOfWeek());
        System.out.println("Piateczki nadchodze. Juz niedlugo:");
        int fridaysCount =0;
        while (fridaysCount <5){
            DayOfWeek dayOfWeek = currentDay.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.FRIDAY){
                currentDay = currentDay.plusDays(1);
            } else {
                System.out.println(currentDay);
                currentDay = currentDay.plusWeeks(1);
                fridaysCount++;
            }
        }

    }
}
