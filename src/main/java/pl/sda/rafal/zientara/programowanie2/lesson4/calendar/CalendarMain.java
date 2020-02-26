package pl.sda.rafal.zientara.programowanie2.lesson4.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class CalendarMain {

    public static void main(String[] args) {
        
        MyMonth month1 = new MyMonth(Month.JANUARY);
        MyMonth month2 = new MyMonth(Month.FEBRUARY);
        MyMonth month3 = new MyMonth(Month.MARCH);
        MyMonth month4 = new MyMonth(Month.APRIL);
        MyMonth month5 = new MyMonth(Month.MAY);
        MyMonth month6 = new MyMonth(Month.JUNE);
        MyMonth month7 = new MyMonth(Month.JULY);
        MyMonth month8 = new MyMonth(Month.AUGUST);
        MyMonth month9 = new MyMonth(Month.SEPTEMBER);
        MyMonth month10 = new MyMonth(Month.OCTOBER);
        MyMonth month11 = new MyMonth(Month.NOVEMBER);
        MyMonth month12 = new MyMonth(Month.DECEMBER);

        month1.print();
        month2.print();
        month3.print();
        month4.print();
        month5.print();
        month6.print();
        month7.print();
        month8.print();
        month9.print();
        month10.print();
        month11.print();
        month12.print();




    }
}
