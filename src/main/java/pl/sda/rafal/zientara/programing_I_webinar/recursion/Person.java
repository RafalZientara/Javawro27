package pl.sda.rafal.zientara.programing_I_webinar.recursion;

public class Person {

    public final int age;
    public final String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
