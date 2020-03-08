package pl.sda.rafal.zientara.programowanie2.lesson4;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarMain {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        LocalDate firstDay = date.minusDays(date.getDayOfMonth() - 1);
        System.out.println(firstDay);
        LocalDate currentDate = firstDay;
        System.out.println(currentDate.getMonth());
        DayOfWeek first = firstDay.getDayOfWeek();
//        System.out.println(first.getValue());
        for (int i = 1; i < first.getValue(); i++) {
            System.out.print("   ");
        }
        while (currentDate.getMonth()==firstDay.getMonth()){
            System.out.printf("%2d ",currentDate.getDayOfMonth());
            if (currentDate.getDayOfWeek()== DayOfWeek.SUNDAY){
                System.out.println();
            }
            currentDate=currentDate.plusDays(1);
        }


    }
}
