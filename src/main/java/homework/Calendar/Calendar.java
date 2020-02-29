package homework.Calendar;

import java.time.*;

public class Calendar {
    private LocalDate date = LocalDate.now();
    private LocalDate firstDay = date.minusDays(date.getDayOfMonth() - 1);
    private DayOfWeek first = firstDay.getDayOfWeek();
    private LocalDate currentMonth = firstDay;
    private boolean markFridays = false;

    public Calendar() {
    }

    public void setMarkFridays(boolean input) {
        this.markFridays = input;
    }


    public void printCurrentYear(){
        LocalDate startMonth = LocalDate.of(date.getYear(),1,1);
        LocalDate monthDecember = LocalDate.of(date.getYear(),12,1);

        for (int i = 1; i <= monthDecember.getMonthValue() ; i++) {
            printSpacesBeforeFirstDay(startMonth);
            printCalendarForCurrentMonth(startMonth.minusDays(startMonth.getDayOfMonth()-1), startMonth);
            startMonth = startMonth.plusMonths(1);
        }
    }

    public void printCurrentMonth(){
        printSpacesBeforeFirstDay(firstDay);
        printCalendarForCurrentMonth(firstDay,currentMonth);
    }

    private void printSpacesBeforeFirstDay(LocalDate firstDay) {
        if (firstDay.getMonthValue() != 1)
            System.out.println();

        System.out.println();
        System.out.println(firstDay.getMonth());

        for (int i = 0; i < firstDay.getDayOfWeek().getValue() - 1; i++) {
            if (!markFridays)
                System.out.print("   ");
            else
                System.out.print("      ");
        }
    }

    private void printCalendarForCurrentMonth(LocalDate firstDay, LocalDate currentMonth){
        while(firstDay.getMonth() == currentMonth.getMonth()){
            if (!markFridays) {
                System.out.printf("%2d ", currentMonth.getDayOfMonth());
            } else {
                if(currentMonth.getDayOfWeek() == DayOfWeek.FRIDAY) {
                    System.out.printf("[%2d]  ", currentMonth.getDayOfMonth());
                } else {
                    System.out.printf("%2d    ", currentMonth.getDayOfMonth());
                }
            }
            if (currentMonth.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println();
            }
            currentMonth = currentMonth.plusDays(1);
        }
    }
}
