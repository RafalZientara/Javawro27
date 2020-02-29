package pl.sda.rafal.zientara.programming_II.lesson_3;

import java.time.*;

public class DatesMain {

    public static void main(String[] args) {
        LocalDate now = LocalDate.now(); // dzieñ
        LocalDateTime dateTime = LocalDateTime.now(); // dzieñ, godzina
        ZonedDateTime zonedDateTime = ZonedDateTime.now();// dzieñ, godzina i strefa

        System.out.println(now);
        System.out.println(dateTime);
        System.out.println(zonedDateTime.withZoneSameInstant(ZoneId.of("Canada/Atlantic")));

//        Set<String> allZones = ZoneId.getAvailableZoneIds();//wszystkie strefy
//        for (String zone: allZones){
//            System.out.println(zone);
//        }
        //formatowanie ze Stringa na datê
//        String input = "7-06-2017";
//        DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        LocalDate output = LocalDate.parse(input, formatter);
//        System.out.println(output);
//        System.out.println(LocalDate.now().format(formatter));
//        System.out.println(LocalDate.now().plusDays(1).format(formatter));

        LocalDate currentDate = LocalDate.now();

        int fridaysCount = 0;
        while (fridaysCount < 5){
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.FRIDAY) {
                currentDate = currentDate.plusDays(1);
            }else {
                System.out.println(currentDate);
                currentDate = currentDate.plusWeeks(1);
                fridaysCount++;
            }
        }

    }
}
