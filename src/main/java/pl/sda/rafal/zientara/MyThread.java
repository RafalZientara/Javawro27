package pl.sda.rafal.zientara;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MyThread <T> implements Runnable{

    private List<T> something;
    private ArrayList<LinkedList> listOfList;


    private void addPeople(LinkedList<String> name, LinkedList<Integer> age, LinkedList<Double> salary){
        listOfList.add(name);
        listOfList.add(age);
        listOfList.add(salary);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(i + " mój watek");
        }
    }

    private List<T> printValue(T value){
        something.add(value);
        return something;
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        MyThread thread2 = new MyThread();

//        thread.run();

//        System.out.println(thread2.printValue("Pawe³"));

        thread.addPeople(new LinkedList<>(Arrays.asList("Pawe³", "Anka")),
                         new LinkedList<>(Arrays.asList(12, 14)),
                        new LinkedList<>(Arrays.asList(2300.34 , 3400.23 )));

        System.out.println(thread.listOfList);
    }
}


