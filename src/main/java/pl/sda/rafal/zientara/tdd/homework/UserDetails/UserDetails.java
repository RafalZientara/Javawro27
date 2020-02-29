package pl.sda.rafal.zientara.tdd.homework.UserDetails;

public class UserDetails {
    private String name;
    private int age;
    private String email;
    private String phoneNumber;

    public UserDetails(String name, int age, String email, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return  "Name: " + name +
                ", age: " + age  +
                ", e-mail: " + email  +
                ", phoneNumber: " + phoneNumber;
    }
}
