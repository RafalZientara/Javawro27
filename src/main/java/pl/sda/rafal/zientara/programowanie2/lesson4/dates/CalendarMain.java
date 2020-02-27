package pl.sda.rafal.zientara.programowanie2.lesson4.dates;

public class CalendarMain {

    public static void main(String[] args) {

        CalendarPrinter monthPrinter = new CalendarPrinter();
        monthPrinter.printMonthCalendar();

       /* LocalDate date = LocalDate.now();
        LocalDate firstDay = date.minusDays(date.getDayOfMonth() - 1);
        System.out.println(firstDay);
        LocalDate currentDate = firstDay;
        System.out.println(firstDay.getMonth());

        DayOfWeek first = firstDay.getDayOfWeek();
        System.out.println("Mon Tue Wed Thu Fri Sat  Sun");
        for (int i = 0; i<first.getValue()-1; i++) {
            System.out.print("    ");
        }
        while (firstDay.getMonth() == currentDate.getMonth()) {
            System.out.printf("%3d ",currentDate.getDayOfMonth());
            if (currentDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                System.out.println();
            }
            currentDate = currentDate.plusDays(1);
        }*/

    }
}
