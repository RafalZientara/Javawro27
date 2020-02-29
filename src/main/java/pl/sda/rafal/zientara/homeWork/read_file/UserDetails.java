package pl.sda.rafal.zientara.homeWork.read_file;

public class UserDetails {
    private String name;
    private int age;
    private String eMail;
    private String phoneNumber;

    public UserDetails(String name, int age, String eMail, String phoneNumber) {
        this.name = name;
        this.age = age;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String geteMail() {
        return eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                ", age=" + age +
                ", eMail='" + eMail + '\'' +
                ", phoneNumber='" + phoneNumber;
    }
}
