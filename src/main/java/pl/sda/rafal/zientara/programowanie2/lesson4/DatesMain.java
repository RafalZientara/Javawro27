package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class DatesMain {

    public static void main(String[] args) {

        LocalDate localDate = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("Canada/Atlantic")));

        Set<String> allZones = ZoneId.getAvailableZoneIds();
        int counter = 0;
        for (String zone : allZones) {
//            System.out.println(zone);
            counter++;
        }
        System.out.println(counter);

        String input = "07-06-2017";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(input, formatter);
        System.out.println(date.format(formatter));
        System.out.println(LocalDate.now().plusDays(1).format(formatter));
        System.out.println("===================================");
        LocalDate currentDate = LocalDate.now();
        int fridaysCount = 0;
        while (fridaysCount < 5) {
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.FRIDAY) {
                currentDate = currentDate.plusDays(1);
            }
            else {
                System.out.println(currentDate);
                currentDate = currentDate.plusWeeks(1);
                fridaysCount++;
            }
        }






    }

}
