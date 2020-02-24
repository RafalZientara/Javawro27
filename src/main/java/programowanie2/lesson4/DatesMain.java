package programowanie2.lesson4;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class DatesMain {
    public static void main(String[] args) {
//        LocalDate now = LocalDate.now(); //dzien
//        LocalDateTime dateTime = LocalDateTime.now(); //dzien godzina
//        ZonedDateTime zonedDateTime = ZonedDateTime.now(); //dzien godzina strefa
//        Set<String> allZones = ZoneId.getAvailableZoneIds();


        String time = "07-06-2017";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(time, formatter);

        LocalDate output = date.minusDays(10);
        System.out.println(output);
        System.out.println(output.format(formatter));

        LocalDate currentDate = LocalDate.now();
        System.out.println("Piateczki nadchodza! Looknij ponizej:");

        int fridaysCount = 0;

        while(fridaysCount < 5){
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.FRIDAY){
                currentDate = currentDate.plusDays(1);
            } else {
                System.out.println(currentDate);
                currentDate = currentDate.plusWeeks(1);
                fridaysCount++;
            }
        }


//        System.out.println(now);
//        System.out.println(dateTime);
//        System.out.println(zonedDateTime);
//        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("America/Chicago")));
//
//        for (String zone: allZones){
//            System.out.println(zone);
//        }
    }

}
