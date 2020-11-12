package pl.sda.rafal.zientara.programing_I_webinar;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2, 3, 1);
        Collections.sort(list);
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(-o1, o2); //sortowanie odwrotne
            }
        });

        System.out.println(list);

        Integer[] array = new Integer[]{2, 3, 1};
        Arrays.sort(array, (Comparator<Integer>) (o1,o2) -> Integer.compare(o1,o2));

        for (Integer e : array) {
            System.out.println(e);
        }
    }
}
